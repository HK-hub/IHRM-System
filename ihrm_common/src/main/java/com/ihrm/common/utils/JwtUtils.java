package com.ihrm.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author : HK意境
 * @ClassName : JwtUtils
 * @date : 2021/11/14 20:37
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt.config")
public class JwtUtils {

    // 签名的私钥，随机盐
    private String salt ;
    // 签名失效时间: 三天
    private Long expireTime ;


    /**
     * @methodName : createToken
     * @author : HK意境
     * @date : 2021/11/14 20:55
     * @description : 生成用户 token ，设置认证token
     * @Todo :
     * @params : 
         * @param : id : 登录用户ID ——> setId()
        *  @param : subject : 登录用户名  ——> setSubject()
     * @return : null
     * @throws: 
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    public String createJwtToken(String id , String username , Long expireTime , Map<String, Object> map){

        // 设置失效时间
        long now = System.currentTimeMillis();
        if (Objects.isNull(expireTime)){
            expireTime = this.expireTime ;
        }
        expireTime += now ;

        // 创建 jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, salt);

        // 根据 Map 设置 claims

        for (Map.Entry<String, Object> entry : map.entrySet()){
            jwtBuilder.claim(entry.getKey(), entry.getValue());
        }

        // 设置失效时间
        Jwts.builder().setExpiration(new Date(expireTime));

        // 创建 token
        String token = jwtBuilder.compact();
        return token ;
    }


    /**
     * @methodName : parseJwtToken
     * @author : HK意境
     * @date : 2021/11/14 21:08
     * @description : 解析 token 获取 body 部分的 claims
     * @Todo :
     * @params :
         * @param : String token
     * @return : Claims
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    public Claims parseJwtToken(String token){

        Claims body = Jwts.parser().setSigningKey(salt).parseClaimsJws(token).getBody();

        return body ;
    }


}
