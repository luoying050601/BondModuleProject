package com.cscec8b.bond.module.object;

import com.cscec8b.common.module.object.SearchVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 招标方业主账户实体类
 */
@JsonIgnoreProperties({"total", "page", "size", "orderCol", "orderType", "searchValue", "activeFlag"})
public class TendereeAccount extends SearchVo {

    private Integer accountId;//主键
    private String accountName;//开户单位名称
    private String accountShortName;//开户单位简称
    private String accountPersonName;//开户人
    private String accountBankNumber;//开户银行账号
    private String accountBank;//开户银行
    private String effectiveStartDate;//失效起始日期
    private String effectiveEndDate;//失效结束日期
    private String enableFlag;//失效状态
    private String bankType;//银行类型
    private String bankTypeName;//银行类型名称
    private String province;//省份
    private String city;


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankTypeName() {
        return bankTypeName;
    }

    public void setBankTypeName(String bankTypeName) {
        this.bankTypeName = bankTypeName;
    }

    public String getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(String effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public String getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(String effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }


    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountShortName() {
        return accountShortName;
    }

    public void setAccountShortName(String accountShortName) {
        this.accountShortName = accountShortName;
    }

    public String getAccountPersonName() {
        return accountPersonName;
    }

    public void setAccountPersonName(String accountPersonName) {
        this.accountPersonName = accountPersonName;
    }

    public String getAccountBankNumber() {
        return accountBankNumber;
    }

    public void setAccountBankNumber(String accountBankNumber) {
        this.accountBankNumber = accountBankNumber;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }


}
