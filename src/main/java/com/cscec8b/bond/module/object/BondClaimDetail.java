package com.cscec8b.bond.module.object;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import oracle.sql.DATE;

import java.util.List;

/**
 * 保证金认领详情实体  头表
 */
@JsonIgnoreProperties({"createdBy"})
public class BondClaimDetail {
    /*主键*/
    private Integer headerId;
    /*认领单据单据号*/
    private String documentNumber;
    /*对应的申请单id*/
    private Integer reqId;
    /*申请日期*/
    private String preparedDate;
    /*申請人*/
    private Integer preparedBy;
    private String preparedByName;//单据创建ID+姓名
    /*组织id*/
    private Integer organizationId;
    private String organizationName;//所属组织的名称+id

    private Integer reqOrganizationId;

    private String reqOrganizationName;

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

    /*认领金额*/
    private Double claimAmount;
    /*备注说明*/
    private String comments;
    /*状态*/
    private String status;
    private String statusMeaning;
    /*退款业主*/
    private String repaymentTenderee;
    /*退款业主账号*/
    private String repaymentBankNumber;
    /*创建人*/
    private Integer createdBy;
    /*认领时间*/
    private String claimDate;

    /*业主退款日期*/
    private String repaymentDate;
    ;


    /*其他的保证金申请的信息*/

    private String reqDocumentNumber;//保证申请单据编号
    private String projectName;//项目名称

    private String tendereeAccountName;//招标方账户名称
    private String tendereeAccountBankName;// 招标方帐号银行名称
    private String tendereeAccountBankNumber; //招标方账户银行账号

    private String bondTypeName;//保证金类型名称
    private String bondType;//保证金类型编码

    /*申请金额*/
    private Double payAmount;//支付金额

    private String applyTypeName;//申请类型名称
    private String applyType;//申请类型编码

    private String financeHandlerName;//财务部经办人ID+名称
    private Integer financeHandler;//财务部经办人ID

    private String marketHandlerName; //市场经办人的ID+名称
    private Integer marketHandler; //市场经办人的id
    /*保证认领行*/
    List<BondClaimLine> bondClaimLines;

    /*需要删除的保证认领 行*/
    List delBondClaimLinesId;

    public String getStatusMeaning() {
        return statusMeaning;
    }

    public void setStatusMeaning(String statusMeaning) {
        this.statusMeaning = statusMeaning;
    }


    public String getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }


    public List<BondClaimLine> getBondClaimLines() {
        return bondClaimLines;
    }

    public void setBondClaimLines(List<BondClaimLine> bondClaimLines) {
        this.bondClaimLines = bondClaimLines;
    }

    public List<Integer> getDelBondClaimLinesId() {
        return delBondClaimLinesId;
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

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public String getPreparedDate() {
        return preparedDate;
    }

    public void setPreparedDate(String preparedDate) {
        this.preparedDate = preparedDate;
    }

    public Integer getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(Integer preparedBy) {
        this.preparedBy = preparedBy;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
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

    public String getRepaymentTenderee() {
        return repaymentTenderee;
    }

    public void setRepaymentTenderee(String repaymentTenderee) {
        this.repaymentTenderee = repaymentTenderee;
    }

    public String getRepaymentBankNumber() {
        return repaymentBankNumber;
    }

    public void setRepaymentBankNumber(String repaymentBankNumber) {
        this.repaymentBankNumber = repaymentBankNumber;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }


    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public void setDelBondClaimLinesId(List delBondClaimLinesId) {
        this.delBondClaimLinesId = delBondClaimLinesId;
    }

    public String getReqDocumentNumber() {
        return reqDocumentNumber;
    }

    public void setReqDocumentNumber(String reqDocumentNumber) {
        this.reqDocumentNumber = reqDocumentNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTendereeAccountName() {
        return tendereeAccountName;
    }

    public void setTendereeAccountName(String tendereeAccountName) {
        this.tendereeAccountName = tendereeAccountName;
    }

    public String getTendereeAccountBankName() {
        return tendereeAccountBankName;
    }

    public void setTendereeAccountBankName(String tendereeAccountBankName) {
        this.tendereeAccountBankName = tendereeAccountBankName;
    }

    public String getTendereeAccountBankNumber() {
        return tendereeAccountBankNumber;
    }

    public void setTendereeAccountBankNumber(String tendereeAccountBankNumber) {
        this.tendereeAccountBankNumber = tendereeAccountBankNumber;
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

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPreparedByName() {
        return preparedByName;
    }

    public void setPreparedByName(String preparedByName) {
        this.preparedByName = preparedByName;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }
}
