package com.cscec8b.bond.module.service;


import com.cscec8b.bond.module.object.*;
import com.cscec8b.common.module.object.CSCECUser;
import com.cscec8b.common.module.object.PagerVo;
import com.cscec8b.common.module.object.ResultBean;

import java.util.List;

public interface BondReqApiService {


    void  confirmPayment(Integer reqId);

    /**
     * 保存保证金申请
     *
     * @param
     * @return
     */
    List<Message> getMessage(Integer sourceId, String sourceType);

    /**
     *
     *
     * @param message
     * @return
     */
    Integer saveMessage(Message message);

    /**
     * 保存保证金申请
     *
     * @param bondReqDetail
     * @return
     */
    String saveBondReqDetail(BondReqDetail bondReqDetail, CSCECUser cscecUser);

    /**
     * 提交保证金详情
     *
     * @param bondReqDetail
     * @param cscecUser
     * @return
     */
    ResultBean submitBondReqDetail(BondReqDetail bondReqDetail, CSCECUser cscecUser);

    /**
     * 查询保证金列表
     *
     * @param bondReq
     * @return
     */
    PagerVo getBondReqList(BondReq bondReq, Integer page, Integer size);


    /**
     * 根据主键删除保证金申请
     *
     * @param reqId
     * @return
     */
    Integer delBondReqDetail(Integer reqId);


    /**
     * 根据主键或者单据号获取保证金详情
     *
     * @param reqId
     * @param documentNumber
     * @return
     */
    BondReqDetail getBondReqDetail(Integer reqId, String documentNumber);


    /**
     * 根据主键获取变更历史记录
     *
     * @param reqId
     * @return
     */
    List<BondChange> getBondChangeRecords(Integer reqId);


    /**
     * 发起保证金申请变更
     *
     * @param
     */
    ResultBean changeBondReqDetail(Integer reqId, String commons, List<BondChangeDetail> bondChangeDetails);

    Integer updateBondChange(Integer reqId, String commons, CSCECUser cscecUser, List<BondChangeDetail> bondChangeDetails);

    /**
     * 获取经办人的列表
     *
     * @param deptName
     * @param cscecUser
     * @return
     */
    List<Operator> getOperators(String deptName, CSCECUser cscecUser);

    /**
     * 修改保证金类型状态
     *
     * @param reqId
     * @return
     */
    Integer UpdateBondreqId(Integer reqId);

    /**
     * 财务经办人和市场经办人查询
     *
     * @param bondPayment
     * @return
     */
    List getOrgUser(BondPayment bondPayment, Integer orgId);
}
