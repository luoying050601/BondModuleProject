package com.cscec8b.bond.module.service.serviceImpl;

import com.cscec8b.bond.module.dao.BondClaimApiDao;
import com.cscec8b.bond.module.dao.BondLedgerApiDao;
import com.cscec8b.bond.module.dao.BondReqApiDao;
import com.cscec8b.bond.module.feignService.RetransferJournalService;
import com.cscec8b.common.module.dao.stmDao.StmBpmHistoryVDao;
import com.cscec8b.common.module.dao.stmDao.StmCommAttachVDao;
import com.cscec8b.common.module.feignService.SubmitProcessRequestService;
import com.cscec8b.common.module.service.StmBpmHistoryVService;
import com.cscec8b.common.module.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.cscec8b.bond.module.dao.BondConfigApiDao;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.cscec8b.bond.module.dao.ExportExcelDao;

@Component
public class BaseServiceImpl {
    /**
     * BondClaimApiServiceImpl
     */
    @Autowired
    public ApplicationContext applicationContext;
    @Autowired
    public HttpServletRequest servletRequest;
    @Resource
    public BondReqApiDao bondReqApiDao;
    @Autowired
    public JwtTokenUtil jwtTokenUtil;
    @Autowired
    public SubmitProcessRequestService submitProcessRequestService;
    @Resource
    public BondClaimApiDao bondClaimApiDao;
    @Resource
    public BondLedgerApiDao bondLedgerApiDao;
    /**
     * BondConfigApiServiceImpl
     */
    @Resource
    public BondConfigApiDao BondConfigApiDao;

    /**
     * BondLedgerApiServiceImpl
     */

    @Autowired
    public RetransferJournalService retransferJournalService;

    @Autowired
    public BondConfigApiDao bondConfigApiDao;


    /*BondReqApiServiceImpl*/

    @Resource
    public StmCommAttachVDao stmCommAttachVDao;


    @Autowired
    public StmBpmHistoryVService stmBpmHistoryVService;


    /*ExportExcelServiceImpl*/
    @Resource
    public ExportExcelDao ExportExcelDao;


}
