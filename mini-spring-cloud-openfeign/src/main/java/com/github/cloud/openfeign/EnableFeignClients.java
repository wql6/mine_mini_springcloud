package com.github.cloud.openfeign;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用Feign
 *
 * @author w
 * @date 2022/4/7
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(FeignClientsRegistrar.class)
public @interface EnableFeignClients {
}
