package com.cscec8b.bond.module.dao;

import com.cscec8b.bond.module.object.*;
import com.cscec8b.common.module.object.CSCECUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;
import java.util.Map;


@Mapper
public interface BondClaimApiDao {


    /**
     * 根据单据号查询保证金认领详情
     *
     * @param documentNumber
     * @return
     */
    BondClaimDetail getBondClaimDetailByDocumentNumber(@Param("documentNumber") String documentNumber);


    /**
     * 根据外键获取认领单号
     *
     * @param reqId
     * @return
     */
    List<String> getBondClaimDetailByReqId(@Param("reqId") Integer reqId);

    /**
     * 获取未被提交的 未被审批的
     *
     * @param reqId
     * @return
     */
    String getBondClaimDetailDocumentNumber(@Param("reqId") Integer reqId);

    /**
     * 保存
     *
     * @param bondClaimDetail
     * @param cscecUser
     * @return
     */
    Integer insertBondClaimDetail(@Param("bondClaimDetail") BondClaimDetail bondClaimDetail,
                                  @Param("cscecUser") CSCECUser cscecUser);

    /**
     * 获取主键
     *
     * @return
     */
    @Select("select fdm.FDM_BOND_CLAIM_HEADER_S.nextval from dual")
    Integer getNextval();

    @Select("select fdm.fdm_bond_claim_lines_s.nextval from dual")
    Integer getBondClaimLineNextval();

    /**
     * 修改
     *
     * @param bondClaimDetail
     * @param cscecUser
     * @return
     */
    Integer updateBondClaimDetail(@Param("bondClaimDetail") BondClaimDetail bondClaimDetail,
                                  @Param("cscecUser") CSCECUser cscecUser);


    /**
     * 删除保证金认领
     *
     * @param headerId
     * @return
     */
    Integer delBondClaimDetail(@Param("headerId") Integer headerId);


    /**
     * 新增保证金认领行
     *
     * @param bondClaimLine
     * @param cscecUser
     * @return
     */
    Integer insertBondClaimLine(@Param("bondClaimLine") BondClaimLine bondClaimLine, @Param("cscecUser") CSCECUser cscecUser);

    /**
     * 修改保证金认领行
     *
     * @param bondClaimLine
     * @param cscecUser
     * @return
     */
    Integer updateBondClaimLine(@Param("bondClaimLine") BondClaimLine bondClaimLine, @Param("cscecUser") CSCECUser cscecUser);

    /**
     * 获取保证金  行信息  通过 头的主键
     *
     * @param headerId
     * @return
     */
    List<BondClaimLine> getBondClaimLineByHeaderId(@Param("headerId") Integer headerId);

    /**
     * 根据主键删除
     *
     * @param lineId
     * @return
     */
    Integer delBondClaimLineByLineId(@Param("lineId") Integer lineId);

    /**
     * 根据外键删除
     *
     * @param headerId
     * @return
     */
    Integer delBondClaimLineByHeaderId(@Param("headerId") Integer headerId);


    /**
     * 多字段模糊查询保证金认领概要信息
     *
     * @param bondClaimSimple
     * @param start
     * @param end
     * @return
     */
    List<BondClaimSimple> getBondClaimListBySearchValue(@Param("start") Integer start,
                                                        @Param("end") Integer end,
                                                        @Param("bondClaimSimple") BondClaimSimple bondClaimSimple,
                                                        @Param("cscecUser") CSCECUser cscecUser,
                                                        @Param("containChild") Boolean containChild
    );


    /**
     * 修改保证认领详情的状态
     *
     * @param bondClaimDetail
     * @return
     */
    Integer updateBondClaimDetailStatus(@Param("bondClaimDetail") BondClaimDetail bondClaimDetail);


    void InsertBondErpByTrans(Map<String, Object> map);


}
