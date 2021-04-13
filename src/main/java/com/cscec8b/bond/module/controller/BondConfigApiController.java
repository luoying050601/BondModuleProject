package com.cscec8b.bond.module.controller;


import com.cscec8b.bond.module.object.*;
import com.cscec8b.common.module.object.CSCECUser;
import com.cscec8b.common.module.object.PagerVo;
import com.cscec8b.common.module.object.ResultBean;
import com.cscec8b.common.module.object.exceptionObject.CommonException;
import com.cscec8b.common.module.util.RequestBodyUtil;
import com.cscec8b.common.module.util.ResponseBodyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 吴柱
 * Created by chenzhibin on 2018/6/13.
 */
@RestController
@RequestMapping("${url.BondService}BondConfigApi")
public class BondConfigApiController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(BondConfigApiController.class);

    /**
     * 查询保证金支付
     *
     * @param request
     * @return
     */
    @PostMapping("/getOrgPayDefsList")
    public ResultBean getOrgPayDefsList(@RequestBody Map request) {
        Integer page = RequestBodyUtil.getPage(request);
        Integer size = RequestBodyUtil.getSize(request);
        BondOrgPayDefs bondOrgPayDefs = null;
        try {
            bondOrgPayDefs = RequestBodyUtil.mapToBean(request, BondOrgPayDefs.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }

        PagerVo<BondOrgPayDefs> PayDefsList = bondConfigApiService.getOrgPayDefsList(bondOrgPayDefs, page, size);
        return ResponseBodyUtil.getSuccessRes(PayDefsList);
    }

    /**
     * 保存保证金支付
     */
    @PostMapping("/saveOrgPayDefList")
    public ResultBean saveOrgPayDefList(@RequestBody OrgPayDefList orgPayDefList) {
        Integer Number = bondConfigApiService.saveOrgPayDefList(orgPayDefList);
        return ResponseBodyUtil.getSuccessRes(Number);
    }

    /**
     * 支付定义分页
     */
    @PostMapping("/getPayCategoryList")
    public ResultBean getBondCategoryList(@RequestBody Map request) {
        //获取当前页
        Integer page = RequestBodyUtil.getPage(request);
        Integer size = RequestBodyUtil.getSize(request);
        BondCategory bondCategory = null;
        try {
            bondCategory = RequestBodyUtil.mapToBean(request, BondCategory.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        PagerVo<BondCategory> CategoryList = bondConfigApiService.getBondCategoryList(bondCategory, page, size);
        return ResponseBodyUtil.getSuccessRes(CategoryList);
    }

    /**
     * 保存分类定义
     */
    @PostMapping("/savePayCategoryList")
    public ResultBean saveBondCategory(@RequestBody OrgPayDefList orgPayDefList) {
        Integer Number = bondConfigApiService.saveBondCategory(orgPayDefList);
        return ResponseBodyUtil.getSuccessRes(Number);
    }

    /**
     * 招标方分页
     */
    @PostMapping("/getTendereeAccountList")
    public ResultBean getTendereeAccountList(@RequestBody Map request) {
        //获取当前页
        Integer page = RequestBodyUtil.getPage(request);
        Integer size = RequestBodyUtil.getSize(request);
        TendereeAccount tendereeAccount = null;
        try {
            tendereeAccount = RequestBodyUtil.mapToBean(request, TendereeAccount.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        PagerVo<TendereeAccount> AccountList = bondConfigApiService.getTendereeAccountList(tendereeAccount, page, size);
        return ResponseBodyUtil.getSuccessRes(AccountList);
    }

    /**
     * 保存招标方
     */
    @PostMapping("/saveTendereeAccountList")
    public ResultBean saveTendereeAccountList(@RequestBody OrgPayDefList orgPayDefList) {
        Integer Tend = bondConfigApiService.saveTendereeAccount(orgPayDefList);
        return ResponseBodyUtil.getSuccessRes(Tend);
    }

    /**
     * 会计账科目查询分页
     */
    @PostMapping("/getAccoutSegmentList")
    public ResultBean getAccoutSegmentList(@RequestBody Map request) {
        //获取分页
        Integer page = RequestBodyUtil.getPage(request);
        Integer size = RequestBodyUtil.getSize(request);
        AccountSegment accountSegment = null;
        try {
            accountSegment = RequestBodyUtil.mapToBean(request, AccountSegment.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        PagerVo<AccountSegment> ValuesList = bondConfigApiService.getAccoutSegmentList(accountSegment, page, size);
        return ResponseBodyUtil.getSuccessRes(ValuesList);
    }

    /**
     * 部门段查询所有分页
     *
     * @param request
     * @return
     */
    @PostMapping("/getSegmentValuesList")
    public ResultBean getSegmentValuesList(@RequestBody Map request) {
        Integer page = RequestBodyUtil.getPage(request);
        Integer size = RequestBodyUtil.getSize(request);
        SegmentValues segmentValues = null;
        try {
            segmentValues = RequestBodyUtil.mapToBean(request, SegmentValues.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        PagerVo<SegmentValues> SegmentList = bondConfigApiService.getSegmentValuesList(segmentValues, page, size);
        return ResponseBodyUtil.getSuccessRes(SegmentList);
    }

    /**
     * 备用端查询所有分页
     *
     * @param request
     * @return
     */
    @PostMapping("/getBackUpSegmentValuesList")
    public ResultBean getBackUpSegmentValuesList(@RequestBody Map request) {
        Integer page = RequestBodyUtil.getPage(request);
        Integer size = RequestBodyUtil.getSize(request);
        SegmentValues segmentValues = null;
        try {
            segmentValues = RequestBodyUtil.mapToBean(request, SegmentValues.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        PagerVo<SegmentValues> SegmentList = bondConfigApiService.getBackUpSegmentValuesList(segmentValues, page, size);
        return ResponseBodyUtil.getSuccessRes(SegmentList);
    }


    /**
     * 保证金菜单权限获取
     *
     * @return
     */
    @PostMapping("/getUserMenuList")
    public ResultBean getUserMenuList() {
        CSCECUser curUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        logger.info("【INFO】: 正在获取保证金菜单权限...");
        List<String> users = bondConfigApiService.getUserMenuList(curUser.getUserCode());
        return ResponseBodyUtil.getSuccessRes(users);
    }


    /**
     * 获取往来段列表
     *
     * @param request
     * @return
     */
    @PostMapping("/getContactSegmentList")
    public ResultBean<Contact> getContactSegmentList(@RequestBody Map request) {
        Integer page = RequestBodyUtil.getPage(request);
        Integer size = RequestBodyUtil.getSize(request);
        Contact example = null;
        try {
            example = RequestBodyUtil.mapToBean(request, Contact.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        PagerVo pagerVo = bondConfigApiService.getContactSegmentList(example, page, size);
        return ResponseBodyUtil.getSuccessRes(pagerVo);
    }

}