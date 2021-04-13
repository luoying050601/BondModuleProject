package com.cscec8b.bond.module.object;

import com.cscec8b.common.module.object.SearchVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 保证金认领概要信息 实体类
 */
@JsonIgnoreProperties({"total", "page", "size", "orderCol", "orderType", "searchValue",
        "preparedDateFrom", "preparedDateTo", "claimAmountFrom", "claimAmountTo", "payAmountFrom", "payAmountTo"})
public class BondClaimSimple extends SearchVo {
    /*主键*/
    private Integer headerId;
    /*认领单据单据号*/
    private String documentNumber;


    /*组织id*/
    private Integer organizationId;
    private String organizationName;//所属组织的名称+id
    private  String containChild="N";  //是否包含下级

    private Integer reqOrganizationId;
    private String reqOrganizationName;

    /*申請人*/
    private Integer preparedBy;
    private String preparedByName;//单据创建ID+姓名
    /*申请日期*/
    private String preparedDate;
    private String preparedDateFrom;
    private String preparedDateTo;

    /*申请金额*/
    private Double payAmountFrom;
    private Double payAmount;//支付金额
    private Double payAmountTo;
    /*认领金额*/
    private Double claimAmount;
    private Double claimAmountFrom;
    private Double claimAmountTo;
    /*备注说明*/
    private String comments;
    /*状态*/
    private String status;

    private String projectName;//项目名称
    private String reqDocumentNumber;//保证申请单据编号

    public Integer getReqOrganizationId() {
        return reqOrganizationId;
    }

    public void setReqOrganizationId(Integer reqOrganizationId) {
        this.reqOrganizationId = reqOrganizationId;
    }

    public String getReqOrganizationName() {
        return reqOrganizationName;
    }

    public void setReqOrganizationName(String reqOrganizationName) {
        this.reqOrganizationName = reqOrganizationName;
    }

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(Integer preparedBy) {
        this.preparedBy = preparedBy;
    }

    public String getPreparedByName() {
        return preparedByName;
    }

    public void setPreparedByName(String preparedByName) {
        this.preparedByName = preparedByName;
    }

    public String getPreparedDate() {
        return preparedDate;
    }

    public void setPreparedDate(String preparedDate) {
        this.preparedDate = preparedDate;
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

    public Double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(Double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getReqDocumentNumber() {
        return reqDocumentNumber;
    }

    public void setReqDocumentNumber(String reqDocumentNumber) {
        this.reqDocumentNumber = reqDocumentNumber;
    }

    public Double getClaimAmountFrom() {
        return claimAmountFrom;
    }

    public void setClaimAmountFrom(Double claimAmountFrom) {
        this.claimAmountFrom = claimAmountFrom;
    }

    public Double getClaimAmountTo() {
        return claimAmountTo;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public Double getPayAmountFrom() {
        return payAmountFrom;
    }

    public void setPayAmountFrom(Double payAmountFrom) {
        this.payAmountFrom = payAmountFrom;
    }

    public Double getPayAmountTo() {
        return payAmountTo;
    }

    public void setPayAmountTo(Double payAmountTo) {
        this.payAmountTo = payAmountTo;
    }

    public void setClaimAmountTo(Double claimAmountTo) {
        this.claimAmountTo = claimAmountTo;
    }


    public String getContainChild() {
        return containChild;
    }

    public void setContainChild(String containChild) {
        this.containChild = containChild;
    }
}
