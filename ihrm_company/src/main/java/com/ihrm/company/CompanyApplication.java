package com.ihrm.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

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
@SpringBootApplication
public class CompanyApplication {

    public static void main(String[] args) {

        SpringApplication.run(CompanyApplication.class,args) ;
    }


}
