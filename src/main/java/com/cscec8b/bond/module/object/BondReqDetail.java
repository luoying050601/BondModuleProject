package com.cscec8b.bond.module.object;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 保证金申请实体类
 */
public class BondReqDetail {

    private String claimDocumentNumber;
    private Integer reqId;//主键
    private String documentNumber;//单据编号

    private String organizationName;//所属组织的名称+id
    private Integer organizationId;//所属公司id

    private String applyTypeName;//申请类型名称
    private String applyType;//申请类型编码

    private String projectName;//项目名称

    private String bondTypeName;//保证金类型名称
    private String bondType;//保证金类型编码

    private String financeHandlerName;//财务部经办人ID+名称
    private Integer financeHandler;//财务部经办人ID

    private String marketHandlerName; //市场经办人的ID+名称
    private Integer marketHandler; //市场经办人的id

    private String projectBondAmount;//项目履约保证金金额
    private String projectBondAmountType;//项目履约保证金金额类型

    private String repaymentDate;//还款日期
    private Double contractAmount;//合同金额
    private String otherDuty;//其他义务
    private String otherDutyMeaning;//其他义务
    /*申请金额*/
    private Double payAmount;//支付金额

    private String payDate;//付款日期  由CBS付款时间为准进行回写 付款给招标单位的时间
    private String payDesc;// 付款摘要  传送时：单据编号+付款摘要向后截取
    private String payUsage;//用途
    private String payDuty;//垫付义务
    private String otherDesc;//其他事项说明

    private String tendereeAccountName;//招标方账户名称（对方账户名称）
    private String tendereeAccountBankName;// 招标方帐号银行名称
    private String tendereeAccountBankNumber; //招标方账户银行账号

    private String finacingFlag;  // 融资增信标记
    private String finacingDutyFlag; //融资义务标记
    private String delFundFlag; //交付资金标记
    private String loanFlag; //提供借款标记
    private String mortgageFlag; // 以物抵债标记  单据创建日期，默认今天

    private String projectPayType; //项目付款方式
    private String projectPayTypeName; //项目付款方式 名称
    private Double ecdsPayRate; //商票付款比例 单据状态
    private String progressPayTypeName;//进度款付款名称
    private String progressPayType; //进度款付款类型
    private Double progressPayRate; //进度款付款比例

    private Integer preparedBy; //单据创建人ID，默认当前人
    private String preparedByName;//单据创建ID+姓名

    private String preparedDate; //单据创建日期，默认今天
    private String status; //单据状态
    private String statusMeaning; //单据状态
    private String investFlag; //是否投资类

    private String comments; //备注说明
    private String changeStatus; //备注说明
    private String transferFlag;

    private String tendereeBankType;//
    private String tendereeBankTypeName;//
    private String tendereeProvince;//
    private String tendereeCity;//
    @JsonProperty("ISFlag")
    private String ISFlag;//基础设施标识

    private String payType;//支付方式

    private String attribute1;//是否军民融合类项目


    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getISFlag() {
        return ISFlag;
    }

    public void setISFlag(String ISFlag) {
        this.ISFlag = ISFlag;
    }

    public String getTendereeCity() {
        return tendereeCity;
    }

    public void setTendereeCity(String tendereeCity) {
        this.tendereeCity = tendereeCity;
    }

    public String getTendereeBankTypeName() {
        return tendereeBankTypeName;
    }

    public void setTendereeBankTypeName(String tendereeBankTypeName) {
        this.tendereeBankTypeName = tendereeBankTypeName;
    }

    public String getProjectBondAmountType() {
        return projectBondAmountType;
    }

    public void setProjectBondAmountType(String projectBondAmountType) {
        this.projectBondAmountType = projectBondAmountType;
    }

    public String getTendereeBankType() {
        return tendereeBankType;
    }

    public void setTendereeBankType(String tendereeBankType) {
        this.tendereeBankType = tendereeBankType;
    }

    public String getTendereeProvince() {
        return tendereeProvince;
    }

    public void setTendereeProvince(String tendereeProvince) {
        this.tendereeProvince = tendereeProvince;
    }

    public String getTransferFlag() {
        return transferFlag;
    }

    public void setTransferFlag(String transferFlag) {
        this.transferFlag = transferFlag;
    }


    public String getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

    public String getOtherDutyMeaning() {
        return otherDutyMeaning;
    }

    public void setOtherDutyMeaning(String otherDutyMeaning) {
        this.otherDutyMeaning = otherDutyMeaning;
    }

    public String getStatusMeaning() {
        return statusMeaning;
    }

    public void setStatusMeaning(String statusMeaning) {
        this.statusMeaning = statusMeaning;
    }

    public String getClaimDocumentNumber() {
        return claimDocumentNumber;
    }

    public void setClaimDocumentNumber(String claimDocumentNumber) {
        this.claimDocumentNumber = claimDocumentNumber;
    }

    public String getProjectPayTypeName() {
        return projectPayTypeName;
    }

    public void setProjectPayTypeName(String projectPayTypeName) {
        this.projectPayTypeName = projectPayTypeName;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }


    public Integer getMarketHandler() {
        return marketHandler;
    }

    public void setMarketHandler(Integer marketHandler) {
        this.marketHandler = marketHandler;
    }

    public String getProjectBondAmount() {
        return projectBondAmount;
    }

    public void setProjectBondAmount(String projectBondAmount) {
        this.projectBondAmount = projectBondAmount;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public Double getContractAmount() {
        return contractAmount;
    }

    public String getPayUsage() {
        return payUsage;
    }

    public void setPayUsage(String payUsage) {
        this.payUsage = payUsage;
    }

    public String getPayDuty() {
        return payDuty;
    }

    public void setPayDuty(String payDuty) {
        this.payDuty = payDuty;
    }

    public void setContractAmount(Double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getOtherDuty() {
        return otherDuty;
    }

    public void setOtherDuty(String otherDuty) {
        this.otherDuty = otherDuty;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayDesc() {
        return payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc;
    }

    public String getOtherDesc() {
        return otherDesc;
    }

    public void setOtherDesc(String otherDesc) {
        this.otherDesc = otherDesc;
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

    public String getFinacingFlag() {
        return finacingFlag;
    }

    public void setFinacingFlag(String finacingFlag) {
        this.finacingFlag = finacingFlag;
    }

    public String getFinacingDutyFlag() {
        return finacingDutyFlag;
    }

    public void setFinacingDutyFlag(String finacingDutyFlag) {
        this.finacingDutyFlag = finacingDutyFlag;
    }

    public String getDelFundFlag() {
        return delFundFlag;
    }

    public void setDelFundFlag(String delFundFlag) {
        this.delFundFlag = delFundFlag;
    }

    public String getLoanFlag() {
        return loanFlag;
    }

    public void setLoanFlag(String loanFlag) {
        this.loanFlag = loanFlag;
    }

    public String getMortgageFlag() {
        return mortgageFlag;
    }

    public void setMortgageFlag(String mortgageFlag) {
        this.mortgageFlag = mortgageFlag;
    }

    public String getProjectPayType() {
        return projectPayType;
    }

    public void setProjectPayType(String projectPayType) {
        this.projectPayType = projectPayType;
    }

    public Double getEcdsPayRate() {
        return ecdsPayRate;
    }

    public void setEcdsPayRate(Double ecdsPayRate) {
        this.ecdsPayRate = ecdsPayRate;
    }

    public String getProgressPayType() {
        return progressPayType;
    }

    public void setProgressPayType(String progressPayType) {
        this.progressPayType = progressPayType;
    }

    public Double getProgressPayRate() {
        return progressPayRate;
    }

    public void setProgressPayRate(Double progressPayRate) {
        this.progressPayRate = progressPayRate;
    }

    public Integer getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(Integer preparedBy) {
        this.preparedBy = preparedBy;
    }

    public String getPreparedDate() {
        return preparedDate;
    }

    public void setPreparedDate(String preparedDate) {
        this.preparedDate = preparedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvestFlag() {
        return investFlag;
    }

    public void setInvestFlag(String investFlag) {
        this.investFlag = investFlag;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getBondTypeName() {
        return bondTypeName;
    }

    public void setBondTypeName(String bondTypeName) {
        this.bondTypeName = bondTypeName;
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

    public String getProgressPayTypeName() {
        return progressPayTypeName;
    }

    public void setProgressPayTypeName(String progressPayTypeName) {
        this.progressPayTypeName = progressPayTypeName;
    }

    public String getPreparedByName() {
        return preparedByName;
    }

    public void setPreparedByName(String preparedByName) {
        this.preparedByName = preparedByName;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }
}
