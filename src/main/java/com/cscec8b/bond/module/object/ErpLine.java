package com.cscec8b.bond.module.object;

import java.math.BigDecimal;

/**
 * erp记录行表
 */
public class ErpLine {

    /*主键*/
    private Integer lineId;
    /*外键*/
    private Integer headerId;
    /*行号*/
    private String lineNum;
    /*说明摘要*/
    private String accountSegment;
    /*信贷金额*/
    private Double debetAmount;
    /*借项金额*/
    private Double creditAmount;


    private String lineDesc;
    private String accountSegmentDesc;
    private Integer codeCombinationId; //借贷的id

    public String getAccountSegmentDesc() {
        return accountSegmentDesc;
    }

    public void setAccountSegmentDesc(String accountSegmentDesc) {
        this.accountSegmentDesc = accountSegmentDesc;
    }

    public Integer getCodeCombinationId() {
        return codeCombinationId;
    }

    public void setCodeCombinationId(Integer codeCombinationId) {
        this.codeCombinationId = codeCombinationId;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public String getAccountSegment() {
        return accountSegment;
    }

    public void setAccountSegment(String accountSegment) {
        this.accountSegment = accountSegment;
    }

    public Double getDebetAmount() {
        return debetAmount;
    }

    public void setDebetAmount(Double debetAmount) {
        this.debetAmount = debetAmount;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getLineDesc() {
        return lineDesc;
    }

    public void setLineDesc(String lineDesc) {
        this.lineDesc = lineDesc;
    }
}
