package com.cscec8b.bond.module.controller;


import com.cscec8b.bond.module.object.BondChange;
import com.cscec8b.bond.module.object.BondClaimDetail;
import com.cscec8b.bond.module.object.BondReqDetail;
import com.cscec8b.common.module.object.ResultBean;
import com.cscec8b.common.module.util.RequestBodyUtil;
import com.cscec8b.common.module.util.ResponseBodyUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 移动端请求接口褚海涛   要求没有token也可以进入
 */
@RestController
@RequestMapping("${url.BondService}BondMobApi")
public class BondMobApiController extends BaseController{



    /**
     * 根据主键或者单据号获取保证金详情
     *
     * @param map
     * @return
     *
     */
    @PostMapping("/getBondReqDetail")
    public ResultBean getBondReqDetail(@RequestBody Map map) {
        Integer reqId = RequestBodyUtil.getInteger(map, "reqId");
        String documentNumber = RequestBodyUtil.getString(map, "documentNumber");
        BondReqDetail bondReqDetail = bondReqApiService.getBondReqDetail(reqId, documentNumber);
        return ResponseBodyUtil.getSuccessRes(bondReqDetail);
    }

    /**
     * 获取保证金认领详情信息
     *
     * @param request
     * @return
     */
    @PostMapping("/getBondClaimDetail")
    public ResultBean getBondClaimDetail(@RequestBody Map request) {
        String documentNumber = RequestBodyUtil.getString(request, "documentNumber");
        BondClaimDetail bondClaimDetail = bondClaimApiService.getBondClaimDetail(documentNumber);
        return ResponseBodyUtil.getSuccessRes(bondClaimDetail);
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

}
