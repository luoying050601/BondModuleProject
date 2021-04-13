package com.cscec8b.bond.module.object;

import com.cscec8b.common.module.object.SearchVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * cbs 流水
 */
@JsonIgnoreProperties({"total", "page", "size", "orderCol", "orderType", "searchValue",
        "amountFrom", "amountTo", "claimDate"})
public class CBSStatement extends SearchVo {


    private String transId;
    private String accounts;//账户
    private String accountsName;//账户名称

    private Double amount; //交易 或者支付金额
    private Double amountFrom; //交易 或者支付金额
    private Double amountTo; //交易 或者支付金额
    private String organizationName;
    private String status;
    private String bankSeqNumber;//银行流水
    private String remark;// abstract  摘要
    private String purpose;//   用途
    private String transDatetime; //申请单对应的认领日期

    private String claimDate; //查询用的  申请单对应的认领日期

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

/*   private String oppositeBankName; //银行名称

    private String oppositeAccountsName; //银行账户名称

    private String oppositeAccounts;//

    private String debitCreditFlag;//借记信用标志

    private String protocolType; //协议类型

    private BigDecimal balance;

    private String currencyType; //货币类型

    private String purpose;

    private String eBankType;

    private String fromWhoFlag;//从哪里来

    private String cbsComment;//cbs说明

    private String erpComment;

    private String payType;//支付类型

    private String referenceNumber;

    private String transNumber;

    private String interestBeginDate; //利息开始日期

    private String groupSubRegion;

    private String groupSubAccount;

    private String groupSubName;

    private String recordInfo;//记录信息*/

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getAccounts() {
        return accounts;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts;
    }

    public Double getAmount() {
        return amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAccountsName() {
        return accountsName;
    }

    public String getTransDatetime() {
        return transDatetime;
    }

    public void setTransDatetime(String transDatetime) {
        this.transDatetime = transDatetime;
    }

    public void setAccountsName(String accountsName) {
        this.accountsName = accountsName;
    }

    public String getBankSeqNumber() {
        return bankSeqNumber;
    }

    public void setBankSeqNumber(String bankSeqNumber) {
        this.bankSeqNumber = bankSeqNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(Double amountFrom) {
        this.amountFrom = amountFrom;
    }

    public Double getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(Double amountTo) {
        this.amountTo = amountTo;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }
}
