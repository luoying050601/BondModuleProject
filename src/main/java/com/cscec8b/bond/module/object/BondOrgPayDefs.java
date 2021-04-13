package com.cscec8b.bond.module.object;

import com.cscec8b.common.module.object.SearchVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 保证金支付实体类
 */
@JsonIgnoreProperties({"total", "page", "size", "orderCol", "orderType", "searchValue"})
public class BondOrgPayDefs extends SearchVo {
    private Integer defId;//主键
    private Integer companyId;//公司ID
    private String companyName;
    private String accountName;//账户名称
    private String accountBankNumber;//银行账户
    private String accountBank;//开户银行
    private String accountPersonName;//开户人
    private String accountSegment;//会计科目段
    private String accountSegmentName;

    private String enableFlag;
    private String effectiveStartDate;//失效起始日期
    private String effectiveEndDate;//失效结束日期
    private Integer objectVersionNumber;
    private String creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    private String lastUpdatedDate;
    private String orgShortName;//部门简称

    private String departmentSect;//部门段
    private String departmentDesc;//部门段

    public String getDepartmentDesc() {
        return departmentDesc;
    }

    public void setDepartmentDesc(String departmentDesc) {
        this.departmentDesc = departmentDesc;
    }

    private String financeHandlerName;//财务部经办人ID+名称
    private Integer financeHandler;//财务部经办人ID

    private String marketHandlerName; //市场经办人的ID+名称
    private Integer marketHandler; //市场经办人的id


    private String contactSegmentCode; //往來段编码
    private String contactSegmentDesc; //往來段
    private String agentRule;//代收代付规则
    private String noAgentRule;//非代收代付规则
    private String backUpSegment;//备用段
    private String backUpSegmentDesc;//备用段名称
    private String bankType;//银行类型
    private String bankTypeName;//银行类型名称
    private String province;//省份

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

    private String city;

    public String getBackUpSegmentDesc() {
        return backUpSegmentDesc;
    }

    public void setBackUpSegmentDesc(String backUpSegmentDesc) {
        this.backUpSegmentDesc = backUpSegmentDesc;
    }

    public String getBackUpSegment() {
        return backUpSegment;
    }

    public void setBackUpSegment(String backUpSegment) {
        this.backUpSegment = backUpSegment;
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

    public String getContactSegmentCode() {
        return contactSegmentCode;
    }

    public void setContactSegmentCode(String contactSegmentCode) {
        this.contactSegmentCode = contactSegmentCode;
    }

    public String getContactSegmentDesc() {
        return contactSegmentDesc;
    }

    public void setContactSegmentDesc(String contactSegmentDesc) {
        this.contactSegmentDesc = contactSegmentDesc;
    }

    public String getAgentRule() {
        return agentRule;
    }

    public void setAgentRule(String agentRule) {
        this.agentRule = agentRule;
    }

    public String getNoAgentRule() {
        return noAgentRule;
    }

    public void setNoAgentRule(String noAgentRule) {
        this.noAgentRule = noAgentRule;
    }

    public String getOrgShortName() {
        return orgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName;
    }

    public String getDepartmentSect() {
        return departmentSect;
    }

    public void setDepartmentSect(String departmentSect) {
        this.departmentSect = departmentSect;
    }



    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
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

    public Integer getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Integer objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getAttribuieCategory() {
        return attribuieCategory;
    }

    public void setAttribuieCategory(String attribuieCategory) {
        this.attribuieCategory = attribuieCategory;
    }

    private String attribuieCategory;

    public Integer getDefId() {
        return defId;
    }

    public void setDefId(Integer defId) {
        this.defId = defId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public String getAccountPersonName() {
        return accountPersonName;
    }

    public void setAccountPersonName(String accountPersonName) {
        this.accountPersonName = accountPersonName;
    }

    public String getAccountSegment() {
        return accountSegment;
    }

    public void setAccountSegment(String accountSegment) {
        this.accountSegment = accountSegment;
    }

    public String getAccountSegmentName() {
        return accountSegmentName;
    }

    public void setAccountSegmentName(String accountSegmentName) {
        this.accountSegmentName = accountSegmentName;
    }


}
