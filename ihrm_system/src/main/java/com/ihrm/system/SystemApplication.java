package com.ihrm.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author : HK意境
 * @ClassName : SystemApplication
 * @date : 2021/11/14 14:13
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@EntityScan(value = "com.ihrm.domain.system")
@SpringBootApplication(scanBasePackages = "com.ihrm")
public class SystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(SystemApplication.class, args) ;

    }


}
