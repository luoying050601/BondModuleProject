package com.cscec8b.bond.module.service.serviceImpl;


import com.alibaba.fastjson.JSON;
import com.cscec8b.bond.module.object.*;
import com.cscec8b.bond.module.service.BondReqApiService;
import com.cscec8b.common.module.object.*;
import com.cscec8b.common.module.object.exceptionObject.CommonException;
import com.cscec8b.common.module.util.RequestBodyUtil;
import com.cscec8b.common.module.util.ResponseBodyUtil;
import com.cscec8b.common.module.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BondReqApiServiceImpl extends BaseServiceImpl implements BondReqApiService {

    Logger logger = LoggerFactory.getLogger(BondReqApiServiceImpl.class);


    @PostConstruct
    public BondReqApiService getSelfProxy() {
        return applicationContext.getBean(BondReqApiService.class);
    }

    /**
     * 确认非cbs方式的付款
     */
    @Override
    @Transactional(transactionManager = "TransactionManager_one")
    public void confirmPayment(Integer reqId) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        /** 1修改申请单的支付日期。支付日期改为当前日期，已通过其他方式付款*/
        try {
            bondReqApiDao.updateBondReqDetailPayDate(reqId, cscecUser);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CommonException("修改支付日期出错");
        }
        /**2获取cbs制单主键,生成erp的凭证*/
        Integer transId = null;
        try {
            transId = bondLedgerApiDao.getCBSStatementIdBySourceIdAndType(reqId, "BOND");
            /**将cbs记录状态修改成推送成功*/
            if (transId != null) {
                logger.info("cbs记录的主键:"+transId);
                bondLedgerApiDao.updateCbsPaymentStatus(transId);
                Map map = new HashMap();
                map.put("p_source_id", transId);
                bondLedgerApiDao.createErpCft(map);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CommonException("生成ERP失败");
        }
    }

    /**
     * 获取留言
     *
     * @param sourceId
     * @param sourceType
     * @return
     */
    @Override
    public List<Message> getMessage(Integer sourceId, String sourceType) {
        List<Message> messages = new ArrayList<>();

        try {
            messages = bondReqApiDao.getMessages(sourceId, sourceType);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CommonException("获取留言信息异常");
        }
        return messages;
    }

    /**
     * 保存留言信息
     *
     * @param message
     * @return
     */
    @Override
    @Transactional(transactionManager = "TransactionManager_one")
    public Integer saveMessage(Message message) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        try {
            if (StringUtils.isEmpty(message.getMessageId())) {
                bondReqApiDao.insertMessage(message, cscecUser);

            } else {
                bondReqApiDao.updateMessage(message, cscecUser);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CommonException("保存留言信息异常");
        }
        Integer messageId = message.getMessageId();

        return messageId;
    }

    /**
     * 根据主键或单据号查询保证金详情
     *
     * @param reqId
     * @param documentNumber
     * @return
     */
    @Override
    public BondReqDetail getBondReqDetail(Integer reqId, String documentNumber) {
        if (reqId == null && (documentNumber == null || "".equals(documentNumber))) {
            throw new CommonException("【错误信息】:主键和单据号不能同时为空！");
        }
        BondReqDetail bondReqDetail = new BondReqDetail();
        String claimDocumentNumber = null;
        try {
            /*1、获取保证金申请详情*/
            bondReqDetail = bondReqApiDao.getBondReqDetailByReqIdOrDocumentNumber(reqId, documentNumber);
            /*2、获取是否存在认领单*/
            if (bondReqDetail != null && bondReqDetail.getReqId() != null) {
                /*1 变更状态*/
                String changStatus = bondReqApiDao.getChangeStatus(bondReqDetail.getReqId());
                if (changStatus != null) {
                    bondReqDetail.setChangeStatus(changStatus);
                }
                /*2*/
                claimDocumentNumber = bondClaimApiDao.getBondClaimDetailDocumentNumber(bondReqDetail.getReqId());
            }

            if (claimDocumentNumber != null) {
                bondReqDetail.setClaimDocumentNumber(claimDocumentNumber);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：获取保证金详情失败！");
        }
        return bondReqDetail;
    }

    /**
     * 保证金申请保存接口
     *
     * @param bondReqDetail
     * @param cscecUser
     * @return
     */
    @Override
    @Transactional(transactionManager = "TransactionManager_one", propagation = Propagation.REQUIRES_NEW)
    public String saveBondReqDetail(BondReqDetail bondReqDetail, CSCECUser cscecUser) {
        String result = null;
        String accountName = bondReqDetail.getTendereeAccountName();
        String accountBank = bondReqDetail.getTendereeAccountBankName();
        String accountBankNumber = bondReqDetail.getTendereeAccountBankNumber();

        String accountBankNumber2 = accountBankNumber.replaceAll(" ", "");

        bondReqDetail.setTendereeAccountBankNumber(accountBankNumber2);

        String tendereeBankType = bondReqDetail.getTendereeBankType();
        String tendereeProvince = bondReqDetail.getTendereeProvince();
        String tendereeCity = bondReqDetail.getTendereeCity();
        //验证招标方银行信息
        Boolean boo = RequestBodyUtil.isEmpty(accountName)
                && RequestBodyUtil.isEmpty(accountBank)
                && RequestBodyUtil.isEmpty(accountBankNumber2);

        try {
            /*如果有一个不是空*/
            if (!boo) {
                //判断银行数据是否存在 ：
                TendereeAccount tendereeAccount = bondConfigApiDao.getTendereeAccount(accountName, accountBank, accountBankNumber);
                if (tendereeAccount == null) {
                    //插入一条银行数据
                    bondConfigApiDao.getNextval();
                    TendereeAccount newTendereeAccount = new TendereeAccount();
                    newTendereeAccount.setAccountName(accountName);
                    newTendereeAccount.setAccountBank(accountBank);
                    newTendereeAccount.setAccountBankNumber(accountBankNumber2);
                    newTendereeAccount.setBankType(tendereeBankType);
                    newTendereeAccount.setProvince(tendereeProvince);
                    newTendereeAccount.setCity(tendereeCity);
                    newTendereeAccount.setAccountId(bondConfigApiDao.getNextval());
                    bondConfigApiDao.insertTendereeAccount(newTendereeAccount, cscecUser);
                }
            }
            //设置初始值
            if (StringUtils.isEmpty(bondReqDetail.getPreparedDate())) {
                bondReqDetail.setPreparedDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            }
            /**设置支付方式默认CBS*/
            if (StringUtils.isEmpty(bondReqDetail.getPayType())) {
                bondReqDetail.setPayType("CBS");
            }
            if (bondReqDetail.getDocumentNumber() == null || "".equals(bondReqDetail.getDocumentNumber())) {
                //获取主键和单据号
                Map map = new HashMap();
                map.put("docType", "FDM_BOND_REQ");
                map.put("orgId", cscecUser.getOrgId());
                bondReqApiDao.callGetDocNumber(map);
                Integer reqId = bondReqApiDao.getNextval();
                result = map.get("docNum").toString();
                bondReqDetail.setDocumentNumber(result);
                bondReqDetail.setReqId(reqId);
                //新建
                bondReqApiDao.insertBondReqDetail(bondReqDetail, cscecUser);
            } else {
                //更新
                bondReqApiDao.updateBondReqDetail(bondReqDetail, cscecUser);
                result = bondReqDetail.getDocumentNumber();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：保存保证金申请单异常！");
        }
        return result;
    }


    /**
     * 提交
     *
     * @param bondReqDetail
     * @param cscecUser
     * @return
     */
    @Override
    @Transactional(transactionManager = "TransactionManager_one")
    public ResultBean submitBondReqDetail(BondReqDetail bondReqDetail, CSCECUser cscecUser) {
        //验证信息
        ValidateUtil.checkIsNull(bondReqDetail.getInvestFlag(), "是否为投资类");
        ValidateUtil.checkIsNull(bondReqDetail.getISFlag(), "是否为基础设施类");
        ValidateUtil.checkIsNull(bondReqDetail.getApplyType(), "申请类型");
        ValidateUtil.checkIsNull(bondReqDetail.getProjectName(), "项目名称");
        ValidateUtil.checkIsNull(bondReqDetail.getBondType(), "保证金类型");
        ValidateUtil.checkIntegerIsNull(bondReqDetail.getFinanceHandler(), "财务经办人");
        ValidateUtil.checkIntegerIsNull(bondReqDetail.getMarketHandler(), "市场经办人");
        ValidateUtil.checkIsNull(bondReqDetail.getRepaymentDate(), "还款日期");
        ValidateUtil.checkDoubleIsNull(bondReqDetail.getPayAmount(), "支付金额");
        ValidateUtil.checkIsNull(bondReqDetail.getPayUsage(), "用途");
        ValidateUtil.checkIsNull(bondReqDetail.getAttribute1(), "是否军民融合部项目");
        /*1、调用保存接口,事务及时提交*/
        bondReqDetail.setPreparedDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        String documentNumber = this.getSelfProxy().saveBondReqDetail(bondReqDetail, cscecUser);
        logger.info("【提示信息】：已保存保证金详情信息！正在提交。。。");
        /*2、获取保证金详情*/

        BondReqDetail new_bondReqDetail1 = this.getBondReqDetail(null, documentNumber);
        /*做一个附件验证  FDM.FDM_BOND_REQS_T*/
        if (new_bondReqDetail1 == null) {
            throw new CommonException("【错误】：获取保证金详情信息失败");
        }
        List<Attachment> attachments = stmCommAttachVDao.getAttachmentList("FDM.FDM_BOND_REQS_T", new_bondReqDetail1.getReqId().toString());
        if (attachments == null || attachments.size() == 0) {
            throw new CommonException("【提示】：提交保证金申请至少需要一个附件");
        }
        /*3、二级单位、招标方验证*/
        Integer orgId = new_bondReqDetail1.getOrganizationId();
        /*二级单位*/
        Integer count = bondConfigApiDao.getBondOrgPayDefByOrgId(orgId);

        if (count <= 0) {
            throw new CommonException("【提示】:请联系关键用户配置支付方式定义。组织名称：" + new_bondReqDetail1.getOrganizationName() + "。");
        }
        if (count > 1) {
            throw new CommonException("【提示】:当前下的组织下有效的‘支付方式定义信息’多于1条。组织名称：" + new_bondReqDetail1.getOrganizationName() + "。");
        }
        List<BondOrgPayDefs> bondOrgPayDefs = null;
        try {
            bondOrgPayDefs = bondConfigApiDao.getSimpleBondOrgPayDefsByOrgId(orgId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：支付信息异常");
        }
         /*添加校验-根据
         1 、备用段的不能为空 backUpSegment，备用段连接查询不能为空 。
         2、部门departmentSect3
         3、noAgentRule为1时 往来段，不为空
        */
        BondOrgPayDefs bpd = bondOrgPayDefs.get(0);
        Boolean validateBackUpSegment = bpd.getBackUpSegment() != null && !"".equals(bpd.getBackUpSegment()) &&
                bpd.getBackUpSegmentDesc() != null && !"".equals(bpd.getBackUpSegmentDesc());
        Boolean validateDepartmentSect = bpd.getDepartmentSect() != null && !"".equals(bpd.getDepartmentSect()) &&
                bpd.getDepartmentDesc() != null && !"".equals(bpd.getDepartmentDesc());
        Boolean validateContactSegmentCode = true;
        if ("1".equals(bpd.getNoAgentRule())) {
            validateContactSegmentCode = bpd.getContactSegmentCode() != null && !"".equals(bpd.getContactSegmentCode()) &&
                    bpd.getContactSegmentDesc() != null && !"".equals(bpd.getContactSegmentDesc());
        }

        if (!validateBackUpSegment && validateDepartmentSect && validateContactSegmentCode) {
            throw new CommonException("【提示信息】：基础支付设置中的备用段未设置，请联系关键用户配置");
        }
        if (validateBackUpSegment && !validateDepartmentSect && validateContactSegmentCode) {
            throw new CommonException("【提示信息】：基础支付设置中的部门段未设置，请联系关键用户配置");
        }
        if (validateBackUpSegment && validateDepartmentSect && !validateContactSegmentCode) {
            throw new CommonException("【提示信息】：基础支付设置中的往来段未设置，请联系关键用户配置");
        }
        if (!validateBackUpSegment && !validateDepartmentSect && validateContactSegmentCode) {
            throw new CommonException("【提示信息】：基础支付设置中的备用段、部门段未设置，请联系关键用户配置");
        }
        if (validateBackUpSegment && (!validateDepartmentSect) && (!validateContactSegmentCode)) {
            throw new CommonException("【提示信息】：基础支付设置中的部门段、往来段未设置，请联系关键用户配置");
        }
        if (!validateBackUpSegment && validateDepartmentSect && !validateContactSegmentCode) {
            throw new CommonException("【提示信息】：基础支付设置中的备用段、往来段未设置，请联系关键用户配置");
        }
        if (!validateBackUpSegment && !validateDepartmentSect && !validateContactSegmentCode) {
            throw new CommonException("【提示信息】：基础支付设置中的备用段、部门段、往来段未设置，请联系关键用户配置");
        }

        /*4、如果是局代收代付，验证局的支付方式数量*/
        if ("BUREAU_AGENT".equals(new_bondReqDetail1.getApplyType())) {
            Integer count2 = bondConfigApiDao.getBondOrgPayDefByOrgId(102);
            if (count2 <= 0) {
                throw new CommonException("【错误】：类型为'局代收代付'的申请单，局总部支付方式定义信息不存在");
            }
        }

        //2020.3.4 kaiye.you 取消流程审批，提交时自动触发后续逻辑
//        try {
//            DocumentOrder documentOrder = new DocumentOrder();
//            //设置申请单主键
//            documentOrder.setSourceId(new_bondReqDetail1.getReqId().toString());
//            //设置table
//            documentOrder.setSourceTable("");
//            //设置类型
//            // 申请 ：  FDM_BOND_REQ
//            //认领:   	FDM_BOND_CLAIM
//            documentOrder.setSourceType("FDM_BOND_REQ");
//            //设置申请单单据编号
//            documentOrder.setDocumentNumber(documentNumber);
//            //设置申请人组织ID
//            if (orgId == null) {
//                orgId = cscecUser.getOrgId();
//            }
//            documentOrder.setOrganizationId(orgId);
//            //设置
//            documentOrder.setCreator(cscecUser.getUserCode());
//            //设置和用户名+工号
//            documentOrder.setCreateName(new_bondReqDetail1.getPreparedByName());
//
//            documentOrder.setUserId(cscecUser.getUserId());
//            SubmitProcessRequest submitMaterailVo = new SubmitProcessRequest();
//            submitMaterailVo.setDocumentOrder(documentOrder);
//            submitMaterailVo.setFirstNodeAssignees("");
//            submitMaterailVo.setNeedCalculateFirstNodeApprover("Y");
//            SubmitProcessResponse rpcResult = null;
//            System.out.println(JSON.toJSONString(submitMaterailVo));
//            rpcResult = submitProcessRequestService.submitReq(submitMaterailVo);
//            logger.info("【提交结果】" + rpcResult.getErrorMsg().get("$"));
//            if (!"Y".equals(rpcResult.getResultFlag().get("$"))) {
//                //  throw new CommonException("【错误信息】：" + rpcResult.getErrorMsg().get("$"));
//                return ResponseBodyUtil.getFailedMsg("【错误信息】：远程接口服务超时,请联系管理员", "【错误】：" + rpcResult.getErrorMsg().get("$"));
//            }
//        } catch (CommonException e) {
//            throw new CommonException(e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info("【错误】:保证金信息已保存，远程提交保证金申请单异常！ 单据号；" + documentNumber);
//            throw new CommonException("【错误】:保证金信息已保存，远程提交保证金申请单异常！单据号：" + documentNumber);
//        }
//        /*3、跟新状态为SUBMITED*/
//        try {
//            new_bondReqDetail1.setStatus("SUBMITED");
//            bondReqApiDao.updateBondReqDetailStatus(new_bondReqDetail1);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info("【错误】:远程提交保证金申请单成功！本地修改状态失败，单据号；" + documentNumber);
//        }
        Map map = new HashMap();
        map.put("reqId", bondReqDetail.getReqId());
        bondReqApiDao.callProcessComplete(map);
        if (map.get("resultFlag")==null){
            return ResponseBodyUtil.getFailedMsg("【错误信息】：远程接口服务异常", "【错误】未获取到返回信息");
        } else if ("N".equals(map.get("resultFlag").toString())){
            String errMsg = map.get("errMsg").toString();
            return ResponseBodyUtil.getFailedMsg("【错误信息】：远程接口服务异常", "【错误】"+errMsg);
        } else {
            new_bondReqDetail1.setStatus("APPROVED");
            bondReqApiDao.updateBondReqDetailStatus(new_bondReqDetail1);
            return ResponseBodyUtil.getSuccessRes(documentNumber);
        }

    }

    /**
     * 获取保证金列表
     *
     * @param bondReq
     * @param page
     * @param size
     * @return
     */
    @Override
    public PagerVo getBondReqList(BondReq bondReq, Integer page, Integer size) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));

        PagerVo pagerVo = new PagerVo();
        pagerVo.setPage(page);
        pagerVo.setSize(size);
        List<BondReq> bondReqList = new ArrayList<BondReq>();

        /*是否包含下级*/
        Boolean containChild = false;
        if ("Y".equals(bondReq.getContainChild())) {
            containChild = true;
        }
        try {
            bondReqList = bondReqApiDao.getBondReqListBySearchValue(bondReq, cscecUser, (page - 1) * size, page * size, containChild);
            if (bondReqList.size() > 0) {
                pagerVo.setTotal(bondReqList.get(0).getTotal());

                /*设置认领单*/
                for (int i = 0; i < bondReqList.size(); i++) {
                    BondReq br = bondReqList.get(i);
                    /*1/设置状态*/
                    String changStatus = bondReqApiDao.getChangeStatus(br.getReqId());
                    if (changStatus != null) {
                        br.setChangeStatus(changStatus);
                    }
                    /*2、获取是否存在认领单*/
                    String claimDocumentNumber = null;
                    if (br.getReqId() != null) {
                        claimDocumentNumber = bondClaimApiDao.getBondClaimDetailDocumentNumber(br.getReqId());
                    }
                    if (claimDocumentNumber != null) {
                        br.setClaimDocumentNumber(claimDocumentNumber);
                    }
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：查询保证金申请列表异常");
        }
        pagerVo.setList(bondReqList);
        return pagerVo;
    }

    /**
     * 根据主键删除保证金申请单
     *
     * @param reqId
     * @return
     */
    @Override
    @Transactional(transactionManager = "TransactionManager_one")
    public Integer delBondReqDetail(Integer reqId) {
        if (RequestBodyUtil.isEmpty(reqId)) {
            throw new CommonException("【错误】: 申请单主键不能为空 ！");
        }
        int result = 0;
        try {
            //删除状态为草稿"NEW" 的和拒绝"REJECTED"的
            result = bondReqApiDao.delBondReqDetail(reqId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】:删除供应商申请单异常");
        }
        return result;

    }

    /**
     * 获取变更记录
     *
     * @param reqId
     * @return
     */
    @Override
    public List<BondChange> getBondChangeRecords(Integer reqId) {
        ValidateUtil.checkIntegerIsNull(reqId, "reqId");
        //查询变更头记录
        List<BondChange> bondChanges = new ArrayList<>();
        try {
            bondChanges = bondReqApiDao.getBondChangeByReqId(reqId);
            /*获取变更历史记录中*/
            if (bondChanges.size() <= 0) {
                logger.info("【提示】：不存在变更记录");
                return bondChanges;
            }
            //遍历变更记录头
            // 变更模式后，只有“约定还款日期” “用途” “开户银行帐号” “开户单位名称” “开户银行名称”支持修改。
            for (int i = 0; i < bondChanges.size(); i++) {
                /*设置最新的便跟审批人*/
                BondChange bondChange = bondChanges.get(i);
                List<ApprovalHistory> approvalHistories = null;
                approvalHistories = stmBpmHistoryVService.getApprovalHistioryList("FDM.FDM_BOND_CHANGE_HEADER_T", bondChange.getHeaderId().toString());
                if (approvalHistories != null && approvalHistories.size() > 0) {
                    String approver = approvalHistories.get(approvalHistories.size() - 1).getHistoryApprover();
                    bondChange.setApprover(approver == null ? "" : approver);
                }
                //设置变更详情
                List<BondChangeDetail> changeDetails = new ArrayList<>();
                Integer headerId = bondChanges.get(i).getHeaderId();
                changeDetails = bondReqApiDao.getBondChangeDetailsByHeaderId(headerId);
                bondChange.setBondChangeDetails(changeDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：获取保证金列表异常");
        }
        return bondChanges;
    }


    /**
     * 获取经办人列表
     *
     * @param deptName
     * @param cscecUser
     * @return
     */
    @Override
    public List<Operator> getOperators(String deptName, CSCECUser cscecUser) {
        ValidateUtil.checkIsNull(deptName, "部门名称");
        List<Operator> operators = new ArrayList<>();
        try {
            operators = bondReqApiDao.getOperatorsLikeDeptName(deptName, cscecUser.getOrgId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：获取" + deptName + "人员列表异常！");
        }
        return operators;
    }

    /**
     * 发起保证金申请变更
     *
     * @param
     */
    @Override
    @Transactional(transactionManager = "TransactionManager_one")
    public ResultBean changeBondReqDetail(Integer reqId, String commons, List<BondChangeDetail> bondChangeDetails) {
        ValidateUtil.checkIntegerIsNull(reqId, "reqId");
        //验证修改信息
        if (bondChangeDetails == null || bondChangeDetails.size() <= 0) {
            throw new CommonException("【错误】：变更内容不能为空");
        }
        /*原来的保证金申请单*/
        BondReqDetail oldBondReqDetail = bondReqApiDao.getBondReqDetailByReqIdOrDocumentNumber(reqId, null);
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        //变更申请单头表的id
        Integer headerId = null;
        try {
            headerId = this.getSelfProxy().updateBondChange(reqId, commons, cscecUser, bondChangeDetails);
        } catch (CommonException e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：修改保证金信息异常，暂时无法提交");
        }
        // 发起流程
        //获取修改后的保证金详情
        BondReqDetail new_bondReqDetail = null;
        try {
            new_bondReqDetail = this.getBondReqDetail(reqId, null);
            DocumentOrder documentOrder = new DocumentOrder();
            //设置物料申请单主键
            documentOrder.setSourceId(headerId.toString());
            //设置table
            documentOrder.setSourceTable("");
            //设置类型
            documentOrder.setSourceType("FDM_BOND_REQ_CHANGE");
            //设置申请单单据编号
            documentOrder.setDocumentNumber(new_bondReqDetail.getDocumentNumber());
            //设置申请人组织ID
            Integer orgId = oldBondReqDetail.getOrganizationId();
            if (orgId == null) {
                orgId = cscecUser.getOrgId();
            }
            documentOrder.setOrganizationId(orgId);
            //设置
            documentOrder.setCreator(cscecUser.getUserCode());
            //设置和用户名+工号
            documentOrder.setCreateName(new_bondReqDetail.getPreparedByName());
            documentOrder.setUserId(cscecUser.getUserId());
            SubmitProcessRequest submitMaterailVo = new SubmitProcessRequest();
            submitMaterailVo.setDocumentOrder(documentOrder);
            submitMaterailVo.setFirstNodeAssignees("");
            submitMaterailVo.setNeedCalculateFirstNodeApprover("Y");
            SubmitProcessResponse rpcResult = null;
            logger.info("--变更传送参数--" + JSON.toJSONString(submitMaterailVo));
            rpcResult = submitProcessRequestService.submitReq(submitMaterailVo);
            logger.info("【提交结果】" + rpcResult.getErrorMsg().get("$"));
            if (!"Y".equals(rpcResult.getResultFlag().get("$"))) {
                /*1、回退申请单*/
                bondReqApiDao.changeBondReqDetail(oldBondReqDetail, cscecUser);
                /*2删除插入的变更头*/
                if (headerId != null) {
                    bondReqApiDao.delBondChangeByHeaderId(headerId);
                }
                return ResponseBodyUtil.getFailedMsg("【提交结果】:远程接口服务超时,请联系管理员", "【远程接口信息】：" + rpcResult.getErrorMsg().get("$"));
            }
        } catch (Exception e) {
            bondReqApiDao.changeBondReqDetail(oldBondReqDetail, cscecUser);
            /*2删除插入的变更头*/
            if (headerId != null) {
                bondReqApiDao.delBondChangeByHeaderId(headerId);
            }
            e.printStackTrace();
            logger.info("【错误】:保证金变更信息已保存，远程提交保证金申请单异常！ 单据号；" + new_bondReqDetail.getDocumentNumber());
            return ResponseBodyUtil.getFailedRes("【错误信息】:保证金变更信息已保存，远程提交保证金申请单异常！ 请联系管理员");
        }
        //添加保证金变更的审批人
        List<ApprovalHistory> approvalHistories = null;
        BondChange bondChange = new BondChange();
        bondChange.setHeaderId(headerId);
        try {
            approvalHistories = stmBpmHistoryVService.getApprovalHistioryList("FDM.FDM_BOND_CHANGE_HEADER_T", bondChange.getHeaderId().toString());
            if (approvalHistories != null && approvalHistories.size() > 0) {
                bondChange.setApprover(approvalHistories.get(approvalHistories.size() - 1).getHistoryApprover());
            }
            bondChange.setStatus("SUBMITED");
            bondReqApiDao.updateBondChangeDetail(bondChange, cscecUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBodyUtil.getFailedRes("【错误信息】:更新审批人失败,请联系管理员");
        }
        return ResponseBodyUtil.Success();
    }

    /**
     * 新增修改信息
     *
     * @param reqId
     * @param commons
     * @param cscecUser
     * @param bondChangeDetails
     * @return
     */
    @Override
    @Transactional(transactionManager = "TransactionManager_one", propagation = Propagation.REQUIRES_NEW)
    public Integer updateBondChange(Integer reqId, String commons, CSCECUser cscecUser, List<BondChangeDetail> bondChangeDetails) {
        BeanInfo beanInfo = null;
        Integer lineId = null;
        Integer headerId = null;
        BondChange bondChange = new BondChange();
        BondReqDetail bondReqDetail = new BondReqDetail();
        /*1、验证是否在存在变更中*/
        BondChange oldBondChange = bondReqApiDao.getBondChangeByStatus(reqId);
        if (oldBondChange != null) {
            throw new CommonException("【错误】：当前单据已经提交变更，不能重复提交变更申请");
        }
        try {
            /*2、获取前端传来的值，并把值赋给相应的属性*/
            beanInfo = Introspector.getBeanInfo(BondReqDetail.class, Object.class);
            //2.1获取所有的属性集合
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            //2.2赋值变更数据
            // 变更模式后，只有 约定还款日期、 用途、开户银行帐号、开户单位名称、 开户银行名称，支持修改  支付类型。
            for (int i = 0; i < bondChangeDetails.size(); i++) {
                //获取集合的属性名字
                String filedName = bondChangeDetails.get(i).getColumnName();
                //获取集合属性的值
                Object fileValue = bondChangeDetails.get(i).getColumnTargetValue();
                for (PropertyDescriptor pd : pds) {
                    //javaBean的属性名称
                    String propertyName = pd.getName();
                    //获取setter方法
                    Method setter = pd.getWriteMethod();
                    if (filedName.equals(propertyName)) {
                        setter.invoke(bondReqDetail, fileValue);
                    }
                }
            }
            //3修改保证金的信息
            //获取当前用户
            bondReqDetail.setReqId(reqId);
            bondReqApiDao.changeBondReqDetail(bondReqDetail, cscecUser);
            //4新增变更头记录
            //4.1 获取变更的主键
            headerId = bondReqApiDao.getBondChangeNextVal();
            bondChange.setHeaderId(headerId);
            bondChange.setReqId(reqId);
            bondChange.setComments(commons);
            bondChange.setStatus("NEW");
            //4.2插入变更头
            bondReqApiDao.insertBondChange(bondChange, cscecUser);
            for (int i = 0; i < bondChangeDetails.size(); i++) {
                BondChangeDetail bondChangeDetail = bondChangeDetails.get(i);
                //2.2插入变更行的详情记录记录
                lineId = bondReqApiDao.getBondChangeDetailNextVal();
                /*设置为变更头的主键*/
                bondChangeDetail.setHeaderId(headerId);
                bondChangeDetail.setLineId(lineId);
                /*驼峰转换为大写*/
                String columnName = bondChangeDetail.getColumnName();
                if ("repaymentDate".equals(columnName)) {
                    bondChangeDetail.setColumnName("REPAYMENT_DATE");
                    bondChangeDetail.setColumnType("date");
                } else if ("payUsage".equals(columnName)) {
                    bondChangeDetail.setColumnName("PAY_USAGE");
                } else if ("tendereeAccountName".equals(columnName)) {
                    bondChangeDetail.setColumnName("TENDEREE_ACCOUNT_NAME");
                } else if ("tendereeAccountBankName".equals(columnName)) {
                    bondChangeDetail.setColumnName("TENDEREE_ACCOUNT_BANK_NAME");
                } else if ("tendereeAccountBankNumber".equals(columnName)) {
                    bondChangeDetail.setColumnName("TENDEREE_ACCOUNT_BANK_NUMBER");
                } else if ("bondType".equals(columnName)) {
                    bondChangeDetail.setColumnName("BOND_TYPE");
                } else if ("transferFlag".equals(columnName)) {
                    bondChangeDetail.setColumnName("TRANSFER_FLAG");
                } else if ("tendereeBankType".equals(columnName)) {
                    bondChangeDetail.setColumnName("TENDEREE_BANK_TYPE");
                } else if ("tendereeProvince".equals(columnName)) {
                    bondChangeDetail.setColumnName("TENDEREE_PROVINCE");
                } else if ("tendereeCity".equals(columnName)) {
                    bondChangeDetail.setColumnName("TENDEREE_CITY");
                } else if ("payType".equals(columnName)) {
                    bondChangeDetail.setColumnName("PAY_TYPE");
                }
                /*设置原值和目标值的意义*/
                String ColumnSourceValue = bondChangeDetail.getColumnSourceValue() == null ? "" : bondChangeDetail.getColumnSourceValue();
                String ColumnTargetValue = bondChangeDetail.getColumnTargetValue() == null ? "" : bondChangeDetail.getColumnTargetValue();
                if ("bondType".equals(columnName)) {
                    if ("TENDER_BOND".equals(ColumnSourceValue)) {
                        bondChangeDetail.setColumnSourceValueDesc("投标保证金");
                    } else if ("PERFORMANCE_BOND".equals(ColumnSourceValue)) {
                        bondChangeDetail.setColumnSourceValueDesc("履约保证金");
                    } else if ("MIGRANT_BOND".equals(ColumnSourceValue)) {
                        bondChangeDetail.setColumnSourceValueDesc("农民工保证金");
                    } else {
                        bondChangeDetail.setColumnSourceValueDesc(ColumnSourceValue);
                    }
                    if ("TENDER_BOND".equals(ColumnTargetValue)) {
                        bondChangeDetail.setColumnTargetValueDesc("投标保证金");
                    } else if ("PERFORMANCE_BOND".equals(ColumnTargetValue)) {
                        bondChangeDetail.setColumnTargetValueDesc("履约保证金");
                    } else if ("MIGRANT_BOND".equals(ColumnTargetValue)) {
                        bondChangeDetail.setColumnTargetValueDesc("农民工保证金");
                    } else {
                        bondChangeDetail.setColumnTargetValueDesc(ColumnTargetValue);
                    }
                } else if ("tendereeBankType".equals(columnName)) {
                    /*根据type获取到名称*/
                    LookUpRecord lookUpRecord = bondReqApiDao.getBondBankTypeName(ColumnSourceValue);
                    if (lookUpRecord != null) {
                        bondChangeDetail.setColumnSourceValueDesc(lookUpRecord.getLookUpValue() == null ? ColumnSourceValue : lookUpRecord.getLookUpValue());
                    }
                    LookUpRecord lookUpRecord2 = bondReqApiDao.getBondBankTypeName(ColumnTargetValue);
                    if (lookUpRecord2 != null) {
                        bondChangeDetail.setColumnTargetValueDesc(lookUpRecord2.getLookUpValue() == null ? ColumnTargetValue : lookUpRecord2.getLookUpValue());
                    }
                } else if ("payType".equals(columnName)) {
                    if ("OTHER".equals(ColumnSourceValue)) {
                        bondChangeDetail.setColumnSourceValueDesc("其他");
                    } else {
                        bondChangeDetail.setColumnSourceValueDesc(ColumnSourceValue);
                    }
                    if ("OTHER".equals(ColumnTargetValue)) {
                        bondChangeDetail.setColumnTargetValueDesc("其他");
                    } else {
                        bondChangeDetail.setColumnTargetValueDesc(ColumnTargetValue);
                    }
                } else {
                    bondChangeDetail.setColumnSourceValueDesc(ColumnSourceValue);
                    bondChangeDetail.setColumnTargetValueDesc(ColumnTargetValue);
                }
                bondReqApiDao.insertBondChangeDetail(bondChangeDetail, cscecUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：修改保证金信息失败");
        }
        return headerId;
    }

    /**
     * 修改保证金类型状态
     *
     * @param reqId
     * @return
     */
    @Override
    public Integer UpdateBondreqId(Integer reqId) {
        Integer result = 0;
        try {
            result = bondReqApiDao.UpdateBondreqId(reqId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】:修改保证金类型异常");
        }
        return result;
    }

    /**
     * 财务经办人和市场经办人查询接口
     *
     * @param bondPayment
     * @return
     */
    @Override
    public List getOrgUser(BondPayment bondPayment, Integer orgId) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        orgId = cscecUser.getOrgId();
        List<BondPayment> bond = new ArrayList<BondPayment>();
        try {
            bond = bondReqApiDao.getOrgUser(bondPayment, cscecUser, orgId);
            if (bond.size() > 0) {
                return bond;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：查询财务经办人和市场经办人异常！");
        }
        return bond;
    }
}
