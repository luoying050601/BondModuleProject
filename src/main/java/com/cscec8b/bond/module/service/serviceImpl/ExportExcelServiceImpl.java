package com.cscec8b.bond.module.service.serviceImpl;

import com.cscec8b.bond.module.object.BondClaimLine;
import com.cscec8b.bond.module.object.BondTally;
import com.cscec8b.bond.module.object.BondTallyDetail;
import com.cscec8b.bond.module.service.ExprotExcelService;
import com.cscec8b.common.module.object.CSCECUser;
import com.cscec8b.common.module.object.exceptionObject.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExportExcelServiceImpl extends BaseServiceImpl implements ExprotExcelService {
    private Logger logger = LoggerFactory.getLogger(BondConfigApiServiceImpl.class);

    /*
    保证金台账导出
     */
    @Override
    public List getTallyDetailderive(BondTally bondTally) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));

        List<BondTallyDetail> list = new ArrayList<BondTallyDetail>();
        /*是否包含下级*/
        Boolean containChild = false;
        if ("Y".equals(bondTally.getContainChild())) {
            containChild = true;
        }
        try {


            list = ExportExcelDao.getTallyDetailderive(bondTally, cscecUser, containChild);


            if (list.size() > 0) {
                /*获取支付单号*/
                for (int i = 0; i < list.size(); i++) {
                    BondTallyDetail bondTallyDetail = list.get(i);
                    Integer headerId = bondTallyDetail.getHeaderId();
                    Integer reqId = bondTallyDetail.getReqId();
                    if (headerId != null) {
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
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：获取保证金台账导出异常！");
        }
        return list;
    }
}
