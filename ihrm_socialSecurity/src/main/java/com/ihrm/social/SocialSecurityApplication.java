package com.ihrm.social;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : HK意境
 * @ClassName : SocialSecurityApplication
 * @date : 2021/11/24 12:28
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
@EntityScan("com.ihrm.domain")
@SpringBootApplication(scanBasePackages = "com.ihrm")
public class SocialSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialSecurityApplication.class , args);
    }

}
