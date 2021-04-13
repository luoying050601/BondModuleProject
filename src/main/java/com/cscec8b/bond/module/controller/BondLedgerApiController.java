package com.cscec8b.bond.module.controller;


import com.cscec8b.bond.module.object.*;
import com.cscec8b.common.module.object.PagerVo;
import com.cscec8b.common.module.object.ResultBean;
import com.cscec8b.common.module.object.exceptionObject.CommonException;
import com.cscec8b.common.module.util.Obj2ListUtil;
import com.cscec8b.common.module.util.RequestBodyUtil;
import com.cscec8b.common.module.util.ResponseBodyUtil;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * CBS、ERP、保证金台账的 褚海涛
 */
@RequestMapping("${url.BondService}BondLedger")
@RestController
public class BondLedgerApiController extends BaseController{

    /**
     * 获取erp的列表
     *
     * @param request
     * @return
     */
    @PostMapping("/getERPJournalList")
    public ResultBean getErpCftList(@RequestBody Map request) {
        Integer page = RequestBodyUtil.getPage(request);
        Integer size = RequestBodyUtil.getSize(request);
        ERPCft erpCft = null;
        try {
            erpCft = RequestBodyUtil.mapToBean(request, ERPCft.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        PagerVo pagerVo = bondLedgerApiService.getErpCftList(erpCft, page, size);
        return ResponseBodyUtil.getSuccessRes(pagerVo);
    }

    /**
     * 获取erp日记账的详情
     * {
     * "erpBatchName" : "ERP记账名称3"
     * <p>
     * }
     *
     * @param request
     * @return
     */
    @PostMapping("/getERPJournalDetail")
    public ResultBean getErpCftDetail(@RequestBody Map request) {
        Integer headerId = RequestBodyUtil.getInteger(request, "headerId");
        ERPCftDetail erpCftDetail = bondLedgerApiService.getErpCftDetail(headerId);
        return ResponseBodyUtil.getSuccessRes(erpCftDetail);
    }


    /**
     * erp总账重新传送接口
     *
     * @param request
     * @return
     */
    @PostMapping("/retransferERPJournals")
    public ResultBean retransferErpCft(@RequestBody Map request) {
        String glDate = RequestBodyUtil.getString(request, "glDate");
        String JournalName = RequestBodyUtil.getString(request, "JournalName");
        List<Integer> headerIds = Obj2ListUtil.getListFobj(request.get("headerIds"), Integer.class);
            return bondLedgerApiService.retransferErpCft(headerIds,JournalName, glDate);
    }

    /**
     * .CBS记录查询接口
     *
     * @param request
     * @return
     */
    @PostMapping("/getCBSPayMentList")
    public ResultBean getCBSPayMentList(@RequestBody Map request) {
        Integer page = RequestBodyUtil.getPage(request);
        Integer size = RequestBodyUtil.getSize(request);
        CBSPayMent cbspayMent = null;
        try {
            cbspayMent = RequestBodyUtil.mapToBean(request, CBSPayMent.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        PagerVo<CBSPayMent> CbspList = bondLedgerApiService.getCBSPayMentList(cbspayMent, page, size);
        return ResponseBodyUtil.getSuccessRes(CbspList);
    }

    /**
     * CBS记录重传接口
     *
     * @param request
     * @return
     */
    @PostMapping("/getCBSPayMentListid")
    public ResultBean getCBSPayMentListid(@RequestBody Map request) {
        Integer cbsPaymentId = RequestBodyUtil.getInteger(request, "cbsPaymentId");
        bondLedgerApiService.retransferCbspayMent(cbsPaymentId);
        return ResponseBodyUtil.Success();
    }

    /**
     * 查询CBS凭证流水
     *
     * @param request
     * @return {
     * "accounts" :"",
     * "amount" : null,
     * "transDatetime" :"",
     * <p>
     * <p>
     * "bankSeqNumber" : "",
     * "amountFrom" : null,
     * "amountTo" : null,
     * "accountsName" : "",
     * "remark" : "",
     * "page": 1,
     * "size": 10
     * }
     */
    @PostMapping("/getCBSStatementList")
    public ResultBean getCBSStatementList(@RequestBody Map request) {
        Integer page = RequestBodyUtil.getPage(request);
        Integer size = RequestBodyUtil.getSize(request);
        Integer  reqId=RequestBodyUtil.getInteger(request,"reqId");
        String  applyType=RequestBodyUtil.getString(request,"applyType");
        CBSStatement cbsStatement = null;
        try {
            cbsStatement = RequestBodyUtil.mapToBean(request, CBSStatement.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        PagerVo pagerVo = bondLedgerApiService.getCBSStatementList(cbsStatement,reqId,applyType, page, size);
        return ResponseBodyUtil.getSuccessRes(pagerVo);
    }


    /**
     * 获取保证金台账列表
     *
     * @return
     */
    @PostMapping("/getBondTallyList")
    public ResultBean getBondTallyList(@RequestBody Map request) {
        Integer page = RequestBodyUtil.getPage(request);
        Integer size = RequestBodyUtil.getSize(request);
        BondTally bondTally = null;
        try {
            bondTally = RequestBodyUtil.mapToBean(request, BondTally.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        TallyResult pagerVo = bondLedgerApiService.getBondTallyList(bondTally, page, size);
        return ResponseBodyUtil.getSuccessRes(pagerVo);
    }


}