package com.cscec8b.bond.module.object;

import java.util.List;

/**
 * 保证金变更实体类
 */
public class BondChange {

    private Integer headerId;//主键
    private Integer reqId;//外键  保证金申请的主键
    private String status;//是否已变更、回滚
    private String Comments;//变更原因
    private String lastUpdateDate;//变更日期
    private String createdBy;//变更ren
    private String createdByName;

    private String approver;//变更审批人

    public String getCreatedBy() {
        return createdBy;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    private List<BondChangeDetail> bondChangeDetails;

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public List<BondChangeDetail> getBondChangeDetails() {
        return bondChangeDetails;
    }

    public void setBondChangeDetails(List<BondChangeDetail> bondChangeDetails) {
        this.bondChangeDetails = bondChangeDetails;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
