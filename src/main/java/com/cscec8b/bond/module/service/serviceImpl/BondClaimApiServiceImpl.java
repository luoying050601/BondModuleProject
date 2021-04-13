package com.cscec8b.bond.module.service.serviceImpl;

import com.cscec8b.bond.module.object.*;
import com.cscec8b.bond.module.service.BondClaimApiService;
import com.cscec8b.common.module.object.*;
import com.cscec8b.common.module.object.exceptionObject.CommonException;
import com.cscec8b.common.module.util.ResponseBodyUtil;
import com.cscec8b.common.module.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BondClaimApiServiceImpl extends BaseServiceImpl implements BondClaimApiService {

    Logger logger = LoggerFactory.getLogger(BondClaimApiServiceImpl.class);


    @PostConstruct
    public BondClaimApiService getSelfProxy() {
        return applicationContext.getBean(BondClaimApiService.class);
    }


    /**
     * 分页多字段 查询保证金认领概要信息列表
     *
     * @param bondClaimSimple
     * @param page
     * @param size
     * @return
     */
    @Override
    public PagerVo<BondClaimSimple> getBondClaimList(BondClaimSimple bondClaimSimple, Integer page, Integer size) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));

        PagerVo pagerVo = new PagerVo();
        pagerVo.setPage(page);
        pagerVo.setSize(size);
        List<BondClaimSimple> bondClaimSimples = new ArrayList<>();
        /*是否包含下级*/
        Boolean containChild = false;
        if ("Y".equals(bondClaimSimple.getContainChild())) {
            containChild = true;
        }
        try {
            bondClaimSimples = bondClaimApiDao.getBondClaimListBySearchValue((page - 1) * size, page * size, bondClaimSimple, cscecUser, containChild);
            if (bondClaimSimples.size() > 0) {
                pagerVo.setTotal(bondClaimSimples.get(0).getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：查询保证金认领列表异常！");
        }
        pagerVo.setList(bondClaimSimples);
        return pagerVo;
    }

    /**
     * 根据认领单据号查询详情
     *
     * @param documentNumber
     * @return
     */
    @Override
    public BondClaimDetail getBondClaimDetail(String documentNumber) {
        ValidateUtil.checkIsNull(documentNumber, "documentNumber");
        BondClaimDetail bondClaimDetail = null;
        try {
            //1获取保证金认领详情
            bondClaimDetail = bondClaimApiDao.getBondClaimDetailByDocumentNumber(documentNumber);
            //2获取流水的行信息
            if (bondClaimDetail != null) {
                Integer headerId = bondClaimDetail.getHeaderId();
                List<BondClaimLine> bondClaimLines = new ArrayList<BondClaimLine>();
                bondClaimLines = bondClaimApiDao.getBondClaimLineByHeaderId(headerId);
                bondClaimDetail.setBondClaimLines(bondClaimLines);
                /*赋值 退款信息*/
                if (bondClaimLines.size() > 0) {
                    bondClaimDetail.setRepaymentDate(bondClaimLines.get(0).getTransDatetime());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：查询保证金认领单详情异常！");
        }
        return bondClaimDetail;
    }

    /**
     * 保存保证金认领单
     *
     * @param bondClaimDetail
     * @return
     */
    @Override
    @Transactional( transactionManager = "TransactionManager_one",propagation = Propagation.REQUIRES_NEW)
    public String saveBondClaimDetail(BondClaimDetail bondClaimDetail) {
        ValidateUtil.checkIntegerIsNull(bondClaimDetail.getReqId(), "reqId");
        ValidateUtil.checkIsNull(bondClaimDetail.getPreparedDate(), "preparedDate");
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        if (bondClaimDetail.getOrganizationId() == null) {
            bondClaimDetail.setOrganizationId(cscecUser.getOrgId());
        }
        String result = null;
        Integer headerId = null;
        List<BondClaimLine> bondClaimLines = bondClaimDetail.getBondClaimLines();
        List<Integer> delBondClaimLinesId = bondClaimDetail.getDelBondClaimLinesId();
        //1、验证：流水号不能重复
        if (bondClaimLines != null && bondClaimLines.size() > 0) {
            for (BondClaimLine bondClaimLine : bondClaimLines) {
                // Ky:由流水号不能重复改成transId不能重复
                String transId = bondClaimLine.getTransId();
                int count = 0;
                if (transId != null && !"".equals(transId)) {
                    for (BondClaimLine bondClaimLine2 : bondClaimLines) {
                        if (transId.equals(bondClaimLine2.getTransId())) {
                            count = count + 1;
                        }
                    }
                    if (count > 1) {
                        throw new CommonException("【错误】 流水重复！流水单号：" + bondClaimLine.getBankSeqNumber());
                    }
                }
            }

        }
        //2、保存保证金认领
        try {
            boolean BOO = (bondClaimDetail.getDocumentNumber() == null || "".equals(bondClaimDetail.getDocumentNumber()))
                    && bondClaimDetail.getHeaderId() == null;
            /*赋值行上的*/
            if (bondClaimLines != null && bondClaimLines.size() > 0) {
                bondClaimDetail.setRepaymentTenderee(bondClaimLines.get(0).getAccountBankName());
                bondClaimDetail.setRepaymentBankNumber(bondClaimLines.get(0).getAccountBankNumber());
            }
            if (BOO) {
                //获取主键和单据号
                Map map = new HashMap();
                map.put("docType", "FDM_BOND_CLAIM");
                map.put("orgId", cscecUser.getOrgId());
                bondReqApiDao.callGetDocNumber(map);
                result = map.get("docNum").toString();
                bondClaimDetail.setDocumentNumber(result);
                headerId = bondClaimApiDao.getNextval();
                bondClaimDetail.setHeaderId(headerId);
                bondClaimDetail.setStatus("NEW");
                //新建保证金头信息
                bondClaimApiDao.insertBondClaimDetail(bondClaimDetail, cscecUser);
            } else {
                //更新保证头信息 修改的时候headerId
                if (StringUtils.isEmpty(bondClaimDetail.getStatus())) {
                    bondClaimDetail.setStatus("NEW");
                }
                bondClaimApiDao.updateBondClaimDetail(bondClaimDetail, cscecUser);
                result = bondClaimDetail.getDocumentNumber();
                headerId = bondClaimDetail.getHeaderId();
            }
            ///3、保存保证金行列表
            //判断是插入还是修改
            if (bondClaimLines != null && bondClaimLines.size() > 0) {
                for (int i = 0; i < bondClaimLines.size(); i++) {
                    BondClaimLine bondClaimLine = bondClaimLines.get(i);
                    bondClaimLine.setHeaderId(headerId);
                    Boolean bll = bondClaimLine.getLineId() == null;
                    if (bll) {
                        Integer lineId = bondClaimApiDao.getBondClaimLineNextval();
                        bondClaimLine.setLineId(lineId);
                        bondClaimApiDao.insertBondClaimLine(bondClaimLine, cscecUser);
                    } else {
                        bondClaimApiDao.updateBondClaimLine(bondClaimLine, cscecUser);
                    }
                }
            }
            ///4、删除保证金认领 行 列表
            if (delBondClaimLinesId != null && delBondClaimLinesId.size() > 0) {
                for (int j = 0; j < delBondClaimLinesId.size(); j++) {
                    bondClaimApiDao.delBondClaimLineByLineId(delBondClaimLinesId.get(j));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：保存保证金认领单异常！");
        }
        return result;
    }

    /**
     * 完成认领接口
     *
     * @param bondClaimDetail
     * @return
     */
    @Override
    public String completeBondClaimDetail(BondClaimDetail bondClaimDetail) {
        //验证 不能有流水  没有开户单位信息的
        ValidateUtil.checkIntegerIsNull(bondClaimDetail.getReqId(), "reqId");
        BondReqDetail bondReqDetail = bondReqApiDao.getBondReqDetailByReqIdOrDocumentNumber(bondClaimDetail.getReqId(), null);
        if (bondReqDetail == null) {
            throw new CommonException("[错误]：认领的申请单不存在");
        }
        String claimDocumentNumber = bondClaimApiDao.getBondClaimDetailDocumentNumber(bondClaimDetail.getReqId());
        if (claimDocumentNumber != null) {
            throw new CommonException("【提示】：当前认领单对应的申请单已被认领或者处于认领状态中，请核实！");
        }
        ValidateUtil.checkIsNull(bondClaimDetail.getPreparedDate(), "preparedDate");
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        List<BondClaimLine> bondClaimLines = bondClaimDetail.getBondClaimLines();
        if (bondClaimLines != null && bondClaimLines.size() > 0) {
            throw new CommonException("[错误]：完成认领功能无需选择流水");
        }
        String result = null;
        Integer headerId = null;
        try {
            boolean BOO = (bondClaimDetail.getDocumentNumber() == null || "".equals(bondClaimDetail.getDocumentNumber()))
                    && bondClaimDetail.getHeaderId() == null;
            if (BOO) {
                Map map = new HashMap();
                map.put("docType", "FDM_BOND_CLAIM");
                map.put("orgId", cscecUser.getOrgId());
                bondReqApiDao.callGetDocNumber(map);
                result = map.get("docNum").toString();
                bondClaimDetail.setDocumentNumber(result);
                headerId = bondClaimApiDao.getNextval();
                bondClaimDetail.setHeaderId(headerId);
                bondClaimDetail.setStatus("APPROVED");
                //新建保证金头信息
                bondClaimApiDao.insertBondClaimDetail(bondClaimDetail, cscecUser);
            } else {
                //更新保证头信息 修改的时候headerId
                bondClaimDetail.setStatus("APPROVED");
                bondClaimApiDao.updateBondClaimDetail(bondClaimDetail, cscecUser);
                result = bondClaimDetail.getDocumentNumber();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：完成保证金认领单异常！");
        }
        return result;
    }

    /**
     * 认领提交
     *
     * @param bondClaimDetail
     * @return
     */
    @Override
    @Transactional(transactionManager = "TransactionManager_one")
    public ResultBean submitBondClaimDetail(BondClaimDetail bondClaimDetail) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        //1、验证字段信息*/

        ValidateUtil.checkIsNull(bondClaimDetail.getReqDocumentNumber(), "保证金申请单据号");
        ValidateUtil.checkIntegerIsNull(bondClaimDetail.getReqId(), "保证金申请单主键");
        String claimDocumentNumber = null;
        try {
            /*验证申请单是否已经提交或审批通过*/
            claimDocumentNumber = bondClaimApiDao.getBondClaimDetailDocumentNumber(bondClaimDetail.getReqId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：验证认领单对应的申请单信息出错！");
        }
        /*如果存在*/
        if (claimDocumentNumber != null) {
            throw new CommonException("【提示】：当前认领单对应的申请单已被认领或者处于认领状态中，请核实！");
        }

        if (bondClaimDetail.getBondClaimLines() == null || bondClaimDetail.getBondClaimLines().size() == 0) {
            throw new CommonException("【错误】：保证金认领流水不能为空！");
        }
        /*2、保存信息，保存后事务自动提交*/
        String documentNumber = this.getSelfProxy().saveBondClaimDetail(bondClaimDetail);
        /*3、获取保证认领详情信息*/
        BondClaimDetail newBondClaimDetail = null;
        try {
            newBondClaimDetail = this.getBondClaimDetail(documentNumber);
            /*4、获取并验证流水号,通过金额倒序*/
            List<BondClaimLine> bondClaimLines = newBondClaimDetail.getBondClaimLines();
            for (int i = 0; i < bondClaimLines.size(); i++) {
                String bankSeqNumber = bondClaimLines.get(i).getBankSeqNumber();
                String transId = bondClaimLines.get(i).getTransId();
                /*获取流水是否存在   只能获取到一条*/
                List<CBSStatement> cbsStatements = bondLedgerApiDao.getCBSStatementByTransId(transId);
                if (cbsStatements == null || cbsStatements.size() <= 0) {
                    throw new CommonException("【错误】：银行流水号：" + bankSeqNumber + "，不存在，或者已被认领，请确认！");
                }
            }

            //获取保证金的申请类型
            String applyType = newBondClaimDetail.getApplyType();
            /*如果是非局代收代付 直接调用接口生成凭证记录 */
            if ("NOT_BUREAU_AGENT".equals(applyType)) {
                Map map = new HashMap();
                /*认领表的主键id  和流水的id*/
                /*根据主键获取cbs支付单据主键*/
                Integer headerId = newBondClaimDetail.getHeaderId();
                /*获取金额最大的流水对应的transId*/
                String transId = bondClaimLines.get(0).getTransId();
                map.put("p_source_id", headerId);
                map.put("p_cbs_trans_id", transId);
                bondClaimApiDao.InsertBondErpByTrans(map);
                /*修改状态为审批过，返回成功*/
                newBondClaimDetail.setStatus("APPROVED");
                this.updateBondClaimDetailStatus(newBondClaimDetail);
                //返回退出
                return ResponseBodyUtil.getSuccessRes(documentNumber);
            }
            /*提交保证认领信息*/
            DocumentOrder documentOrder = new DocumentOrder();
            //设置申请单主键
            documentOrder.setSourceId(newBondClaimDetail.getHeaderId().toString());
            //设置table
            documentOrder.setSourceTable("");
            //设置类型
            // 申请 ：  FDM_BOND_REQ
            //认领:   	FDM_BOND_CLAIM
            documentOrder.setSourceType("FDM_BOND_CLAIM");
            //设置申请单单据编号
            documentOrder.setDocumentNumber(newBondClaimDetail.getDocumentNumber());
            //设置申请人组织ID
            Integer orgId = newBondClaimDetail.getOrganizationId();
            if (orgId == null) {
                orgId = cscecUser.getOrgId();
            }
            documentOrder.setOrganizationId(orgId);
            //设置
            documentOrder.setCreator(cscecUser.getUserCode());
            //设置和用户名+工号
            documentOrder.setCreateName(newBondClaimDetail.getPreparedByName());
            documentOrder.setUserId(cscecUser.getUserId());
            SubmitProcessRequest submitMaterailVo = new SubmitProcessRequest();
            submitMaterailVo.setDocumentOrder(documentOrder);
            submitMaterailVo.setFirstNodeAssignees("");
            submitMaterailVo.setNeedCalculateFirstNodeApprover("Y");
            SubmitProcessResponse rpcResult = null;
            rpcResult = submitProcessRequestService.submitReq(submitMaterailVo);
            logger.info("【提交结果】：" + rpcResult.getErrorMsg().get("$"));
            if (!"Y".equals(rpcResult.getResultFlag().get("$"))) {
                // throw new CommonException("【错误信息】：" + rpcResult.getErrorMsg().get("$"));
                return ResponseBodyUtil.getFailedMsg("【错误信息】：远程接口服务超时,请联系管理员", "【错误】：" + rpcResult.getErrorMsg().get("$"));
            }

        } catch (CommonException e) {
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("【错误】:保证金认领信息已保存，远程提交保证金认领申请单异常！ 单据号；" + newBondClaimDetail.getDocumentNumber());
            return ResponseBodyUtil.getFailedRes("【错误信息】:保证金认领信息已保存，远程提交保证金认领申请单异常! 请联系管理员");
        }
        /*4修改状态信息，处理异常*/
        try {
            newBondClaimDetail.setStatus("SUBMITED");
            bondClaimApiDao.updateBondClaimDetailStatus(newBondClaimDetail);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("【提示】:保证金认领信息已提交，修改保证金认领申请单状态异常！ 单据号；" + newBondClaimDetail.getDocumentNumber());
        }
        return ResponseBodyUtil.getSuccessRes(documentNumber);
    }


    /**
     * 修改状态
     *
     * @param bondClaimDetail
     */
    private void updateBondClaimDetailStatus(BondClaimDetail bondClaimDetail) {

        bondClaimApiDao.updateBondClaimDetailStatus(bondClaimDetail);

    }


    /**
     * 删除保证金的详情信息
     *
     * @param headerId
     * @return
     */
    @Override
    @Transactional( transactionManager = "TransactionManager_one")
    public Integer delBondClaimDetail(Integer headerId) {
        ValidateUtil.checkIntegerIsNull(headerId, "headerId");
        Integer result = null;
        try {
            //1 删除保证金详情 头
            result = bondClaimApiDao.delBondClaimDetail(headerId);
            //2 删除保证金详情 行
            bondClaimApiDao.delBondClaimLineByHeaderId(headerId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：删除保证金信息异常！");
        }
        return result;
    }


}
