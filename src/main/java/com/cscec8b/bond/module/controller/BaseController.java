package com.cscec8b.bond.module.controller;

import com.cscec8b.bond.module.service.*;
import com.cscec8b.common.module.service.CommonService;
import com.cscec8b.common.module.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class BaseController {
    /**
     * BondClaimApiController
     */
    @Autowired
    public BondClaimApiService bondClaimApiService;

    @Autowired
    public ApplicationContext applicationContext;

    /**
     * BondConfigApiController
     */
    @Autowired
    public JwtTokenUtil jwtTokenUtil;

    @Autowired
    public BondConfigApiService bondConfigApiService;

    @Autowired
    public CommonService commonService;


    @Autowired
    public HttpServletRequest servletRequest;


    /**
     * BondLedgerApiController
     */
    @Autowired
    public BondLedgerApiService bondLedgerApiService;


    /**
     * BondMobApiController
     */

    @Autowired
    public BondReqApiService bondReqApiService;


    /**
     * BondReqApiController
     */

    @Autowired
    public ExprotExcelService exprotExcelService;

}
