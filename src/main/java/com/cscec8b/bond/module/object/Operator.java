package com.cscec8b.bond.module.object;

/**
 * 经办人
 */
public class Operator {

    private Integer employeeId;
    private String operatorId; //经办人的工号
    private String operatorName;//工号+姓名

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
