package com.cscec8b.bond.module.object;

import com.cscec8b.common.module.object.SearchVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 保证金台账详情实体类
 */
@JsonIgnoreProperties({"total", "page", "size", "orderCol", "orderType", "searchValue"})
public class BondTallyDetail extends SearchVo {

    /*主键*/
    private Integer headerId;
    /*认领单据单据号*/
    private String documentNumber;
    /*对应的申请单id*/
    private Integer reqId;
    /*认领单申请日期*/
    private String preparedDate;
    /*认领人申請人*/
    private Integer preparedBy;
    private String preparedByName;//单据创建ID+姓名
    /*认领金额*/
    private Double claimAmount;
    /*备注说明*/
    private String comments;
    /*认领状态*/
    private String claimStatus;
    /*退款业主*/

    /*认领时间*/
    private String claimDate;

    /*其他的保证金申请的信息*/



    private String reqDocumentNumber;//保证金申请单据编号
    private String projectName;//项目名称
    private String tendereeAccountName;//招标方账户名称
    private String tendereeAccountBankName;// 招标方帐号银行名称
    private String tendereeAccountBankNumber; //招标方账户银行账号





    /*申请单申请日期*/
    private String rePreparedDate;

    /*组织id*/
    private Integer organizationId;
    private String organizationName;//所属组织的名称+id
    private String bondTypeName;//保证金类型名称
    private String bondType;//保证金类型编码

    /*申请金额*/
    private Double payAmount;//支付金额

    private String applyTypeName;//申请类型名称
    private String applyType;//申请类型编码 局代收代付 非局代代付

    private String financeHandlerName;//财务部经办人ID+名称
    private Integer financeHandler;//财务部经办人ID

    private String marketHandlerName; //市场经办人的ID+名称
    private Integer marketHandler; //市场经办人的id

    /*申请人*/
    private Integer reqPreparedBy; //申请单据创建人ID，默认当前人
    private String  reqPreparedByName;//单据创建ID+姓名

    private String repaymentDate;//还款日期
    private String payDate;//实际支付？付款日期  由CBS付款时间为准进行回写 付款给招标单位的时间

    /*集合的cbs流水的单据拼接*/
    private String CbsSeqNumber;//认领cbs流水单号
    /*自定义属性*/
    private String payStatus;//代付状态？


    private String payType;//支付类型

    private String ecdsPayRate;//商票付款比例
    private String projectPayType;//项目付款方式
    private String progressPayType;//进度款付款类型
    private String progressPayRate;//进度款付款比例

    /*对应的*/
    private String CbsPayNumber;//cbs支付单号

    private String isOverdue;//是否逾期
    private Integer overDays; //超额天数

    private Double balance; //余额


    /*单据状态*/
    private  String documentStatus;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getRePreparedDate() {
        return rePreparedDate;
    }

    public void setRePreparedDate(String rePreparedDate) {
        this.rePreparedDate = rePreparedDate;
    }

    public String getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public String getPreparedDate() {
        return preparedDate;
    }

    public void setPreparedDate(String preparedDate) {
        this.preparedDate = preparedDate;
    }

    public Integer getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(Integer preparedBy) {
        this.preparedBy = preparedBy;
    }

    public String getPreparedByName() {
        return preparedByName;
    }

    public void setPreparedByName(String preparedByName) {
        this.preparedByName = preparedByName;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(Double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }

    public String getReqDocumentNumber() {
        return reqDocumentNumber;
    }

    public void setReqDocumentNumber(String reqDocumentNumber) {
        this.reqDocumentNumber = reqDocumentNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTendereeAccountName() {
        return tendereeAccountName;
    }

    public void setTendereeAccountName(String tendereeAccountName) {
        this.tendereeAccountName = tendereeAccountName;
    }

    public String getTendereeAccountBankName() {
        return tendereeAccountBankName;
    }

    public void setTendereeAccountBankName(String tendereeAccountBankName) {
        this.tendereeAccountBankName = tendereeAccountBankName;
    }

    public String getTendereeAccountBankNumber() {
        return tendereeAccountBankNumber;
    }

    public void setTendereeAccountBankNumber(String tendereeAccountBankNumber) {
        this.tendereeAccountBankNumber = tendereeAccountBankNumber;
    }

    public String getBondTypeName() {
        return bondTypeName;
    }

    public void setBondTypeName(String bondTypeName) {
        this.bondTypeName = bondTypeName;
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public String getApplyTypeName() {
        return applyTypeName;
    }

    public void setApplyTypeName(String applyTypeName) {
        this.applyTypeName = applyTypeName;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getFinanceHandlerName() {
        return financeHandlerName;
    }

    public void setFinanceHandlerName(String financeHandlerName) {
        this.financeHandlerName = financeHandlerName;
    }

    public Integer getFinanceHandler() {
        return financeHandler;
    }

    public void setFinanceHandler(Integer financeHandler) {
        this.financeHandler = financeHandler;
    }

    public String getMarketHandlerName() {
        return marketHandlerName;
    }

    public void setMarketHandlerName(String marketHandlerName) {
        this.marketHandlerName = marketHandlerName;
    }

    public Integer getMarketHandler() {
        return marketHandler;
    }

    public void setMarketHandler(Integer marketHandler) {
        this.marketHandler = marketHandler;
    }

    public Integer getReqPreparedBy() {
        return reqPreparedBy;
    }

    public void setReqPreparedBy(Integer reqPreparedBy) {
        this.reqPreparedBy = reqPreparedBy;
    }

    public String getReqPreparedByName() {
        return reqPreparedByName;
    }

    public void setReqPreparedByName(String reqPreparedByName) {
        this.reqPreparedByName = reqPreparedByName;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getCbsPayNumber() {
        return CbsPayNumber;
    }

    public void setCbsPayNumber(String cbsPayNumber) {
        CbsPayNumber = cbsPayNumber;
    }

    public String getCbsSeqNumber() {
        return CbsSeqNumber;
    }

    public void setCbsSeqNumber(String cbsSeqNumber) {
        CbsSeqNumber = cbsSeqNumber;
    }

    public String getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(String isOverdue) {
        this.isOverdue = isOverdue;
    }

    public Integer getOverDays() {
        return overDays;
    }

    public void setOverDays(Integer overDays) {
        this.overDays = overDays;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getProjectPayType() {
        return projectPayType;
    }

    public void setProjectPayType(String projectPayType) {
        this.projectPayType = projectPayType;
    }

    public String getProgressPayType() {
        return progressPayType;
    }

    public void setProgressPayType(String progressPayType) {
        this.progressPayType = progressPayType;
    }

    public void setEcdsPayRate(String ecdsPayRate) {
        this.ecdsPayRate = ecdsPayRate;
    }

    public void setProgressPayRate(String progressPayRate) {
        this.progressPayRate = progressPayRate;
    }

    public String getEcdsPayRate() {
        return ecdsPayRate;
    }

    public String getProgressPayRate() {
        return progressPayRate;
    }
}
