package com.ihrm.gateway.filter;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author : HK意境
 * @ClassName : LoginFilter
 * @date : 2021/11/23 22:02
 * @description : zuul 网关过滤器进行鉴权操作
 * @Todo : 继承 zuul 网关的原生过滤器，实现统一鉴权
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class LoginFilter extends ZuulFilter {


    /****
     * 定义过滤器类型的：
     *      pre: 请求转发之前，在执行路由请求之前
     *      routing: 在路由请求时才调用
     *      post: 在路由阶段和错误过滤器之后执行
     *      error: 处理请求出现异常执行
     * @return
     */
    @Override
    public String filterType() {

        return "pre";
    }


    /***
     * 过滤器执行顺序：
     *      定义过滤器的优先级，数字越小优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 2;
    }


    /**
     * 判断过滤器是否需要执行：
     *      可以对某些请求进行过滤不进行过滤，不执行过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {

        return true;
    }



    /**
     * @methodName : run()
     * @author : HK意境
     * @date : 2021/11/23 22:08
     * @description : 过滤器中负责的具体的业务逻辑
     * @Todo : 在zuul 网关中进行对 token 进行校验，校验 token 是否存在，并且具有权限
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Override
    public Object run() throws ZuulException {
        log.info("[zuulFilter.run() : 执行 {}]",new Date().toString());

        // 获取request 对象
        // 获取请求上下文
        // 通过上下文对象获取request 对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        // 获取请求头 token
        String token = request.getHeader("Authorization");

        if (token == null || StringUtils.isEmpty(token) || "".equals(token)) {
            // 没有 token 信息，进行登录， 拦截
            requestContext.setSendZuulResponse(false);
            // 放回错误码 401
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            requestContext.setResponseBody(new Result(ResultCode.UNAUTHENTICATED).toString());
        }
        // 继续往后执行
        return null;
    }
}
