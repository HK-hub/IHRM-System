package com.ihrm.system.realm;

import com.ihrm.domain.system.Permission;
import com.ihrm.domain.system.User;
import com.ihrm.domain.system.response.ProfileResult;
import com.ihrm.system.service.PermissionService;
import com.ihrm.system.service.UserService;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : UserRealm
 * @date : 2021/11/17 14:01
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class UserRealm extends PublicAuthorizeRealm {

    @Autowired
    private UserService userService ;

    @Autowired
    private PermissionService permissionService ;

    /**
     * @methodName : 认证，具体进行登录认证
     * @author : HK意境
     * @date : 2021/11/17 14:04
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
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        // 获取到手机号
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String mobile = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());

        // 根据手机号查询用户
        User byMobile = userService.findByMobile(mobile);
        //System.out.println("查询出来的User：username = " + byMobile.getUsername());
        //System.out.println("查询出来的User：password = " + byMobile.getPassword());
        //System.out.println("token 中的密码： " + password);
        // 判断用户是否存在，密码是否匹配 : 注意空指针异常
        if (byMobile != null && byMobile.getPassword().equals(password)) {
            // 用户名密码正确

            // 构造安全数据 ： 用户的基本数据，权限信息
            ProfileResult profileResult = null ;

            if("user".equals(byMobile.getLevel())) {
                profileResult = new ProfileResult(byMobile);
            }else {
                Map map = new HashMap();
                if("coAdmin".equals(byMobile.getLevel())) {
                    map.put("enVisible","1");
                }
                List<Permission> list = permissionService.findAll(map);
                profileResult = new ProfileResult(byMobile,list);
            }
            //构造方法：安全数据，密码，realm域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(profileResult,byMobile.getPassword(),this.getName());
            return info;
        }else{
            //返回null，会抛出异常，标识用户名和密码不匹配
            return null;
        }
    }
}
