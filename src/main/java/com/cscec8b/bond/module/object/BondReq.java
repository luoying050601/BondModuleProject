package com.cscec8b.bond.module.object;


import com.cscec8b.common.module.object.SearchVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 保证金列表类
 */
@JsonIgnoreProperties({"total", "page", "size", "orderCol", "orderType", "searchValue",
        "preparedDateFrom", "preparedDateTo", "repaymentDateFrom", "repaymentDateTo", "companyId"})
public class BondReq extends SearchVo {
    private Integer reqId;//主键

    private String documentNumber;//单据编号
    private String claimDocumentNumber;//单据编号
    private String projectName;//项目名称
    private String status; //单据状态
    private String preparedDateFrom; //单据开始日期
    private String preparedDateTo; //单据创结束期
    private String preparedDate; //单据创建日期
    private Integer organizationId;//所属公司id
    private String organizationName;//所属公司id

    private  String containChild="N";  //是否包含下级


    public String getContainChild() {
        return containChild;
    }

    public void setContainChild(String containChild) {
        this.containChild = containChild;
    }

    public String getClaimDocumentNumber() {
        return claimDocumentNumber;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setClaimDocumentNumber(String claimDocumentNumber) {
        this.claimDocumentNumber = claimDocumentNumber;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    private String repaymentDateFrom;
    private String repaymentDateTo;
    private String repaymentDate;//还款日期

    private Integer companyId;// 组织id

    private String applyType;
    private String applyTypeName;//申请类型名称

    private String bondTypeName;//保证金类型名称
    private String bondType;//保证金类型编码
    private Double applyAmount;// payAmount支付金额> 申请金额

    private String comments; //备注说明 申请原因
    private String changeStatus; //备注说明 申请原因

    public String getChangeStatus() {
        return changeStatus;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

    private Integer preparedBy; //单据创建人ID，默认当前人 编制人
    private String preparedByName; //单据创建人ID，+名称

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getRepaymentDateFrom() {
        return repaymentDateFrom;
    }

    public void setRepaymentDateFrom(String repaymentDateFrom) {
        this.repaymentDateFrom = repaymentDateFrom;
    }

    public String getRepaymentDateTo() {
        return repaymentDateTo;
    }

    public void setRepaymentDateTo(String repaymentDateTo) {
        this.repaymentDateTo = repaymentDateTo;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getApplyTypeName() {
        return applyTypeName;
    }

    public void setApplyTypeName(String applyTypeName) {
        this.applyTypeName = applyTypeName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPreparedDateFrom() {
        return preparedDateFrom;
    }

    public void setPreparedDateFrom(String preparedDateFrom) {
        this.preparedDateFrom = preparedDateFrom;
    }

    public String getPreparedDateTo() {
        return preparedDateTo;
    }

    public void setPreparedDateTo(String preparedDateTo) {
        this.preparedDateTo = preparedDateTo;
    }

    public String getPreparedDate() {
        return preparedDate;
    }

    public void setPreparedDate(String preparedDate) {
        this.preparedDate = preparedDate;
    }

    public String getBondTypeName() {
        return bondTypeName;
    }

    public void setBondTypeName(String bondTypeName) {
        this.bondTypeName = bondTypeName;
    }

    public String getPreparedByName() {
        return preparedByName;
    }

    public void setPreparedByName(String preparedByName) {
        this.preparedByName = preparedByName;
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }



    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(Integer preparedBy) {
        this.preparedBy = preparedBy;
    }

    public Double getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(Double applyAmount) {
        this.applyAmount = applyAmount;
    }
}
