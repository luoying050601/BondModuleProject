package com.cscec8b.bond.module.config;


import com.cscec8b.common.module.object.CSCECUser;
import com.cscec8b.common.module.object.ldapObject.Person;
import com.cscec8b.common.module.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 保证金拦截
 */
public class BondServiceInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(BondServiceInterceptor.class);
    @Resource
    private JwtTokenUtil jwtToken;
    @Resource
    private LdapTemplate ldapTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        request.setCharacterEncoding("utf-8");
        Object token = request.getHeader("authorizationToken");
        boolean release = false;
        if (token == null) {
            release = false;
        } else {
            String tokenString = token.toString();
            logger.info("获取到的authorizationToken : " + tokenString);
            if (!"".equals(tokenString.trim())) {
                Boolean expired;
                try {
                    expired = this.jwtToken.isTokenExpired(tokenString);
                } catch (Exception var12) {
                    expired = true;
                    var12.printStackTrace();
                }

                if (!expired) {
                    CSCECUser curUser = this.jwtToken.getPersonFromToken(tokenString);
                    System.out.println("BondServiceInterceptor:" + curUser.toString());
                    if (curUser == null) {
                        release = false;
                    } else {
                        Person result = null;

                        try {
                            result = (Person) this.ldapTemplate.findOne(LdapQueryBuilder.query().where("employeeNumber").is(curUser.getUserCode()).and("uid").not().is(curUser.getUserCode()), Person.class);
                            System.out.println("Person:" + result);
                        } catch (Exception var11) {
                            result = null;
                            var11.printStackTrace();
                        }

                        if (result != null) {
                            release = true;
                        } else {
                            release = false;
                        }
                    }
                } else {
                    release = false;
                }
            } else {
                release = false;
            }
        }
        System.out.println("release:" + release);

        if (release) {
            return true;
        } else {
            response.setCharacterEncoding("utf-8");
            response.getWriter().append("{\"ResponseCode\":\"FAILED\",\"ErrorMsg\":\"token不存在或者token过期\"}");
            response.getWriter().close();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
