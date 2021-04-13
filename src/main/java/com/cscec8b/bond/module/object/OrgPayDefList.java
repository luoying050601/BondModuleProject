package com.cscec8b.bond.module.object;

import java.util.List;

/**
 * Created by admin on 2018/6/20.
 */
public class OrgPayDefList {
    //保存保证金支付
    private List<BondOrgPayDefs> newLines;
    private List<BondOrgPayDefs> updateLines;
    private List<Integer> deleteLines;

    //保存保证金定义
    private List<BondCategory> newLine;
    private List<BondCategory> updateLine;
    private List<Integer> deleteLine;

    //招标方保存
    private List<TendereeAccount> newLineT;
    private List<TendereeAccount> updateLineT;
    private List<Integer> deleteLineT;

    public List<TendereeAccount> getNewLineT() {
        return newLineT;
    }

    public void setNewLineT(List<TendereeAccount> newLineT) {
        this.newLineT = newLineT;
    }

    public List<TendereeAccount> getUpdateLineT() {
        return updateLineT;
    }

    public void setUpdateLineT(List<TendereeAccount> updateLineT) {
        this.updateLineT = updateLineT;
    }

    public List<Integer> getDeleteLineT() {
        return deleteLineT;
    }

    public void setDeleteLineT(List<Integer> deleteLineT) {
        this.deleteLineT = deleteLineT;
    }



    public List<BondCategory> getNewLine() {
        return newLine;
    }

    public void setNewLine(List<BondCategory> newLine) {
        this.newLine = newLine;
    }

    public List<BondCategory> getUpdateLine() {
        return updateLine;
    }

    public void setUpdateLine(List<BondCategory> updateLine) {
        this.updateLine = updateLine;
    }

    public List<Integer> getDeleteLine() {
        return deleteLine;
    }

    public void setDeleteLine(List<Integer> deleteLine) {
        this.deleteLine = deleteLine;
    }



    public List<BondOrgPayDefs> getNewLines() {
        return newLines;
    }

    public void setNewLines(List<BondOrgPayDefs> newLines) {
        this.newLines = newLines;
    }

    public List<BondOrgPayDefs> getUpdateLines() {
        return updateLines;
    }

    public void setUpdateLines(List<BondOrgPayDefs> updateLines) {
        this.updateLines = updateLines;
    }

    public List<Integer> getDeleteLines() {
        return deleteLines;
    }

    public void setDeleteLines(List<Integer> deleteLines) {
        this.deleteLines = deleteLines;
    }
}