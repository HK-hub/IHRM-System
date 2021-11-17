package com.ihrm.system.session;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.Serializable;

/**
 * @author : HK意境
 * @ClassName : CustomSessionManager
 * @date : 2021/11/17 13:47
 * @description : 自定义 会话管理器
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
public class CustomSessionManager extends DefaultWebSessionManager implements WebSessionManager {



    private final String Authorization = "Authorization";


    /**
     * @methodName : getSessionId()
     * @author : HK意境
     * @date : 2021/11/17 13:48
     * @description : 头信息中具有 sessionID ，请求头：Authorization ：sessionId
     * @Todo : 指定 sessionID 的获取方式，
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        // 获取请求头 Authorization 头信息
        String sessionId = WebUtils.toHttp(request).getHeader("Authorization");
        //String sessionId = WebUtils.toHttp(request).getSession().getId();


        if (StringUtils.isEmpty(sessionId)) {
            // 没有携带 sessionId , 生成新的 sessionId
            // 若header获取不到token则尝试从cookie中获取
            return super.getSessionId(request, response);
        } else {
            // 获取请求头信息进行加工
            sessionId = sessionId.replaceAll("Bearer ", "");
            // 返回 sessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId ;
        }
    }





}
