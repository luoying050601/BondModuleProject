package com.cscec8b.bond.module.object;
import com.cscec8b.common.module.object.SearchVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 往来段实体
 */
@JsonIgnoreProperties({"total", "page", "size", "orderCol", "orderType", "searchValue"})
public class Contact extends SearchVo {


    private String contactSegmentCode; //往来段编码
    private String contactSegmentDesc;//往来段名称

    public String getContactSegmentCode() {
        return contactSegmentCode;
    }

    public void setContactSegmentCode(String contactSegmentCode) {
        this.contactSegmentCode = contactSegmentCode;
    }

    public String getContactSegmentDesc() {
        return contactSegmentDesc;
    }

    public void setContactSegmentDesc(String contactSegmentDesc) {
        this.contactSegmentDesc = contactSegmentDesc;
    }
}
