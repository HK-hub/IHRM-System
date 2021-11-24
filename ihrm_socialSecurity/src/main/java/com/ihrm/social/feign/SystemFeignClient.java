package com.ihrm.social.feign;

import com.ihrm.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @ClassName : SystemFeignClient
 * @author : HK意境
 * @date : 2021/11/24
 * @description : 声明接口，通过feign调用其他微服务
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */

//声明调用的微服务名称
@FeignClient("ihrm-system")
public interface SystemFeignClient {

    /**
     * 调用微服务的接口
     */
    @RequestMapping(value="/sys/user/{id}",method = RequestMethod.GET)
    Result findById(@PathVariable(value="id") String id);

}
