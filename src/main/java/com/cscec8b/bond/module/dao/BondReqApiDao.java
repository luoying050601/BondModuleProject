package com.cscec8b.bond.module.dao;


import com.cscec8b.bond.module.object.*;
import com.cscec8b.common.module.object.CSCECUser;
import com.cscec8b.common.module.object.LookUpRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 保证金申请DAO
 */
@Mapper
public interface BondReqApiDao {

    Integer updateBondReqDetailPayDate(@Param("reqId") Integer reqId,
                                       @Param("cscecUser") CSCECUser cscecUser);

    /**
     * @param
     */
    List<Message> getMessages(@Param("sourceId") Integer sourceId,
                              @Param("sourceType") String sourceType);

    /**
     * @param
     */
    void updateMessage(@Param("message") Message message,
                       @Param("cscecUser") CSCECUser cscecUser);

    /**
     * @param
     */
    void insertMessage(@Param("message") Message message,
                       @Param("cscecUser") CSCECUser cscecUser);

    /**
     * 获取单据号
     *
     * @param map
     */
    void callGetDocNumber(Map<String, Object> map);

    @Select("select fdm.fdm_bond_reqs_s.nextval from dual")
    Integer getNextval();

    @Select("select fdm.fdm_bond_change_header_s.nextval from dual")
    Integer getBondChangeNextVal();

    @Select("select fdm.fdm_bond_change_lines_S.nextval from dual")
    Integer getBondChangeDetailNextVal();

    /**
     * 新增保证金申请
     *
     * @param bondReqDetail
     * @return
     */
    Integer insertBondReqDetail(@Param("bondReqDetail") BondReqDetail bondReqDetail,
                                @Param("cscecUser") CSCECUser cscecUser);

    /**
     * 修改保证金申请
     *
     * @param bondReqDetail
     * @return
     */
    Integer updateBondReqDetail(@Param("bondReqDetail") BondReqDetail bondReqDetail,
                                @Param("cscecUser") CSCECUser cscecUser);

    /**
     * 修改已经审核的保证金申请
     *
     * @param bondReqDetail
     * @param cscecUser
     * @return
     */
    Integer changeBondReqDetail(@Param("bondReqDetail") BondReqDetail bondReqDetail,
                                @Param("cscecUser") CSCECUser cscecUser);

    /**
     * 多字段分页排序查询
     *
     * @param bondReq
     * @param start
     * @param end
     * @return
     */
    List getBondReqListBySearchValue(@Param("bondReq") BondReq bondReq,
                                     @Param("cscecUser") CSCECUser cscecUser,
                                     @Param("start") Integer start,
                                     @Param("end") Integer end,
                                     @Param("containChild") Boolean containChild);


    /**
     * 根据主键删除保证金申请
     *
     * @param reqId
     * @return
     */
    Integer delBondReqDetail(@Param("reqId") Integer reqId);


    /**
     * 根据主键或者单据号查询保证金详情
     *
     * @param reqId
     * @param documentNumber
     * @return
     */
    BondReqDetail getBondReqDetailByReqIdOrDocumentNumber(@Param("reqId") Integer reqId,
                                                          @Param("documentNumber") String documentNumber);


    String getChangeStatus(@Param("reqId") Integer reqId);

    /**
     * 主键或者单据号查询保证金详情  web端的查询接口
     *
     * @param reqId
     * @param documentNumber
     * @return
     */
    BondReqDetail getBondReqDetailByReqIdOrDocumentNumberWeb(@Param("reqId") Integer reqId,
                                                             @Param("documentNumber") String documentNumber);


    /**
     * 修改状态
     *
     * @param
     */
    void updateBondReqDetailStatus(@Param("bondReqDetail") BondReqDetail bondReqDetail);


    /**
     * 根据保证金申请单据号获取变更记录列表
     *
     * @param reqId
     * @return
     */
    List<BondChange> getBondChangeByReqId(@Param("reqId") Integer reqId);

    int delBondChangeByHeaderId(@Param("headerId") Integer headerId);

    /**
     * 根据变更头主键获取变更详情列表
     *
     * @param headerId
     * @return
     */
    List<BondChangeDetail> getBondChangeDetailsByHeaderId(@Param("headerId") Integer headerId);


    /**
     * 获取当前登录用户的组织指定部门的 所有的人员
     *
     * @param deptName
     * @param organizationId
     * @return
     */
    List<Operator> getOperatorsLikeDeptName(@Param("deptName") String deptName,
                                            @Param("organizationId") Integer organizationId);


    /**
     * 新增变更记录
     *
     * @param bondChange
     * @param cscecUser
     * @return
     */
    Integer insertBondChange(@Param("bondChange") BondChange bondChange,
                             @Param("cscecUser") CSCECUser cscecUser);


    /**
     * 新增变更记录详情
     *
     * @param bondChangeDetail
     * @param cscecUser
     * @return
     */
    Integer insertBondChangeDetail(@Param("bondChangeDetail") BondChangeDetail bondChangeDetail,
                                   @Param("cscecUser") CSCECUser cscecUser);


    Integer updateBondChangeDetail(@Param("bondChange") BondChange BondChange,
                                   @Param("cscecUser") CSCECUser cscecUser);

    /**
     * 获取状态为正在审批的保证金变更
     *
     * @param reqId
     * @return
     */
    BondChange getBondChangeByStatus(@Param("reqId") Integer reqId);


    /**
     * 修改保证金申请类型状态
     *
     * @param reqId
     * @return
     */
    Integer UpdateBondreqId(@Param("reqId") Integer reqId);


    /**
     * 财务经办人和市场经办人
     *
     * @param bondPayment
     * @param cscecUser
     * @return
     */
    List getOrgUser(@Param("bondPayment") BondPayment bondPayment,
                    @Param("cscecUser") CSCECUser cscecUser,
                    @Param("orgId") Integer orgId);


    LookUpRecord getBondBankTypeName(@Param("BondBankType") String BondBankType);

    /**
     * 审批完成后执行逻辑
     *
     * @param map
     */
    void callProcessComplete(Map<String, Object> map);
}
