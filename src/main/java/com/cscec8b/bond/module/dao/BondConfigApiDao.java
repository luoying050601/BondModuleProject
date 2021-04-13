package com.cscec8b.bond.module.dao;

import com.cscec8b.bond.module.object.*;
import com.cscec8b.common.module.object.CSCECUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 保证金支付DAO
 */
@Mapper
@Component
public interface BondConfigApiDao {

    //支付定义序列
    @Select("select fdm.fdm_bond_org_pay_defs_s.nextval from dual")
    Integer getNextval();

    //分类定义
    @Select("select fdm.fdm_bond_category_s.nextval from dual")
    Integer getBondCategoryNextval();

    //招标方保存
    @Select("select fdm.fdm_bond_tenderee_accounts_s.nextval from dual")
    Integer getTendereeAccountNextval();

    /**
     * 查询支付
     *
     * @return
     */
    List getOrgPayDefsList(@Param("BondOrgPayDefs") BondOrgPayDefs BondOrgPayDefs,
                           @Param("cscecUser") CSCECUser cscecUser,
                           @Param("start") Integer start,
                           @Param("end") Integer end);



    int getBondOrgPayDefByOrgId(@Param("orgId") Integer orgId);

    List <BondOrgPayDefs> getSimpleBondOrgPayDefsByOrgId(@Param("orgId") Integer orgId);



    /**
     * 新增保证金支付
     */
    Integer insertBondOrgPayDefs(@Param("bondOrgPayDefs") BondOrgPayDefs bondOrgPayDefs,
                                 @Param("cscecUser") CSCECUser cscecUser);

    /**
     * 修改保证金支付
     */
    Integer updateBondOrgPayDefs(@Param("bondOrgPayDefs") BondOrgPayDefs bondOrgPayDefs,
                                 @Param("cscecUser") CSCECUser cscecUser);

    /**
     * 保证金删除
     */
    Integer delBondOrgPayDef(@Param("defId") Integer defId);

    /**
     * 查询分类分页
     *
     * @param bondCategory
     * @param start
     * @param end
     * @return
     */
    List getBondCategoryList(@Param("bondCategory") BondCategory bondCategory,
                             @Param("start") Integer start,
                             @Param("end") Integer end);

    /**
     * 新增分类定义
     *
     * @param bondCategory
     * @param cscecUser
     * @return
     */
    Integer insertBondCategory(@Param("bondCategory") BondCategory bondCategory,
                               @Param("cscecUser") CSCECUser cscecUser);

    /**
     * 修改分类定义
     *
     * @param bondCategory
     * @param cscecUser
     * @return
     */
    Integer updateBondCategory(@Param("bondCategory") BondCategory bondCategory,
                               @Param("cscecUser") CSCECUser cscecUser);

    /**
     * 根据id修改状态
     * 删除分类定义
     *
     * @param categoryId
     * @return
     */
    Integer delBondCategory(@Param("categoryId") Integer categoryId);

    /**
     * 查询招标方分页
     *
     * @param tendereeAccount
     * @param start
     * @param end
     * @return
     */
    List getTendereeAccountList(@Param("tendereeAccount") TendereeAccount tendereeAccount,
                                @Param("start") Integer start,
                                @Param("end") Integer end);


    /**
     * 获取招标方信息
     *
     * @return
     */
    TendereeAccount getTendereeAccount(@Param("accountName") String accountName,
                                       @Param("accountBank") String accountBank,
                                       @Param("accountBankNumber") String accountBankNumber);
    TendereeAccount getAccount(@Param("accountName") String accountName,
                                       @Param("accountBank") String accountBank,
                                       @Param("accountBankNumber") String accountBankNumber,
                                       @Param("orgId")Integer orgId);

    /**
     * 新增招标方
     *
     * @param tendereeAccount
     * @param cscecUser
     * @return
     */
    Integer insertTendereeAccount(@Param("tendereeAccount") TendereeAccount tendereeAccount,
                                  @Param("cscecUser") CSCECUser cscecUser);


    /**
     * 修改招标方
     *
     * @param tendereeAccount
     * @param cscecUser
     * @return
     */
    Integer updateTendereeAccount(@Param("tendereeAccount") TendereeAccount tendereeAccount,
                                  @Param("cscecUser") CSCECUser cscecUser);

    /**
     * 根据id修改状态
     * 删除招标方
     *
     * @param accountId
     * @return
     */
    Integer delTendereeAccount(@Param("accountId") Integer accountId);

    /**
     * 会计账科目查询接口 分页
     *
     * @param accountSegment
     * @param start
     * @param end
     * @return
     */
    List getAccoutSegmentList(@Param("accountSegment") AccountSegment accountSegment,
                              @Param("start") Integer start,
                              @Param("end") Integer end);

    /**
     * 部门段查询分页
     *
     * @param segmentValues
     * @param start
     * @param end
     * @return
     */
    List getSegmentValuesList(@Param("segmentValues") SegmentValues segmentValues,
                              @Param("start") Integer start,
                              @Param("end") Integer end,
                              @Param("segmentType")String segmentType);

    /**
     * 保证金菜单权限获取
     *
     * @param usercode
     * @return
     */
    List<String> getUserMenuList(@Param("usercode") String usercode);


    List<Contact> getContactSegmentList(@Param("contact") Contact contact,
                                        @Param("start") Integer start,
                                        @Param("end") Integer end);





}
