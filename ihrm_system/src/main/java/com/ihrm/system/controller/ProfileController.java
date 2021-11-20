package com.ihrm.system.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.common.utils.JwtUtils;
import com.ihrm.domain.system.response.ProfileResult;
import com.ihrm.system.service.PermissionService;
import com.ihrm.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


/**
 * @author : HK意境
 * @ClassName : ProfileController
 * @date : 2021/11/14 21:23
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@RestController
@RequestMapping("/sys")
public class ProfileController extends BaseController {

    @Autowired
    private UserService userService ;
    @Autowired
    private JwtUtils jwtUtils ;
    @Autowired
    private PermissionService permissionService ;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String, String> loginMap) {

        String mobile = loginMap.get("mobile");
        String password = loginMap.get("password");

        // Shiro 登录
        //1. 构造登录令牌
        //2. 获取 subject
        //3. 调用 login 方法
        //4. 获取 sessionId
        //5. 构造返回对象
       try {
           // 加密密码
           password = new Md5Hash(password, mobile, 3).toString();
           //1. 构造登录令牌
           UsernamePasswordToken token = new UsernamePasswordToken(mobile,password);

           //2. 获取 subject
           Subject subject = SecurityUtils.getSubject();
           //3. 调用 login() 进入 realm 进行认证
           subject.login(token);
           String sessionId = (String)subject.getSession().getId();

           //构造返回结果
           return new Result(ResultCode.SUCCESS, sessionId);

       }catch(AuthenticationException exception){
           return new Result(ResultCode.MOBILEORPASSWORDERROR);
       }
        // JWT  登录方式
        /*User byMobile = userService.findByMobile(mobile);
        if (Objects.nonNull(byMobile)) {
            // 校验密码
            if (password.equals(byMobile.getPassword())) {
                // 登录成功
                StringBuilder apiPermissionSB = new StringBuilder();

                // 获取所有可以访问的API 权限
                for (Role role : byMobile.getRoles()) {
                    for (Permission permission : role.getPermissions()) {
                        if (permission.getType() == PermissionConstants.PERMISSION_API) {
                              apiPermissionSB.append(permission.getCode()).append(",");
                        }
                    }
                }

                Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
                // 添加可访问的API权限字符串
                stringObjectHashMap.put("apis",apiPermissionSB.toString());
                stringObjectHashMap.put("companyId", byMobile.getCompanyId());
                stringObjectHashMap.put("companyName", byMobile.getCompanyName());

                String jwtToken = jwtUtils.createJwtToken(byMobile.getId(), byMobile.getUsername(), null, stringObjectHashMap);
                return new Result(ResultCode.SUCCESS,jwtToken);
            }
        }

        // 登录失败
        return new Result(ResultCode.MOBILEORPASSWORDERROR) ;*/
    }


    /**
     * @methodName : profile
     * @author : HK意境
     * @date : 2021/11/14 22:06
     * @description : 获取用户信息，包括角色权限信息
     * @Todo :
     * @params : 
         * @param : null 
     * @return : ProfileResult 返回的是 User 的 VO 对象
     * @throws: 
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public Result profile(HttpServletRequest request) throws CommonException {

        /**
         * 应为我们已经把所有的 sessionId 都放到了 redis 缓存中，所以我们只需要获取 sessionId 中的 安全数据即可
         *
         */

        // 获取session 中的安全数据
        Subject subject = SecurityUtils.getSubject();

        // 通过 subject 获取所有的安全数据集合
        PrincipalCollection principals = subject.getPrincipals();

        // 获取主体安全数据
        ProfileResult result = (ProfileResult) principals.getPrimaryPrincipal();

        return new Result(ResultCode.SUCCESS, result);

        /*// 从请求头信息中获取token信息： Authorization=Bearer + " " + token
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            throw new CommonException(ResultCode.UNAUTHENTICATED) ;
        }

        // 解析 token
        String token = authorization.replace("Bearer ", "");

        // 获取 claims
        Claims claims = jwtUtils.parseJwtToken(token);*/
        //Claims claims = (Claims) request.getAttribute("user_claims");

        // 使用 Realm 域获取
        /*String userId = this.claims.getId() ;
        // 业务查询
        User byId = userService.findById(userId);

        // 根据不同的用户级别获取用户权限
        ProfileResult profileResult = null ;

        if ("user".equals(byId.getLevel())){
            profileResult = new ProfileResult(byId) ;
        }else {
            Map map = new HashMap();
            List<Permission> list = null ;

            if ("coAdmin".equals(byId.getLevel())){
                map.put("enVisible","1");
            }
            List allPermissions = permissionService.findAll(map);
            profileResult = new ProfileResult(byId, allPermissions);
        }*/
    }

    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/11/20 15:23
     * @description : 七牛云存储，实现用户头像
     * @Todo : 上传用户头像，实现用户头像自定义
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @RequestMapping(value = "/user/upload/{id}",method = RequestMethod.GET)
    public Result avatarImage(@PathVariable(name = "id")String id, @RequestParam(name = "file")MultipartFile file) throws IOException {

        // 调用 service 保存图片，
        String url = userService.uploadImage(id,file);

        // 返回数据：url, data
        Result result = new Result(ResultCode.SUCCESS, url);
        return result ;
    }



}