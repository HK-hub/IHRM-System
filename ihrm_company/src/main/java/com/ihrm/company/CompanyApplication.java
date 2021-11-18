package com.ihrm.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : HK意境
 * @ClassName : CompanyApplication
 * @date : 2021/11/13 14:42
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@EntityScan("com.ihrm.domain.company")
// 开启服务发现
@EnableDiscoveryClient
// 开启服务调用
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.ihrm")
public class CompanyApplication {

    public static void main(String[] args) {

        SpringApplication.run(CompanyApplication.class,args) ;
    }


}
