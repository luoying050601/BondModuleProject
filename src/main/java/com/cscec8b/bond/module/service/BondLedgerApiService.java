package com.cscec8b.bond.module.service;

import com.cscec8b.bond.module.object.*;
import com.cscec8b.common.module.object.PagerVo;
import com.cscec8b.common.module.object.ResultBean;

import java.util.List;

public interface BondLedgerApiService {


    /**
     * 获取Erp凭证列表
     *
     * @param erpCft
     * @param page
     * @param size
     * @return
     */
    PagerVo<ERPCft> getErpCftList(ERPCft erpCft, Integer page, Integer size);

    int getNextVal();

    /**
     * 根据ERP日记账名称 查询erp记录
     *
     * @param headerId
     * @return
     */
    ERPCftDetail getErpCftDetail(Integer headerId);


    /**
     * 重新传送接口
     *
     * @param headerIds
     */
    ResultBean retransferErpCft(List<Integer> headerIds,String JournalName, String glDate);

    /**
     * CBS记录查询接口 分页
     *
     * @param cbspayMent
     * @param page
     * @param size
     * @return
     */
    PagerVo getCBSPayMentList(CBSPayMent cbspayMent, Integer page, Integer size);

    /**
     * CBS记录重传
     *
     * @param cbsPaymentId
     * @return
     */
    void retransferCbspayMent(Integer cbsPaymentId);

    /**
     * 获取CBS流水
     *
     * @param cbsStatement
     * @return
     */
    PagerVo<CBSStatement> getCBSStatementList(CBSStatement cbsStatement,Integer organizationId ,String applyType,Integer page, Integer size);


    /**
     * 获取保证金台账
     *
     * @param bondTally
     * @param page
     * @param size
     * @return
     */
    TallyResult getBondTallyList(BondTally bondTally, Integer page, Integer size);


}