package com.cscec8b.bond.module.object;

import com.cscec8b.common.module.object.SearchVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 会计账科目实体类
 */
@JsonIgnoreProperties({"total", "page", "size", "orderCol", "orderType", "searchValue"})
public class AccountSegment extends SearchVo {

    private String accountSegment;
    private String accountSegmentName;
    public String getAccountSegment() {
        return accountSegment;
    }

    public void setAccountSegment(String accountSegment) {
        this.accountSegment = accountSegment;
    }

    public String getAccountSegmentName() {
        return accountSegmentName;
    }

    public void setAccountSegmentName(String accountSegmentName) {
        this.accountSegmentName = accountSegmentName;
    }
}