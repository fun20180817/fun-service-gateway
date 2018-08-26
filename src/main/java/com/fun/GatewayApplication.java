/**
 * Company:	F-space
 * Project Name:fun-service-gateway 
 * File Name:GatewayApplication.java 
 * Package Name:com.fun 
 * Date:2018年8月20日下午5:39:18 
 * Copyright (C) 2016,F-space. All rights reserved.
 * 
 */
package com.fun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/** 
 * ClassName: GatewayApplication <br/> 
 * Function: server starter. <br/> 
 * date: 2018年8月20日 下午5:39:18 <br/> 
 * 
 * @author lishuai11 
 * @version  
 * @since JDK 1.8
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
