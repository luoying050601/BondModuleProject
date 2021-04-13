package com.cscec8b.bond.module.controller;

import com.cscec8b.bond.module.object.*;
import com.cscec8b.common.module.object.PagerVo;
import com.cscec8b.common.module.object.ResultBean;
import com.cscec8b.common.module.object.exceptionObject.CommonException;
import com.cscec8b.common.module.util.PdfReportUtil;
import com.cscec8b.common.module.util.RequestBodyUtil;
import com.cscec8b.common.module.util.ResponseBodyUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保证金认领褚海涛
 * Created by chenzhibin on 2018/6/13.
 */
@RestController
@RequestMapping("${url.BondService}BondClaimApi")
public class BondClaimApiController extends BaseController {



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
     * 保存保证金认领详情
     *
     * @param bondClaimDetail
     * @return
     */
    @PostMapping("/saveBondClaimDetail")
    public ResultBean saveBondClaimDetail(@RequestBody BondClaimDetail bondClaimDetail) {
        String documentNumber = bondClaimApiService.saveBondClaimDetail(bondClaimDetail);
        return ResponseBodyUtil.getSuccessRes(documentNumber);

    }

    /**
     * 保存保证金认领详情
     *
     * @param bondClaimDetail
     * @return
     */
    @PostMapping("/completeBondClaimDetail")
    public ResultBean completeBondClaimDetail(@RequestBody BondClaimDetail bondClaimDetail) {
        String documentNumber = bondClaimApiService.completeBondClaimDetail(bondClaimDetail);
        return ResponseBodyUtil.getSuccessRes(documentNumber);

    }


    /**
     * 删除保证金认领详情
     *
     * @param request
     * @return
     */
    @PostMapping("/delBondClaimDetail")
    public ResultBean delBondClaimDetail(@RequestBody Map request) {
        Integer headerId = RequestBodyUtil.getInteger(request, "headerId");
        Integer result = bondClaimApiService.delBondClaimDetail(headerId);
        return ResponseBodyUtil.Success();
    }


    /**
     * 提交保证金认领
     *
     * @param bondClaimDetail
     * @return
     */
    @PostMapping("/submitBondClaimDetail")
    public ResultBean submitBondClaimDetail(@RequestBody BondClaimDetail bondClaimDetail) {
        return bondClaimApiService.submitBondClaimDetail(bondClaimDetail);

    }

    /**
     * 获取保证金认领概要信息列表
     *
     * @param request
     * @return
     */
    @PostMapping("/getBondClaimList")
    public ResultBean getBondClaimList(@RequestBody Map request) {
        //获取当前页
        Integer page = RequestBodyUtil.getPage(request);
        Integer size = RequestBodyUtil.getSize(request);
        BondClaimSimple bondClaimSimple = null;
        try {
            bondClaimSimple = RequestBodyUtil.mapToBean(request, BondClaimSimple.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        PagerVo<BondClaimSimple> bondClaimList = bondClaimApiService.getBondClaimList(bondClaimSimple, page, size);
        return ResponseBodyUtil.getSuccessRes(bondClaimList);
    }

    /*用于测试单据打印功能*/

    @PostMapping("/pdfReportView")
    public ModelAndView pdfView(@RequestBody Map<String, Object> map) {
        List<Integer> reqList = (List<Integer>) map.get("pdfReportList");
        List<Object> mapList = new ArrayList<Object>();
        for (Integer headerId : reqList) {
            Map<String, Object> tempMap = new HashMap<String, Object>();
            tempMap.put("HEADER_ID", new BigDecimal(headerId));
            tempMap.put("imagePath", "jaspers/images/");
            tempMap.put("SUBREPORT_DIR", "jaspers/");
            mapList.add(tempMap);
        }
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("mapList", mapList);
        return new ModelAndView(new PdfReportUtil("jaspers/保证金认领申请表.jasper", "保证金申请单", applicationContext.getBean(DataSource.class)), hashMap);
    }
}