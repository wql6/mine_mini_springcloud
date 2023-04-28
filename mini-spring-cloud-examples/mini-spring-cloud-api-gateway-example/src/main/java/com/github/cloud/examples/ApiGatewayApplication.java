package com.github.cloud.examples;


import com.github.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author w
 * @date 2022/6/26
 */
@EnableZuulProxy
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}
