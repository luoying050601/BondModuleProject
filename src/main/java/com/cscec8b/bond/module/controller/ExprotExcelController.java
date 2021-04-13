package com.cscec8b.bond.module.controller;

import com.cscec8b.bond.module.object.BondTally;
import com.cscec8b.bond.module.object.BondTallyDetail;
import com.cscec8b.common.module.object.ExcelData;
import com.cscec8b.common.module.util.ExportExcelUtils;
import com.cscec8b.common.module.util.RequestBodyUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${url.BondService}ExportExcel")
public class ExprotExcelController extends BaseController{


    /**
     * 保证金台账导出
     *
     * @throws Exception
     */
    @PostMapping(value = "/export")
    public void excel(@RequestBody Map request, HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        List<String> titles = new ArrayList();
        titles.add("申请单号");
        titles.add("申请组织");
        titles.add("申请类型");
        titles.add("保证金类型");
        titles.add("项目名称");
        titles.add("对方单位名称");
        titles.add("申请人");
        titles.add("申请日期");
        titles.add("申请金额(元)");
        titles.add("申请状态");
        titles.add("约定还款日期");
        titles.add("财务经办人");
        titles.add("市场经办人");
        titles.add("实际支付日期");
        titles.add("代付状态");
        titles.add("CBS支付单号");
        titles.add("付款方式");
        titles.add("进度款付款方式");
        titles.add("进度款付款比例");
        titles.add("商票付款比例");
        titles.add("认领单号");
        titles.add("认领金额(元)");
        titles.add("认领人");
        titles.add("认领日期");
        titles.add("认领状态");
        titles.add("认领CBS流水单号");
        titles.add("是否逾期");
        titles.add("超额天数");
        titles.add("余额(元)");

        data.setTitles(titles);
        BondTally bondTally = null;
        bondTally = RequestBodyUtil.mapToBean(request, BondTally.class);

        //查询数据
        List<List<Object>> rows = new ArrayList();
        List<BondTallyDetail> bondTallys = exprotExcelService.getTallyDetailderive(bondTally);

        try {
            for (int i = 0; i < bondTallys.size(); i++) {
                BondTallyDetail bondTallya = bondTallys.get(i);
                List<Object> tempObject = new ArrayList<>();
                tempObject.add(bondTallya.getReqDocumentNumber());//保证金申请单据编号
                tempObject.add(bondTallya.getOrganizationName());//所属组织的名称
                tempObject.add(bondTallya.getApplyTypeName());//申请类型名称
                tempObject.add(bondTallya.getBondTypeName());//保证金类型名称
                tempObject.add(bondTallya.getProjectName());//项目名称
                tempObject.add(bondTallya.getTendereeAccountName());//招标方账户名称
                tempObject.add(bondTallya.getReqPreparedByName());//单据创建ID+姓名 /*认领人申請人*/
                tempObject.add(bondTallya.getRePreparedDate()); /*申请日期*/
                tempObject.add(bondTallya.getPayAmount());//支付金额
                if ("APPROVED".equals(bondTallya.getDocumentStatus())) {//单据状态
                    tempObject.add("已审批");
                } else if ("NEW".equals(bondTallya.getDocumentStatus())) {
                    tempObject.add("草稿");
                } else if ("REJECTED".equals(bondTallya.getDocumentStatus())) {
                    tempObject.add("已拒绝");
                } else if ("SUBMITED".equals(bondTallya.getDocumentStatus())) {
                    tempObject.add("审批中");
                } else {
                    tempObject.add("");
                }
                tempObject.add(bondTallya.getRepaymentDate());//还款日期
                tempObject.add(bondTallya.getFinanceHandlerName());//财务部经办人名称
                tempObject.add(bondTallya.getMarketHandlerName());//市场经办人名称
                tempObject.add(bondTallya.getPayDate());//实际支付？付款日期
                tempObject.add(bondTallya.getPayStatus());//代付状态
                tempObject.add(bondTallya.getCbsPayNumber());//cbs支付单号

                tempObject.add(bondTallya.getProjectPayType());//付款方式
                tempObject.add(bondTallya.getProgressPayType());//进度款付款方式
                tempObject.add(bondTallya.getProgressPayRate());//进度款付款比例
                tempObject.add(bondTallya.getEcdsPayRate());//商票付款比例

                tempObject.add(bondTallya.getDocumentNumber()); /*认领单据单据号*/
                tempObject.add(bondTallya.getClaimAmount());/*认领金额*/
                tempObject.add(bondTallya.getPreparedByName());//单据创建ID+姓名 /*认领人申請人*/
                tempObject.add(bondTallya.getClaimDate());/*认领时间*/
                if ("APPROVED".equals(bondTallya.getClaimStatus())) {/*认领状态*/
                    tempObject.add("已认领");
                } else if ("SUBMITED".equals(bondTallya.getClaimStatus())) {
                    tempObject.add("认领中");
                } else if (bondTallya.getClaimStatus() == null) {
                    tempObject.add("未认领");
                }
                tempObject.add(bondTallya.getCbsSeqNumber()); //认领cbs流水单号
                if (bondTallya.getOverDays() > 0) {//是否逾期
                    tempObject.add("是");
                } else {
                    tempObject.add("否");
                }
                tempObject.add(bondTallya.getOverDays());//超额天数
                if (bondTallya.getBalance() == null || "".equals(bondTallya.getBalance())) {
                    tempObject.add("0");
                } else {
                    tempObject.add(bondTallya.getBalance());
                }
                rows.add(tempObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        data.setRows(rows);
        ExportExcelUtils.exportExcel(response, "保证金台账导出.xlsx", data);
    }
}