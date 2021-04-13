package com.cscec8b.bond.module.controller;


import com.cscec8b.bond.module.object.*;
import com.cscec8b.common.module.object.CSCECUser;
import com.cscec8b.common.module.object.PagerVo;
import com.cscec8b.common.module.object.ResultBean;
import com.cscec8b.common.module.object.exceptionObject.CommonException;
import com.cscec8b.common.module.util.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保证金维护 褚海涛
 * Created by chenzhibin on 2018/6/13.
 */
@RestController
@RequestMapping("${url.BondService}BondReqApi")
public class BondReqApiController extends BaseController {


    /**
     * 确认已付款
     *
     * @param request
     * @return
     */
    @PostMapping("/confirmPayment")
    public ResultBean confirmPayment(@RequestBody Map request) {
        Integer reqId = (Integer) request.get("reqId");
        if (reqId == null) {
            return ResponseBodyUtil.getFailedRes("reqId不能为空");
        }
        bondReqApiService.confirmPayment(reqId);
        return ResponseBodyUtil.getSuccessRes(null);
    }


    /**
     * 获取留言
     *
     * @param map
     * @return
     */
    @PostMapping("/getMessage")
    public ResultBean getMessage(@RequestBody Map map) {

        Integer sourceId = (Integer) map.get("sourceId");
        String sourceType = (String) map.get("sourceType");
        if (StringUtils.isEmpty(sourceId) || StringUtils.isEmpty(sourceType)) {
            return ResponseBodyUtil.getFailedRes("sourceId、sourceType不能为空");
        }

        List<Message> messages = bondReqApiService.getMessage(sourceId, sourceType);
        return ResponseBodyUtil.getSuccessRes(messages);
    }


    /**
     * 保存留言
     *
     * @param message
     * @return
     */
    @PostMapping("/saveMessage")
    public ResultBean saveMessage(@RequestBody Message message) {

        Integer messageId = bondReqApiService.saveMessage(message);
        return ResponseBodyUtil.getSuccessRes(messageId);
    }


    /**
     * 获取保证金列表
     *
     * @param request
     * @return
     */
    @PostMapping("/getBondReqList")
    public ResultBean getBondReqList(@RequestBody Map request) {
        //获取当前页
        Integer page = RequestBodyUtil.getPage(request);
        Integer size = RequestBodyUtil.getSize(request);
        BondReq bondReq = null;
        try {
            bondReq = RequestBodyUtil.mapToBean(request, BondReq.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        PagerVo<BondReq> bondReqList = bondReqApiService.getBondReqList(bondReq, page, size);
        return ResponseBodyUtil.getSuccessRes(bondReqList);
    }

    /**
     * 根据主键或者单据号获取保证金详情
     *
     * @param map
     * @return
     */
    @PostMapping("/getBondReqDetail")
    public ResultBean getBondReqDetail(@RequestBody Map map) {
        Integer reqId = RequestBodyUtil.getInteger(map, "reqId");
        String documentNumber = RequestBodyUtil.getString(map, "documentNumber");
        BondReqDetail bondReqDetail = bondReqApiService.getBondReqDetail(reqId, documentNumber);
        return ResponseBodyUtil.getSuccessRes(bondReqDetail);
    }

    /**
     * 保存保证金详情信息
     *
     * @param
     * @return
     */
    @PostMapping("/saveBondReqDetail")
    public ResultBean saveBondReqDetail(@RequestBody Map map) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        commonService.getUserInfo(cscecUser);
        BondReqDetail bondReqDetail = null;
        try {
            //初始化查询类，赋初始值
            bondReqDetail = RequestBodyUtil.mapToBean(map, BondReqDetail.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        String newDocumentNumber = bondReqApiService.saveBondReqDetail(bondReqDetail, cscecUser);
        return ResponseBodyUtil.getSuccessRes(newDocumentNumber);
    }

    /**
     * 提交保证金信息
     *
     * @param map
     * @return
     */
    @RequestMapping("/submitBondReqDetail")
    public ResultBean submitBondReqDetail(@RequestBody Map map) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        BondReqDetail bondReqDetail = null;
        try {
            bondReqDetail = RequestBodyUtil.mapToBean(map, BondReqDetail.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        ResultBean resultBean = bondReqApiService.submitBondReqDetail(bondReqDetail, cscecUser);
        return resultBean;
    }

    /**
     * todo 提交保证金信息 无需token
     *
     * @param map
     * @return
     */
    @RequestMapping("/submitBondReqDetailWithoutToken")
    public ResultBean submitBondReqDetailWithToken(@RequestBody Map map) {
        CSCECUser cscecUser = null;
        BondReqDetail bondReqDetail = null;
        try {
            cscecUser = RequestBodyUtil.mapToBean(map, CSCECUser.class);
            bondReqDetail = RequestBodyUtil.mapToBean(map, BondReqDetail.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        ResultBean resultBean = bondReqApiService.submitBondReqDetail(bondReqDetail, cscecUser);
        return resultBean;
    }

    /**
     * 根据主键删除保证金申请
     *
     * @param request
     * @return
     */
    @PostMapping("/delBondReqDetail")
    public ResultBean delBondReqDetail(@RequestBody Map request) {
        Integer reqId = RequestBodyUtil.getInteger(request, "reqId");
        Integer result = bondReqApiService.delBondReqDetail(reqId);
        if (result <= 0) {
            return ResponseBodyUtil.getFailedRes("删除失败，该单据不存在或者该单据状态不允许删除");
        }
        return ResponseBodyUtil.getSuccessRes("删除成功！");
    }


    /**
     * 获取变更记录列表
     *
     * @param request
     * @return
     */
    @PostMapping("/getBondChangeRecords")
    public ResultBean getBondChangeRecords(@RequestBody Map request) {
        Integer reqId = RequestBodyUtil.getInteger(request, "reqId");
        List<BondChange> bondChanges = bondReqApiService.getBondChangeRecords(reqId);
        return ResponseBodyUtil.getSuccessRes(bondChanges);
    }

    /**
     * 发起保证金申请变更
     *
     * @return
     */
    @PostMapping("/changeBondReqDetail")
    public ResultBean changeBondReqDetail(@RequestBody Map request) {
        Integer reqId = RequestBodyUtil.getInteger(request, "reqId");
        String commments = RequestBodyUtil.getString(request, "commments");
        Object object = request.get("BondChangeColumns");
        List<BondChangeDetail> bondChangeDetails = null;
        try {
            bondChangeDetails = Obj2ListUtil.getListFobj(object, BondChangeDetail.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：输入参数解析失败！");
        }
        return bondReqApiService.changeBondReqDetail(reqId, commments, bondChangeDetails);
    }

    /**
     * 获取经办人
     *
     * @param request
     * @return
     */
    @PostMapping("/getOperators")
    public ResultBean getOperators(@RequestBody Map request) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        String departmentName = RequestBodyUtil.getString(request, "departmentName");
        List<Operator> operators = bondReqApiService.getOperators(departmentName, cscecUser);
        return ResponseBodyUtil.getSuccessRes(operators);
    }


    /**
     * 获取保证金菜单权限
     *
     * @return
     */
    @RequestMapping("/getUserMenuList")
    public ResultBean getUserMenuList() {
        List<String> strings = new ArrayList<>();
        strings.add("保证金申请");
        strings.add("报账金认领");
        return ResponseBodyUtil.getSuccessRes(strings);
    }

    /**
     * 用于测试单据打印功能
     */
    @PostMapping("/pdfReportView")
    public ModelAndView pdfView(@RequestBody Map<String, Object> map) {
        List<Integer> reqList = (List<Integer>) map.get("pdfReportList");
        List<Object> mapList = new ArrayList<Object>();
        for (Integer req : reqList) {
            Map<String, Object> tempMap = new HashMap<String, Object>();
            tempMap.put("REQ_ID", new BigDecimal(req));
            tempMap.put("imagePath", "jaspers/images/");
            tempMap.put("SUBREPORT_DIR", "jaspers/");
            mapList.add(tempMap);
        }
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("mapList", mapList);
        return new ModelAndView(new PdfReportUtil("jaspers/保证金申请表.jasper", "保证金申请单", applicationContext.getBean(DataSource.class)), hashMap);
    }

    /**
     * 修改保证金类型
     *
     * @param request
     * @return
     */
    @PostMapping("/UpdateBondreqId")
    public ResultBean UpdateBondreqId(@RequestBody Map request) {
        Integer reqId = RequestBodyUtil.getInteger(request, "reqId");
        bondReqApiService.UpdateBondreqId(reqId);
        return ResponseBodyUtil.Success();
    }

    /**
     * 查询财务经办人和市场经办人
     *
     * @param request
     * @return
     */
    @PostMapping("/getOrgUser")
    public ResultBean getOrgUser(@RequestBody Map request) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        Integer orgId = cscecUser.getOrgId();
        BondPayment bondPayment = null;
        try {
            bondPayment = RequestBodyUtil.mapToBean(request, BondPayment.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        List<BondPayment> PayDefsList = bondReqApiService.getOrgUser(bondPayment, orgId);
        return ResponseBodyUtil.getSuccessRes(PayDefsList);
    }

}
