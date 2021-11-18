package com.ihrm.system.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.system.User;
import com.ihrm.domain.system.response.UserResult;
import com.ihrm.system.feign.DepartmentFeignClient;
import com.ihrm.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author HK意境
 */ //1.解决跨域
@CrossOrigin
//2.声明restContoller
@RestController
//3.设置父路径
@RequestMapping(value="/sys")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentFeignClient departmentFeignClient ;

    /**
     * 测试服务调用功能
     *
     */
    @RequestMapping(value = "/user/test/{id}" , method = RequestMethod.GET)
    public Result test(@PathVariable(name = "id")String id){
        System.out.println("id = : " + id);
        //Result byId = departmentFeignClient.findById(id);
        //System.out.println(byId.toString());
        Result test = departmentFeignClient.findById(id);
        System.out.println(test);
        System.out.println("进入test ");
        return test ;
    }


    /**
     * @methodName : 分配角色
     * @author : HK意境
     * @date : 2021/11/14 20:01
     * @description :
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @RequestMapping(value = "/user/assignRoles" , method = RequestMethod.PUT)
    public Result assignRoles(@RequestBody Map<String , Object> map){
        //获取被分配用户ID
        String userId = (String) map.get("id");
        //获取道角色的 id 列表
        List<String> roleIds = (List<String>) map.get("roleIds");

        // 调用 service 完成角色分配
        userService.assignRoles(userId, roleIds) ;

        return new Result(ResultCode.SUCCESS) ;
    }


    /**
     * 保存
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Result save(@RequestBody User user) {
        //1.设置保存的企业id
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        //2.调用service完成保存企业
        userService.save(user);
        //3.构造返回结果
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询企业的部门列表
     * 指定企业id
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Result findAll(int page, int size, @RequestParam Map map) {
        //1.获取当前的企业id
        map.put("companyId",companyId);
        //2.完成查询
        Page<User> pageUser = userService.findAll(map,page,size);
        //3.构造返回结果
        PageResult pageResult = new PageResult(pageUser.getTotalElements(),pageUser.getContent());
        return new Result(ResultCode.SUCCESS, pageResult);
    }

    /**
     * 根据ID查询user
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id") String id) {

        // 添加用户具有的角色对象，VO 对象
        User user = userService.findById(id);
        UserResult userResult = new UserResult(user);

        return new Result(ResultCode.SUCCESS, userResult);
    }

    /**
     * 修改User
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id, @RequestBody User user) {
        //1.设置修改的部门id
        user.setId(id);
        //2.调用service更新
        userService.update(user);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE , name = "API-USER-DELETE")
    public Result delete(@PathVariable(value = "id") String id) {
        userService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

}
