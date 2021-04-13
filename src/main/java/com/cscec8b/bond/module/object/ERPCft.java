package com.cscec8b.bond.module.object;

import com.cscec8b.common.module.object.SearchVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * ERP凭证概要信息实体
 */
@JsonIgnoreProperties({"total", "page", "size", "orderCol", "orderType",
        "searchValue","glDateFrom","glDateTo","glHeaderId","glBatchId","lastPushDateFrom","lastPushDateTo"})
public class ERPCft extends SearchVo {
    /*主键*/
    private Integer headerId;
    /*资源ID  相当于申请单或认领单的主键*/
    private Integer sourceId;

    private  String documentNumber;//业务单据号
    /*资源类型*/
    private String   sourceType;
    private String   sourceTypeName;

    private String   organizationId;//申请或者认领的组织
    private String   orgId;//生成的erp的打款节点的组织Id
    private String   organizationName;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSourceTypeName() {
        return sourceTypeName;
    }

    public void setSourceTypeName(String sourceTypeName) {
        this.sourceTypeName = sourceTypeName;
    }

    private String applyTypeName;//申请类型名称
    private String applyType;//申请类型编码
    private String bondTypeName;//保证金类型名称
    private String bondType;//保证金类型编码

    private String projectName;//项目名称

    /*附件张数*/

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    private Integer attachmentCount;
    /*erp日记账名称*/
    private String erpBatchName;
    /*erp总账id*/
    private Integer glHeaderId;

    /*总账日期*/
    private String glDate;

    private String glDateFrom;
    private String glDateTo;


    private String lastPushDate;
    private String lastPushDateFrom;
    private String lastPushDateTo;

    /*总账批量id*/
    private Integer glBatchId;
    /*推送状态*/
    private String status;


    /*有效日期*/
    private String effectiveDate;

    private String erpDesc;//说明
    private String Comments;//备注
    private String errorMsg;//备注

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getLastPushDate() {
        return lastPushDate;
    }

    public void setLastPushDate(String lastPushDate) {
        this.lastPushDate = lastPushDate;
    }

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

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getGlDateFrom() {
        return glDateFrom;
    }

    public void setGlDateFrom(String glDateFrom) {
        this.glDateFrom = glDateFrom;
    }

    public String getGlDateTo() {
        return glDateTo;
    }

    public void setGlDateTo(String glDateTo) {
        this.glDateTo = glDateTo;
    }

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getApplyTypeName() {
        return applyTypeName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public Integer getAttachmentCount() {
        return attachmentCount;
    }

    public void setAttachmentCount(Integer attachmentCount) {
        this.attachmentCount = attachmentCount;
    }

    public String getErpBatchName() {
        return erpBatchName;
    }

    public void setErpBatchName(String erpBatchName) {
        this.erpBatchName = erpBatchName;
    }

    public Integer getGlHeaderId() {
        return glHeaderId;
    }

    public void setGlHeaderId(Integer glHeaderId) {
        this.glHeaderId = glHeaderId;
    }

    public String getGlDate() {
        return glDate;
    }

    public void setGlDate(String glDate) {
        this.glDate = glDate;
    }

    public Integer getGlBatchId() {
        return glBatchId;
    }

    public void setGlBatchId(Integer glBatchId) {
        this.glBatchId = glBatchId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErpDesc() {
        return erpDesc;
    }

    public void setErpDesc(String erpDesc) {
        this.erpDesc = erpDesc;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }
}
