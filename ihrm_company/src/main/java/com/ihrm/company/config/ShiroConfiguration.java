package com.ihrm.company.config;

import com.ihrm.system.realm.PublicAuthorizeRealm;
import com.ihrm.system.session.CustomSessionManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName : ShiroConfiguration
 * @author : HK意境
 * @date : 2021/11/17
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Configuration
public class ShiroConfiguration {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password ;

    // 权限，认证错误回调 API
    //private static final String ERROR_PATH = "http://localhost:9111" ;


    //1.创建realm
    @Bean
    public PublicAuthorizeRealm getRealm() {
        PublicAuthorizeRealm publicAuthorizeRealm = new PublicAuthorizeRealm();
        System.out.println("自定义 realm 成功");
        return publicAuthorizeRealm ;
    }

    //2.创建安全管理器
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getSecurityManager(PublicAuthorizeRealm realm) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);

        //将自定义的会话管理器注册到安全管理器中
        securityManager.setSessionManager(this.sessionManager());
        //将自定义的redis缓存管理器注册到安全管理器中
        securityManager.setCacheManager(cacheManager());

        System.out.println("安全管理器注册完成");

        return securityManager;
    }

    //3.配置shiro的过滤器工厂

    /**
     * 再web程序中，shiro进行权限控制全部是通过一组过滤器集合进行控制
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        //1.创建过滤器工厂
        ShiroFilterFactoryBean filterFactory = new ShiroFilterFactoryBean();
        //2.设置安全管理器
        filterFactory.setSecurityManager( securityManager);
        //3.通用配置（跳转登录页面，未授权跳转的页面）
        //跳转url地址
        filterFactory.setLoginUrl("/auth/error?code=1");
        //未授权的url
        filterFactory.setUnauthorizedUrl( "/auth/error?code=2");

        //4.设置过滤器集合
        Map<String,String> filterMap = new LinkedHashMap<>();

        //anon -- 匿名访问
        filterMap.put("/sys/login","anon");
        filterMap.put("/auth/error","anon");
        //注册

        //authc -- 认证之后访问（登录）
        filterMap.put("/**","authc");

        //perms -- 具有某中权限 (使用注解配置授权)

        // 设置过滤器Map 映射集合
        filterFactory.setFilterChainDefinitionMap(filterMap);

        return filterFactory;
    }




    /**
     * 1.redis的控制器，操作redis
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        //redisManager.setPassword("root");
        return redisManager;
    }

    /**
     * 2.sessionDao
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(redisManager());
        return sessionDAO;
    }

    /**
     * 3.会话管理器
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        //禁用cookie
        sessionManager.setSessionIdCookieEnabled(false);
        //禁用url重写   url;jsessionid=id
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    /**
     * 4.缓存管理器
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }


    //开启对shior注解的支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
