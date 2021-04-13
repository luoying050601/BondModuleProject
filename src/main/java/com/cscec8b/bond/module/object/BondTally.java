package com.cscec8b.bond.module.object;

import com.cscec8b.common.module.object.SearchVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 保证金台账查询类
 */
@JsonIgnoreProperties({"total", "page", "size", "orderCol", "orderType", "searchValue"})
public class BondTally extends SearchVo {
    /*认领单据单据号*/
    private String documentNumber;
    private String reqDocumentNumber;//保证申请单据编号

    /*组织id*/
    private Integer organizationId;
    private String organizationName;//所属组织的名称+id
    private String containChild="N";//所属组织的名称+id

    public String getContainChild() {
        return containChild;
    }

    public void setContainChild(String containChild) {
        this.containChild = containChild;
    }

    private String bondTypeName;//保证金类型名称
    private String bondType;//保证金类型编码

    private String applyTypeName;//申请类型名称
    private String applyType;//申请类型编码

    private String financeHandlerName;//财务部经办人ID+名称
    //private Integer financeHandler;//财务部经办人ID

    private String marketHandlerName; //市场经办人的ID+名称
    //private Integer marketHandler; //市场经办人的id
    private String projectName;//项目名称
    private String claimStatus;//认领状态
    private String documentStatus;//申请单状态
    private String isOverdue;//认领状态

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(String isOverdue) {
        this.isOverdue = isOverdue;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getReqDocumentNumber() {
        return reqDocumentNumber;
    }

    public void setReqDocumentNumber(String reqDocumentNumber) {
        this.reqDocumentNumber = reqDocumentNumber;
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

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public String getApplyTypeName() {
        return applyTypeName;
    }

    public void setApplyTypeName(String applyTypeName) {
        this.applyTypeName = applyTypeName;
    }

    public String getFinanceHandlerName() {
        return financeHandlerName;
    }

    public void setFinanceHandlerName(String financeHandlerName) {
        this.financeHandlerName = financeHandlerName;
    }

    public String getMarketHandlerName() {
        return marketHandlerName;
    }

    public void setMarketHandlerName(String marketHandlerName) {
        this.marketHandlerName = marketHandlerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}
