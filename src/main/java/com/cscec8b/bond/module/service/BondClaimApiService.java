package com.cscec8b.bond.module.service;

import com.cscec8b.bond.module.object.BondClaimDetail;
import com.cscec8b.bond.module.object.BondClaimSimple;
import com.cscec8b.bond.module.object.BondTally;
import com.cscec8b.bond.module.object.CBSStatement;
import com.cscec8b.common.module.object.PagerVo;
import com.cscec8b.common.module.object.ResultBean;

public interface BondClaimApiService {


    /**
     * 根据单据号获取保证认领详情
     *
     * @param documentNumber
     * @return
     */
    BondClaimDetail getBondClaimDetail(String documentNumber);

    /**
     * 保存保证金认领
     *
     * @param bondClaimDetail
     * @return
     */
    String saveBondClaimDetail(BondClaimDetail bondClaimDetail);

    /**完成认领
     * @param bondClaimDetail
     * @return
     */
    String completeBondClaimDetail(BondClaimDetail bondClaimDetail);

    /**
     * 提交保证金认领
     *
     * @param bondClaimDetail
     * @return
     */
    ResultBean submitBondClaimDetail(BondClaimDetail bondClaimDetail);

    /**
     * 删除保证金认领
     *
     * @param headerId
     * @return
     */
    Integer delBondClaimDetail(Integer headerId);


    /**
     * 获取保证金认领概要信息 列表
     *
     * @param bondClaimSimple
     * @param page
     * @param size
     * @return
     */
    PagerVo<BondClaimSimple> getBondClaimList(BondClaimSimple bondClaimSimple, Integer page, Integer size);

}
