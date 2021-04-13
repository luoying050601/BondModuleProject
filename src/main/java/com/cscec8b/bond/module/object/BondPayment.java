package com.cscec8b.bond.module.object;

/**
 * Created by admin on 2018/7/26.
 */
public class BondPayment {
    private Integer companyId;//公司ID
    private String financeHandlerName;//财务部经办人ID+名称
    private Integer financeHandler;//财务部经办人ID

    @Override
    public String toString() {
        return "BondPayment{" +
                "companyId=" + companyId +
                ", financeHandlerName='" + financeHandlerName + '\'' +
                ", financeHandler=" + financeHandler +
                ", marketHandlerName='" + marketHandlerName + '\'' +
                ", marketHandler=" + marketHandler +
                '}';
    }

    private String marketHandlerName; //市场经办人的ID+名称

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    private Integer marketHandler; //市场经办人的id
}
