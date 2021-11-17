package com.ihrm.common.interceptor;

import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : HK意境
 * @ClassName : JwtInterceptor
 * @date : 2021/11/15 17:00
 * @description : 简化获取 token 数据的代码编写，统一的用户权限校验——登录 ； 判断用户是否具有访问当前接口的权限
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils ;


    /**
     * 进入到控制器方法之前执行的内容
     * @param request
     * @param response
     * @param handler
     * @return Boolean ： true 可以继续执行接下来的步骤， false : 拦截不予放行
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取 token 数据
        String authorization = request.getHeader("Authorization");
        // 请求头信息是否为空
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer ")){

            // 获取token ，替换字符串
            String token = authorization.replace("Bearer ", "");
            // 解析 token 数据, 获取 claims
            Claims claims = jwtUtils.parseJwtToken(token);

            // 将 claims 绑定到 request 域中
            if (claims != null) {
                // 通过 claims 获取当用户的可访问的AOI权限字符串
                String apis = (String) claims.get("apis");
                // 通过 handler 获取请求方法名称
                HandlerMethod handlerMethod = (HandlerMethod) handler ;
                // 获取接口上的注解
                RequestMapping methodAnnotation = handlerMethod.getMethodAnnotation(RequestMapping.class);
                // 获取接口注解中的 name 属性
                String name = methodAnnotation.name();
                // 当前用户是否具有对应的请求权限
                if (apis.contains(name)) {
                    request.setAttribute("user_claims", claims);
                    return true ;
                }else {
                    // 权限不足
                    throw new CommonException(ResultCode.UNAUTHORISE);
                }

            }

        }
        // 未登录
        throw new CommonException(ResultCode.UNAUTHENTICATED) ;
        //return super.preHandle(request, response, handler);
    }


    /**
     * 执行控制器方法之后执行的内容
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }


    /**
     * 响应结束之前执行的内容
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
