package com.ihrm.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : HK意境
 * @ClassName : AuditApplication
 * @date : 2022/1/16 20:14
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */


@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.ihrm"} ,exclude = SecurityAutoConfiguration.class)
public class AuditApplication {

    public static void main(String[] args) {

        SpringApplication.run(AuditApplication.class, args);

    }



}
