package com.github.cloud.loadbalancer.ribbon.config;

import com.github.cloud.loadbalancer.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author w
 * @date 2023/3/22
 */
@Configuration
@RibbonClients(defaultConfiguration = TutuRibbonClientConfiguration.class)
public class RibbonTutuAutoConfiguration {
}
