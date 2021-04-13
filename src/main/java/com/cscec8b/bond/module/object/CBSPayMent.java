package com.cscec8b.bond.module.object;


import com.cscec8b.common.module.object.SearchVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by admin on 2018/6/25.
 * CBS
 */
@JsonIgnoreProperties({"total", "page", "size", "orderCol", "orderType", "searchValue",
        "lastPushDateFrom", "lastPushDateTo"})
public class CBSPayMent extends SearchVo {
    private Integer cbsPaymentId;//主键
    private Integer sourceId;//申请/认领记录ID

    private String bankSeqNumber;//CBS流水号/银行流水号
    private String payType;//支付方式
    private String sourceTypeName;//来源类型
    private String sourceType;//来源类型Code
    private String pushStatus;//推送状态
    private String errorMsg;//错误消息
    private String projectName;//项目名称
    private String lastPushDate;//推送日期
    private String lastPushDateFrom;
    private String lastPushDateTo;
    private String applyType;//申请类型编码
    private String applyTypeName;//申请类型
    private String payBankNumber;//付款银行账号
    private String accountBankName;//开户行名称
    private String accountBankNumber;//开户行账号
    private String accountName;//开户单位名称
    private String payDesc;//交易摘要
    private String payusage;//用途
    private Double payAmount;//付款金额
    private String bondType;//保证金类型编码
    private String bondTypeName;//保证金类型名称
    private Integer orgId;//付款方组织id


    private  String bankType;
    private  String province;
    private  String  city;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    private String creationDate;//CBS流水时间
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    private String documentNumber;//业务单据号

    public String getLastPushDateFrom() {
        return lastPushDateFrom;
    }

    public void setLastPushDateFrom(String lastPushDateFrom) {
        this.lastPushDateFrom = lastPushDateFrom;
    }

    public String getLastPushDateTo() {
        return lastPushDateTo;
    }

    public void setLastPushDateTo(String lastPushDateTo) {
        this.lastPushDateTo = lastPushDateTo;
    }

    public String getPayusage() {
        return payusage;
    }

    public void setPayusage(String payusage) {
        this.payusage = payusage;
    }


    public String getBondTypeName() {
        return bondTypeName;
    }

    public void setBondTypeName(String bondTypeName) {
        this.bondTypeName = bondTypeName;
    }


    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }


    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public Integer getCbsPaymentId() {
        return cbsPaymentId;
    }

    public void setCbsPaymentId(Integer cbsPaymentId) {
        this.cbsPaymentId = cbsPaymentId;
    }

    public String getBankSeqNumber() {
        return bankSeqNumber;
    }

    public void setBankSeqNumber(String bankSeqNumber) {
        this.bankSeqNumber = bankSeqNumber;
    }

    public String getSourceTypeName() {
        return sourceTypeName;
    }

    public void setSourceTypeName(String sourceTypeName) {
        this.sourceTypeName = sourceTypeName;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(String pushStatus) {
        this.pushStatus = pushStatus;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLastPushDate() {
        return lastPushDate;
    }

    public void setLastPushDate(String lastPushDate) {
        this.lastPushDate = lastPushDate;
    }

    public String getApplyTypeName() {
        return applyTypeName;
    }

    public void setApplyTypeName(String applyTypeName) {
        this.applyTypeName = applyTypeName;
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

    public String getPayDesc() {
        return payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }
}
