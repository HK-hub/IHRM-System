package com.ihrm.social.feign;

import com.ihrm.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName : EmployeeFeignClient
 * @author : HK意境
 * @date : 2021/11/24
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
//声明调用的微服务名称
@FeignClient("ihrm-employee")
public interface EmployeeFeignClient {

    /**
     * 获取个人信息
     * @param uid 用户id
     * @return 个人信息
     */
    /**
     * 员工个人信息读取
     */
    @RequestMapping(value = "employees/{id}/personalInfo", method = RequestMethod.GET)
    public Result findPersonalInfo(@PathVariable(name = "id",value = "id") String uid);
}
