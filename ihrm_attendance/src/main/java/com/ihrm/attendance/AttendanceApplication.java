package com.ihrm.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : HK意境
 * @ClassName : AttendanceApplication
 * @date : 2021/11/24 21:26
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */

@EntityScan(basePackages = "com.ihrm.domain")
@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.ihrm")
public class AttendanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceApplication.class , args);
    }

}
