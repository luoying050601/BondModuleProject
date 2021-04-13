package com.cscec8b.bond.module.object;


/**
 * 保证金认领  行 实体类
 */
public class BondClaimLine {

    /*主键*/
    private Integer lineId;
    /*认领头id*/
    private Integer headerId;
    /*银行流水号*/
    private String bankSeqNumber;
    /*流水交易金额*/
    private Double amount;
    /*付款账户名称*/
    private String payBankNumber;
    /*开户行名称*/
    private String accountBankName;
    /*开户行账号*/
    private String accountBankNumber;
    /*开户单位名称*/
    private String accountName;
    /*流水支付日期*/
    private String transDatetime;

    private String  purpose;
    /*流水主键*/
    private String transId;

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public String getBankSeqNumber() {
        return bankSeqNumber;
    }

    public void setBankSeqNumber(String bankSeqNumber) {
        this.bankSeqNumber = bankSeqNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPayBankNumber() {
        return payBankNumber;
    }

    public void setPayBankNumber(String payBankNumber) {
        this.payBankNumber = payBankNumber;
    }

    public String getAccountBankName() {
        return accountBankName;
    }

    public void setAccountBankName(String accountBankName) {
        this.accountBankName = accountBankName;
    }

    public String getAccountBankNumber() {
        return accountBankNumber;
    }

    public void setAccountBankNumber(String accountBankNumber) {
        this.accountBankNumber = accountBankNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTransDatetime() {
        return transDatetime;
    }

    public void setTransDatetime(String transDatetime) {
        this.transDatetime = transDatetime;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }
}
