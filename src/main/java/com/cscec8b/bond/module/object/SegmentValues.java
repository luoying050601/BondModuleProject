package com.cscec8b.bond.module.object;

import com.cscec8b.common.module.object.SearchVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by admin on 2018/7/16.
 * 部门段
 */
@JsonIgnoreProperties({"total", "page", "size", "orderCol", "orderType", "searchValue"})
public class SegmentValues extends SearchVo{
    private String segmentCode;
    private String segmentDesc;

    public String getSegmentCode() {
        return segmentCode;
    }

    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    public String getSegmentDesc() {
        return segmentDesc;
    }

    public void setSegmentDesc(String segmentDesc) {
        this.segmentDesc = segmentDesc;
    }
}
