package com.cscec8b.bond.module.object;


import java.util.List;

/**
 * 重新传送的 封装类
 */
public class RetransferJournal {

    private Integer userId;
    private List<Journal> journalList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Journal> getJournalList() {
        return journalList;
    }

    public void setJournalList(List<Journal> journalList) {
        this.journalList = journalList;
    }


}
