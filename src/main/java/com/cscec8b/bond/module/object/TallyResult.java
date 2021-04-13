package com.cscec8b.bond.module.object;


import java.util.List;

public class TallyResult<T>  {
    private Integer page = 1;
    private Integer size = 10;
    private Integer total = 0;
    private Double SumBalance ;

    public Double getSumBalance() {
        return SumBalance;
    }

    public void setSumBalance(Double sumBalance) {
        SumBalance = sumBalance;
    }

    List<T> list;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
