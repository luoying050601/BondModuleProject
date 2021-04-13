package com.cscec8b.bond.module.object;

/**
 * 变更详情实体
 */
public class BondChangeDetail {

    private Integer lineId; //主键
    private Integer headerId;//变更头主键
    private String columnName;//变更字段名称
    private  String columnNameDesc;//变更字段中文名称
    private String columnSourceValue;//原值
    private String columnSourceValueDesc;//原值含义
    private String columnTargetValue;//目标值
    private String columnTargetValueDesc;//目标值含义
    public String getColumnSourceValueDesc() {
        return columnSourceValueDesc;
    }

    public void setColumnSourceValueDesc(String columnSourceValueDesc) {
        this.columnSourceValueDesc = columnSourceValueDesc;
    }

    public String getColumnTargetValueDesc() {
        return columnTargetValueDesc;
    }

    public void setColumnTargetValueDesc(String columnTargetValueDesc) {
        this.columnTargetValueDesc = columnTargetValueDesc;
    }

    private String  lastUpdateDate;//变更日期
    private String   columnType="varchar2";//变更日期

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getLastUpdateDate() {

        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnSourceValue() {
        return columnSourceValue;
    }

    public void setColumnSourceValue(String columnSourceValue) {
        this.columnSourceValue = columnSourceValue;
    }

    public String getColumnTargetValue() {
        return columnTargetValue;
    }

    public void setColumnTargetValue(String columnTargetValue) {
        this.columnTargetValue = columnTargetValue;
    }

    public String getColumnNameDesc() {
        return columnNameDesc;
    }

    public void setColumnNameDesc(String columnNameDesc) {
        this.columnNameDesc = columnNameDesc;
    }
}
