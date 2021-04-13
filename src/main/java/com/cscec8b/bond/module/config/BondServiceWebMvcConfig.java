package com.cscec8b.bond.module.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * 配置拦截的服务
 */
@Configuration
public class BondServiceWebMvcConfig extends WebMvcConfigurationSupport {

    Logger logger = LoggerFactory.getLogger(BondServiceWebMvcConfig.class);

    @Bean
    BondServiceInterceptor bondService() {
        return new BondServiceInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("-----注册拦截器，添加拦截路径----");
        registry.addInterceptor(bondService())
                .addPathPatterns(
                        "/BondService/BondClaimApi/**",
                        "/BondService/BondConfigApi/**",
                        "/BondService/BondLedger/**",
                        "/BondService/BondReqApi/**",
                        "/BondService/ExportExcel/**",
                        "/BondClaimApi/**",
                        "/BondConfigApi/**",
                        "/BondLedger/**",
                        "/BondReqApi/**",
                        "/ExportExcel/**")
                .excludePathPatterns(
                        "/BondService/BondReqApi/submitBondReqDetailWithoutToken"
                );

    }

}
