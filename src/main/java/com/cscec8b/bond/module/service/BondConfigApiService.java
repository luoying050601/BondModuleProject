package com.cscec8b.bond.module.service;


import com.cscec8b.bond.module.object.*;
import com.cscec8b.common.module.object.CSCECUser;
import com.cscec8b.common.module.object.PagerVo;

import java.util.List;
import java.util.Map;

public interface BondConfigApiService {
    /**
     * 支付方式定义分页
     *
     * @param bondOrgPayDefs
     * @param page
     * @param size
     * @return
     */
    PagerVo getOrgPayDefsList(BondOrgPayDefs bondOrgPayDefs, Integer page, Integer size);

    /**
     * 保证金新增支付
     *
     * @param orgPayDefList
     * @return
     */
    Integer saveOrgPayDefList(OrgPayDefList orgPayDefList);


    /**
     * 分类定义查询分页
     *
     * @param bondCategory
     * @param page
     * @param size
     * @return
     */
    PagerVo getBondCategoryList(BondCategory bondCategory, Integer page, Integer size);

    /**
     * 新增分类定义
     *
     * @param orgPayDefList
     * @return
     */
    Integer saveBondCategory(OrgPayDefList orgPayDefList);


    /**
     * 招标方查询分页
     *
     * @param tendereeAccount
     * @param page
     * @param size
     * @return
     */
    PagerVo getTendereeAccountList(TendereeAccount tendereeAccount, Integer page, Integer size);

    /**
     * 新增招标方保存
     *
     * @param orgPayDefList
     * @return
     */
    Integer saveTendereeAccount(OrgPayDefList orgPayDefList);

    /**
     * 会计账科目查询接口 分页
     *
     * @param accountSegment
     * @param page
     * @param size
     * @return
     */
    PagerVo getAccoutSegmentList(AccountSegment accountSegment, Integer page, Integer size);


    /**
     * 部门段查询分页接口
     *
     * @param segmentValues
     * @param page
     * @param size
     * @return
     */
    PagerVo getSegmentValuesList(SegmentValues segmentValues, Integer page, Integer size);
    PagerVo getBackUpSegmentValuesList(SegmentValues segmentValues, Integer page, Integer size);

    /**
     * 保证金菜单权限获取
     *
     * @param userCode
     * @return
     */
    List<String> getUserMenuList(String userCode);


    PagerVo<Contact> getContactSegmentList(Contact contact, Integer page, Integer size);


}
