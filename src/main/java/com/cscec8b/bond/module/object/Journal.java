package com.cscec8b.bond.module.object;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


/**
 * 重传分装的erp 实体
 */
public class Journal {

    private String source_code;//系统编码,
    private Integer source_id;//表id
    private String journalName;//日记账名称
    private String currencyCode;//币种
    private String description;//头摘要
    private String cashFlowCode;// 相近流量表项
    private String lineDescription;// 行摘要
    private String attachCount;// 附件张数
    private String actualAmount;// 实际开票金额
    private String glDate;// Gl日期
    @JsonProperty("CCID")
    private String CCID;//
    private String accountDesc;//ID为空时的取值 科目段

    private Double amount;// 金额
    private String direction;


    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAccountDesc() {
        return accountDesc;
    }

    public void setAccountDesc(String accountDesc) {
        this.accountDesc = accountDesc;
    }


    public String getSource_code() {
        return source_code;
    }

    public void setSource_code(String source_code) {
        this.source_code = source_code;
    }

    public Integer getSource_id() {
        return source_id;
    }


    public String getCCID() {
        return CCID;
    }

    public void setCCID(String CCID) {
        this.CCID = CCID;
    }

    public void setSource_id(Integer source_id) {
        this.source_id = source_id;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCashFlowCode() {
        return cashFlowCode;
    }

    public void setCashFlowCode(String cashFlowCode) {
        this.cashFlowCode = cashFlowCode;
    }

    public String getLineDescription() {
        return lineDescription;
    }

    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription;
    }

    public String getAttachCount() {
        return attachCount;
    }

    public void setAttachCount(String attachCount) {
        this.attachCount = attachCount;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getGlDate() {
        return glDate;
    }

    public void setGlDate(String glDate) {
        this.glDate = glDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
