package com.ihrm.company.config;

import com.ihrm.common.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : HK意境
 * @ClassName : BeanConfig
 * @date : 2021/11/13 15:20
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Configuration
public class BeanConfig {

    @Bean
    public IdWorker getIdWorker(){
        return new IdWorker() ;
    }


}
