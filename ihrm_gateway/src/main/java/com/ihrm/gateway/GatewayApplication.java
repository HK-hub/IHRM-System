package com.ihrm.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author : HK意境
 * @ClassName : GatewayApplication
 * @date : 2021/11/23 21:19
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
// 开启服务发现功能
@EnableDiscoveryClient
// 开启 zuul 网关功能
@EnableZuulProxy
@SpringBootApplication(scanBasePackages = "com.ihrm")
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args) ;

    }

}



