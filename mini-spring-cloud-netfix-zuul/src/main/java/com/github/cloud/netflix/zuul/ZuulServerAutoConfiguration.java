package com.github.cloud.netflix.zuul;

import com.github.cloud.netflix.zuul.filters.RouteLocator;
import com.github.cloud.netflix.zuul.filters.SimpleRouteLocator;
import com.github.cloud.netflix.zuul.filters.ZuulProperties;
import com.github.cloud.netflix.zuul.filters.post.SendResponseFilter;
import com.github.cloud.netflix.zuul.filters.pre.PreDecorationFilter;
import com.github.cloud.netflix.zuul.filters.route.RibbonRoutingFilter;
import com.github.cloud.netflix.zuul.metrics.EmptyCounterFactory;
import com.github.cloud.netflix.zuul.metrics.EmptyTracerFactory;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.filters.FilterRegistry;
import com.netflix.zuul.http.ZuulServlet;
import com.netflix.zuul.monitoring.CounterFactory;
import com.netflix.zuul.monitoring.TracerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * zuul API网关自动配置类
 *
 * @author w
 * @date 2022/6/23
 */
@Configuration
@EnableConfigurationProperties({ZuulProperties.class})
public class ZuulServerAutoConfiguration {

	@Autowired
	protected ZuulProperties zuulProperties;

	/**
	 * 注册ZuulServlet，用于拦截处理http请求
	 */
	@Bean
	public ServletRegistrationBean zuulServlet() {
		return new ServletRegistrationBean<>(new ZuulServlet(), zuulProperties.getServletPath());
	}

	/**
	 * 路由定位器
	 */
	@Bean
	public RouteLocator simpleRouteLocator() {
		return new SimpleRouteLocator(zuulProperties);
	}

	/**
	 * pre类型过滤器，根据RouteLocator来进行路由规则的匹配
	 */
	@Bean
	public ZuulFilter preDecorationFilter(RouteLocator routeLocator) {
		return new PreDecorationFilter(routeLocator);
	}

	/**
	 * route类型过滤器，使用ribbon负载均衡器进行http请求
	 */
	@Bean
	ZuulFilter ribbonRoutingFilter(LoadBalancerClient loadBalancerClient) {
		return new RibbonRoutingFilter(loadBalancerClient);
	}

	/**
	 * post类型过滤器，向客户端输出响应报文
	 */
	@Bean
	ZuulFilter sendResponseFilter() {
		return new SendResponseFilter();
	}

	/**
	 * 注册过滤器
	 */
	@Bean
	public FilterRegistry filterRegistry(Map<String, ZuulFilter> filterMap) {
		FilterRegistry filterRegistry = FilterRegistry.instance();
		filterMap.forEach((name, filter) -> {
			filterRegistry.put(name, filter);
		});
		return filterRegistry;
	}

	//监控相关类，不必关注-------------------------------

	@Bean
	public CounterFactory emptyCounterFactory() {
		CounterFactory.initialize(new EmptyCounterFactory());
		return CounterFactory.instance();
	}

	@Bean
	public TracerFactory emptyTracerFactory() {
		TracerFactory.initialize(new EmptyTracerFactory());
		return TracerFactory.instance();
	}
}
