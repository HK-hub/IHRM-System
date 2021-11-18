package com.ihrm.system.feign;

import com.ihrm.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : HK意境
 * @ClassName : DepartmentFeignClient
 * @date : 2021/11/18 19:07
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@FeignClient("ihrm-company")
public interface DepartmentFeignClient {

    /**
     * 调用微服务的接口
     */
    @RequestMapping(value="/company/department/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable(value="id") String id);

    // 测试feign 调用
    @RequestMapping(value = "/company/department/test",method = RequestMethod.GET)
    public Result test();

}
