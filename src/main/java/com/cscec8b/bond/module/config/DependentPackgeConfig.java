package com.cscec8b.bond.module.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 额外加载自定义jar包中的类
 */
@Configuration
@ComponentScan(basePackages = {"com.cscec8b.common.module.config",
        "com.cscec8b.common.module.dao",
        "com.cscec8b.common.module.object",
        "com.cscec8b.common.module.service",
        "com.cscec8b.common.module.util"
})
public class DependentPackgeConfig {
}
