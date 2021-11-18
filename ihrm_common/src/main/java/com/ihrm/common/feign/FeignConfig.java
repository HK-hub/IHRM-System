package com.ihrm.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author : HK意境
 * @ClassName : FeignConfig
 * @date : 2021/11/18 19:24
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Configuration
public class FeignConfig {

    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/11/18 19:27
     * @description :
     * @Todo : 配置 feign 拦截器，解决请求头问题
     * @params : 
         * @param : null 
     * @return : null
     * @throws: 
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        System.out.println("requestInterceptor 注册进入容器");
        return new RequestInterceptor() {

            // 获取所有浏览器发送的请求属性，请求头复制到 feign
            @Override
            public void apply(RequestTemplate requestTemplate) {
                // 获取所有的请求属性
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

                //
                if (requestAttributes != null) {
                    HttpServletRequest request = requestAttributes.getRequest();
                    // 获取浏览器发起的请求头
                    Enumeration<String> headerNames = request.getHeaderNames();
                    if (headerNames != null) {
                        while (headerNames.hasMoreElements()) {
                            // 获取请求头名称，值
                            String name = headerNames.nextElement();
                            String value = request.getHeader(name);
                            requestTemplate.header(name, value);
                        }
                    }
                }
            }
        };
    }
}
