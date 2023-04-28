package com.github.cloud.netflix.zuul;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用zuul网关
 *
 * @author w
 * @date 2022/6/23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ZuulServerAutoConfiguration.class)
public @interface EnableZuulProxy {
}
