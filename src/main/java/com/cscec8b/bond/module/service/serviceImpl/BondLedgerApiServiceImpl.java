package com.cscec8b.bond.module.service.serviceImpl;


import com.alibaba.fastjson.JSON;
import com.cscec8b.bond.module.object.*;
import com.cscec8b.bond.module.service.BondLedgerApiService;
import com.cscec8b.common.module.object.CSCECUser;
import com.cscec8b.common.module.object.PagerVo;
import com.cscec8b.common.module.object.ResultBean;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BondLedgerApiServiceImpl extends BaseServiceImpl implements BondLedgerApiService {
    Logger logger = LoggerFactory.getLogger(BondLedgerApiServiceImpl.class);

    @PostConstruct
    public BondLedgerApiService getSelfProxy() {
        return applicationContext.getBean(BondLedgerApiService.class);
    }

    /**
     * 查询ERP凭证列表
     *
     * @param erpCft
     * @param page
     * @param size
     * @return
     */
    @Override
    public PagerVo<ERPCft> getErpCftList(ERPCft erpCft, Integer page, Integer size) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        PagerVo pagerVo = new PagerVo();
        pagerVo.setPage(page);
        pagerVo.setSize(size);
        List<ERPCft> erpCftList = new ArrayList<>();
        try {
            erpCftList = bondLedgerApiDao.getErpCftList(erpCft, cscecUser, (page - 1) * size, page * size);
            if (erpCftList.size() > 0) {
                pagerVo.setTotal(erpCftList.get(0).getTotal());
            }
            pagerVo.setList(erpCftList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("【错误】：查询ERP凭证列表异常！");
            throw new CommonException("【错误】：查询ERP凭证列表异常！");
        }
        return pagerVo;

    }


    /**
     * 获取erp详情的信息
     *
     * @param header_id
     * @return
     */
    @Override
    public ERPCftDetail getErpCftDetail(Integer header_id) {
        ERPCftDetail erpCftDetail = null;
        List<ErpLine> erpLines = new ArrayList<>();
        try {
            erpCftDetail = bondLedgerApiDao.getErpCftDetailByErpId(header_id);
            if (erpCftDetail == null) {
                throw new CommonException("【提示】:不存在headerId为：" + header_id + "的ERP记录详情！");
            }
            Integer headerId = erpCftDetail.getHeaderId();
            erpLines = bondLedgerApiDao.getSimpleErpLineListByHeaderId(headerId);

        } catch (CommonException e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("【错误】：获取ERP详情异常！");
            throw new CommonException("【错误】：获取ERP详情异常！");
        }
        erpCftDetail.setErpLines(erpLines);
        return erpCftDetail;
    }

    /**
     * 重新传送接口
     *
     * @param headerIds
     */
    @Override
    @Transactional(transactionManager = "TransactionManager_one")
    public ResultBean retransferErpCft(List<Integer> headerIds, String JournalName, String glDate) {
        if (headerIds == null || headerIds.size() <= 0) {
            throw new CommonException("【错误】：输入参数不能为空");
        }
        /*验证  glDate*/
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sf.parse(glDate);
            Map map = new HashMap();
            map.put("P_TRANSFER_DATE", date);
            bondLedgerApiDao.IS_VALIDATE_TRANSFER_DATE(map);
            String outMsg = (String) map.get("p_out");
            if (outMsg != null && !"".equals(outMsg)) {
                return ResponseBodyUtil.getFailedRes("【错误信息】：" + outMsg);
            }
        } catch (ParseException e) {
            return ResponseBodyUtil.getFailedRes("【错误信息】：验证总账期间失败,请联系管理员");
        }
        /*存储组织id的集合*/
        //  Set orgIds = new HashSet();
        //验证
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        //1、根据headerId 获取凭证的详情信息
        RetransferJournal retransferJournal = new RetransferJournal();

        List<Journal> journalList = new ArrayList<>();
        /*存放erp详情集合*/
        List<ERPCftDetail> erpCftDetails = new ArrayList<>();

        Integer groupId = this.getSelfProxy().getNextVal();
        Integer attachCount = 0;
        try {
            for (Integer headerId : headerIds) {
                //2、*获取本地的erp凭证的详细信息*/
                ERPCftDetail erpCftDetail = bondLedgerApiDao.getErpCftDetailHeaderId(headerId);
                if (erpCftDetail == null) {
                    throw new CommonException("【错误】：headerId为" + headerId + "对应的ERP凭证信息不存在！请输入正确的参数！");
                }
                //  orgIds.add(erpCftDetail.getOrgnazationId() == null ? "" : erpCftDetail.getOrgnazationId());
                /*获取erp的行列表信息*/
                List<ErpLine> erpLines = bondLedgerApiDao.getErpLineListByHeaderId(erpCftDetail.getHeaderId());
                if (erpLines == null || erpLines.size() <= 1) {
                    throw new CommonException("【错误】:ERP日记账名称为 ： " + erpCftDetail.getErpBatchName() + ",对应的ERP行信息不存在或者不完整！");
                }
                erpCftDetail.setGroupId(groupId);
                erpCftDetail.setGlDate(glDate);
                erpCftDetail.setStatus("推送中");
                erpCftDetail.setErrorMsg("");
                erpCftDetails.add(erpCftDetail);
                if (erpCftDetail.getAttachmentCount() != null) {
                    attachCount = attachCount + erpCftDetail.getAttachmentCount();
                }
                /*遍历赋值journal*/
                for (int i = 0; i < erpLines.size(); i++) {
                    ErpLine erpLine = erpLines.get(i);
                    Journal journal = new Journal();
                    /*贷方*/
                    if (erpLine.getCreditAmount() != null) {
                        /*摘要*/
                        journal.setDirection("CR");
                        journal.setAmount(erpLine.getCreditAmount());
                        /*借方*/
                    } else if (erpLine.getDebetAmount() != null) {
                        journal.setAmount(erpLine.getDebetAmount());
                        /*摘要*/
                        journal.setDirection("DR");
                    }
                    /*系統编码*/
                    journal.setSource_code("FDM_BOND");
                    journal.setDescription(erpCftDetail.getErpDesc() == null ? "" : erpCftDetail.getErpDesc());
                    journal.setLineDescription(erpLine.getLineDesc() == null ? "" : erpLine.getLineDesc());
                    /*资源id*/
                    journal.setSource_id(groupId);
                    /*日记账名称*/
                    if (JournalName == null || "".equals(JournalName)) {
                        journal.setJournalName(erpCftDetails.get(0).getErpBatchName());
                    } else {
                        journal.setJournalName(JournalName);
                    }
                    /*币种*/
                    journal.setCurrencyCode("CNY");
                    /*现金流量表项*/
                    journal.setCashFlowCode("99");
                    /*设置CCID*/
                    journal.setCCID("");
                    /*实际开票金额*/
                    journal.setActualAmount(erpCftDetail.getAttachmentCount().toString());
                    /*描述*/
                    journal.setAccountDesc(erpLine.getAccountSegment() == null ? "" : erpLine.getAccountSegment());
                    /*总账日期*/
                    journal.setGlDate(glDate);

                    journalList.add(journal);
                }

            }

           /* if (orgIds.size() > 1) {
                throw new CommonException("【错误】,不能选择多个组织的ERP凭证记录！");
            }*/

            if (journalList.size() > 0) {
                for (int j = 0; j < journalList.size(); j++) {
                    journalList.get(j).setAttachCount(attachCount.toString());
                }
            }
            retransferJournal.setJournalList(journalList);
            retransferJournal.setUserId(cscecUser.getUserId());
            //3、调用传送接口
            logger.info("【--传送参数--】:" + JSON.toJSONString(retransferJournal));

            RetransferResult retransferResult = retransferJournalService.retransferJournal(retransferJournal);
            if ("ERROR".equals(retransferResult.getRetCode())) {
                throw new CommonException("【错误信息】,远程服务器消息：" + retransferResult.getRetMsg());
            }
            logger.info(retransferResult.getRetCode());
        } catch (CommonException e) {
            logger.error(e.getMessage());
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CommonException("【错误信息】：推送凭证失败,请联系管理员");
        }
        /*修改状态*/
        try {
            if (erpCftDetails != null && erpCftDetails.size() > 0) {
                for (ERPCftDetail ec : erpCftDetails) {
                    bondLedgerApiDao.updateERPStatus(ec);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseBodyUtil.getFailedRes("本地修改状态失败");
        }
        return ResponseBodyUtil.Success();
    }


    @Override
    @Transactional(transactionManager = "TransactionManager_one", propagation = Propagation.REQUIRES_NEW)
    public int getNextVal() {
        return bondLedgerApiDao.getNextGroupId();
    }

    /**
     * CBS记录查询接口
     *
     * @param cbspayMent
     * @param page
     * @param size
     * @return
     */
    @Override
    public PagerVo getCBSPayMentList(CBSPayMent cbspayMent, Integer page, Integer size) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        PagerVo pagerVo = new PagerVo();
        pagerVo.setPage(page);
        pagerVo.setSize(size);
        List<CBSPayMent> list = new ArrayList<CBSPayMent>();
        try {
            list = bondLedgerApiDao.getCBSPayMentList(cbspayMent, cscecUser, (page - 1) * size, page * size);
            if (list.size() > 0) {
                pagerVo.setTotal(list.get(0).getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：查询CBS记录表异常！");
        }
        pagerVo.setList(list);
        return pagerVo;
    }

    /**
     * CBS记录重传
     *
     * @param cbsPaymentId
     * @return
     */
    @Override
    public void retransferCbspayMent(Integer cbsPaymentId) {
        if (cbsPaymentId == null || cbsPaymentId <= 0) {
            throw new CommonException("【错误】：输入参数不能为空");
        }
        //验证
        //  CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        RetransferCBSprepare CBSprepare = new RetransferCBSprepare();
        List<CBSprePareRetran> prePareList = new ArrayList<>();
        try {
            CBSPayMent cbsp = bondLedgerApiDao.getCbspayMentListCbsPaymentId(cbsPaymentId);
            if (cbsp == null) {
                throw new CommonException("【错误】:cbsPaymentId为" + cbsPaymentId + "对应CBS记录不存在,请输入正确的!");
            }
            /*支付方式的验证*/
            if (!"CBS".equals(cbsp.getPayType())) {
                throw new CommonException("【错误】此申请单选择了其他支付方式，暂不允许推送到CBS，cbsPaymentId为"+ cbsPaymentId );
            }
            //CBS制单重传
            CBSprePareRetran cbsper = new CBSprePareRetran();
            cbsper.setSourceCode("FDM_BOND");//系统编码,
            cbsper.setSourceId(cbsp.getCbsPaymentId());//申请/认领记录ID
            cbsper.setPay_type_id("202");//支付类型
            cbsper.setPayment_method_type_id("2");//结算方式
            cbsper.setPayment_bank_account(cbsp.getPayBankNumber() == null ? "" : cbsp.getPayBankNumber());//付款方银行账号
            Double db = cbsp.getPayAmount() == null ? 0 : cbsp.getPayAmount();
            DecimalFormat decimalFormat = new DecimalFormat("###0.00");//格式化设置
            logger.info("[金额]:" + decimalFormat.format(db));
            String accountName = cbsp.getAccountName();//郑州市公共资源交易中心（郑州市招标局）
            String accountBankNumber = cbsp.getAccountBankNumber();//41050167670809666666-000323
            String accountBank = cbsp.getAccountBankName();//中国建设银行股份有限公司郑州秦岭路支行
            String bankType = cbsp.getBankType();
            String province = cbsp.getProvince();
            String city = cbsp.getCity();
            cbsper.setDesposit_bank_name(accountBank);//收款方  银行名称
            cbsper.setDesposit_bank_account(accountBankNumber);//收款方  银行账号
            cbsper.setDesposit_account_name(accountName);//收款方  账户名称
            //根据三个信息获取银行的信息
            if (StringUtils.isEmpty(bankType) || StringUtils.isEmpty(province) || StringUtils.isEmpty(city)) {
                throw new CommonException("【错误】：该条单据中收款方对应银行类型、省份或城市不存在");
            }
            cbsper.setAmount(decimalFormat.format(db));//交易金额
            cbsper.setDesposit_bank_type(bankType);//收款方银行类型
            cbsper.setDesposit_province(province);//收款方银行开户行省
            cbsper.setDesposit_city(city);//收款方银行开户行市
            cbsper.setDesposit_mobile_phone("");//收款方移动电话号码
            cbsper.setSame_city_flag("0");//是否同城
            cbsper.setPay_channel("3");//支付渠道
            cbsper.setBank_number("");//银行号
            cbsper.setUnion_bank_number("");//联行号
            cbsper.setPurpose(cbsp.getPayDesc() == null ? "空" : cbsp.getPayDesc());//用途
            cbsper.setPay_desc(cbsp.getPayDesc() == null ? "空" : cbsp.getPayDesc());//摘要
            prePareList.add(cbsper);
            CBSprepare.setVariablesList(prePareList);
            logger.info("[--cbs传送参数--]:" + JSON.toJSON(CBSprepare));
            //调用接口
            retransferJournalService.retransferPrepare(CBSprepare);
            logger.info("--传送后---");
            bondLedgerApiDao.updateCbsPaymentId(cbsPaymentId);
        } catch (CommonException e) {
            logger.error(e.getMessage());
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CommonException("【错误】：重传异常！");
        }
    }


    /**
     * 分页查询CBS流水
     *
     * @param cbsStatement
     * @return
     */
    @Override
    public PagerVo<CBSStatement> getCBSStatementList(CBSStatement cbsStatement, Integer reqId, String applyType, Integer page, Integer size) {
        ValidateUtil.checkDoubleIsNull(cbsStatement.getAmount(), "支付金额amount");
        ValidateUtil.checkIsNull(cbsStatement.getAccounts(), "账户信息accounts");
        ValidateUtil.checkIsNull(cbsStatement.getAccountsName(), "账户信息accountsName");
        ValidateUtil.checkIntegerIsNull(reqId, "申请单申请组织");
        ValidateUtil.checkIsNull(applyType, "申请类型");
        if (cbsStatement.getClaimDate() == null || "".equals(cbsStatement.getClaimDate())) {
            cbsStatement.setClaimDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        }
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        PagerVo pagerVo = new PagerVo();
        pagerVo.setPage(page);
        pagerVo.setSize(size);
        List<CBSStatement> cbsStatements = new ArrayList<>();
        try {
            /*根据申请组织  获取支付信息     根据支付信息过滤*/

            cbsStatements = bondLedgerApiDao.getCBSStatementList(cbsStatement, cscecUser, reqId, applyType, (page - 1) * size, page * size);
            if (cbsStatements.size() > 0) {
                pagerVo.setTotal(cbsStatements.get(0).getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：查询保证金申请列表异常！");
        }
        pagerVo.setList(cbsStatements);
        return pagerVo;
    }

    /**
     * 获取保证台账只查询已经审批将通过的
     */
    @Override
    public TallyResult<BondClaimDetail> getBondTallyList(BondTally bondTally, Integer page, Integer size) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        TallyResult pagerVo = new TallyResult();
        pagerVo.setPage(page);
        pagerVo.setSize(size);
        Integer orgId = cscecUser.getOrgId();
        //保证金台账集合
        List<BondTallyDetail> bondTallyDetails = new ArrayList<>();
        /*是否包含下级*/
        Boolean containChild = false;
        if ("Y".equals(bondTally.getContainChild())) {
            containChild = true;
        }
        try {

            //1获取保证金认领详情
            bondTallyDetails = bondLedgerApiDao.getBondTallyList(bondTally, cscecUser, orgId, (page - 1) * size, page * size, containChild);
            /*获取总余额*/
            Double sum = bondLedgerApiDao.getBalance(bondTally, cscecUser, containChild);
            if (sum != null) {
                pagerVo.setSumBalance(sum);
            }
            //2获取流水的行信息
            if (bondTallyDetails.size() > 0) {
                for (int i = 0; i < bondTallyDetails.size(); i++) {


                    BondTallyDetail bondTallyDetail = bondTallyDetails.get(i);
                    Integer headerId = bondTallyDetail.getHeaderId();
                    Integer reqId = bondTallyDetail.getReqId();
                    String status = bondTallyDetail.getClaimStatus();
                    if ("APPROVED".equals(status)) {
                        bondTallyDetail.setClaimStatus("已认领");
                    } else if ("SUBMITED".equals(status)) {
                        bondTallyDetail.setClaimStatus("认领中");
                    } else {
                        bondTallyDetail.setClaimStatus("未认领");
                    }

                    /*説明有对应的认领单*/
                    if (headerId != null) {
                        /*获取对应的认领行*/
                        List<BondClaimLine> bondClaimLines = new ArrayList<BondClaimLine>();
                        bondClaimLines = bondClaimApiDao.getBondClaimLineByHeaderId(headerId);
                        StringBuffer seqNumber = new StringBuffer();
                        /*拼接CBS认领流单据号水*/
                        if (bondClaimLines.size() > 0) {
                            for (BondClaimLine bondClaimLine : bondClaimLines) {
                                if (bondClaimLine.getBankSeqNumber() != null) {
                                    seqNumber.append(bondClaimLine.getBankSeqNumber() + "，");
                                }
                            }
                            bondTallyDetail.setCbsSeqNumber(seqNumber.toString());
                        }
                    }
                    /*  支付状态判断 如果申请单未被认领 有支付状态 根据申请单主键查询流水，且状态为已支付
                        1 局代收代付      结点名称为局总部付款
                        2、非局代收代付   节点名称为二级单位付款
                        3、如果是非cbs的，其他的*/
                    /*判断申请单的类型*/
                    String applyType = bondTallyDetail.getApplyType();
                    String payType = bondTallyDetail.getPayType();
                    String nodeName = "";
                    if ("BUREAU_AGENT".equals(applyType)) {
                        nodeName = "局总部付款";
                    } else if ("NOT_BUREAU_AGENT".equals(applyType)) {
                        nodeName = "二级单位付款";
                    }


                    Integer paymentId = bondLedgerApiDao.getCBSStatementPaymentId(nodeName, reqId);

                    if ("CBS".equals(payType)) {
                        if (paymentId == null) {
                            bondTallyDetail.setPayStatus("未支付");
                        } else {
                            bondTallyDetail.setPayStatus("已支付");
                        }
                    } else {
                        if (!StringUtils.isEmpty(bondTallyDetail.getPayDate())) {
                            bondTallyDetail.setPayStatus("已支付");
                        } else {
                            bondTallyDetail.setPayStatus("未支付");
                        }
                    }
                    /*赋值是否过期*/
                    Integer overDays = bondTallyDetail.getOverDays();
                    if (overDays != null && overDays > 0) {
                        bondTallyDetail.setIsOverdue("是");
                    } else {
                        bondTallyDetail.setIsOverdue("否");
                    }

                    if("MONTH".equals(bondTallyDetail.getProgressPayType())){
                        bondTallyDetail.setProgressPayType("月度");
                    }else if("NODE".equals(bondTallyDetail.getProgressPayType())){
                        bondTallyDetail.setProgressPayType("节点");
                    }
                    if("CASH".equals(bondTallyDetail.getProjectPayType())){
                        bondTallyDetail.setProjectPayType("现金");
                    }else if("ECDS".equals(bondTallyDetail.getProjectPayType())){
                        bondTallyDetail.setProjectPayType("商票");
                    }else if("OTHERS".equals(bondTallyDetail.getProjectPayType())){
                        bondTallyDetail.setProjectPayType("其他");
                    }

                }
                /*创建返回对象*/
                pagerVo.setTotal(bondTallyDetails.get(0).getTotal());
            }
            pagerVo.setList(bondTallyDetails);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：保证金台账查询异常");
        }
        return pagerVo;
    }


}
