package com.cscec8b.bond.module.object;

import java.util.List;

/**
 * erp记录详情
 */
public class ERPCftDetail {

    /*主键*/
    private Integer headerId;
    /*资源ID  相当于申请单或认领单的主键*/
    private Integer sourceId;

    private String documentNumber;//业务单据号

    /*资源类型编码*/
    private String sourceType;

    private String applyTypeName;//申请类型名称
    private String applyType;//申请类型编码
    private String bondTypeName;//保证金类型名称
    private String bondType;//保证金类型编码

    private String projectName;//项目名称

    /*附件张数*/
    private Integer attachmentCount;
    /*erp日记账名称*/
    private String erpBatchName;
    /*erp总账id*/
    private Integer glHeaderId;
    private Integer orgnazationId;

    /*总账日期*/
    private String glDate;
    private String ledger="CCEED_LEDGER";
    private String lastPushDate;
    /*总账批量id*/
    private Integer glBatchId;
    /*推送状态*/
    private String status;
    /*有效日期*/
    private String effectiveDate;

    private String erpDesc;//说明
    private String Comments;//备注
    private String errorMsg;//备注
    private String   erpDocNumber;//
    private Integer   groupId;//

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getErpDocNumber() {
        return erpDocNumber;
    }

    public Integer getOrgnazationId() {
        return orgnazationId;
    }

    public void setOrgnazationId(Integer orgnazationId) {
        this.orgnazationId = orgnazationId;
    }

    public void setErpDocNumber(String erpDocNumber) {
        this.erpDocNumber = erpDocNumber;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getLedger() {
        return ledger;
    }

    public void setLedger(String ledger) {
        this.ledger = ledger;
    }

    private List<ErpLine> erpLines;

    public String getLastPushDate() {
        return lastPushDate;
    }

    public void setLastPushDate(String lastPushDate) {
        this.lastPushDate = lastPushDate;
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

    public List<ErpLine> getErpLines() {
        return erpLines;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setErpLines(List<ErpLine> erpLines) {
        this.erpLines = erpLines;
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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
}
