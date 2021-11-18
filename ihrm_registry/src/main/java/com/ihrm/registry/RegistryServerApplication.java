package com.ihrm.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author : HK意境
 * @ClassName : RegistryServerApplication
 * @date : 2021/11/18 16:39
 * @description : 服务注册中心服务端
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegistryServerApplication.class, args) ;
    }
}
