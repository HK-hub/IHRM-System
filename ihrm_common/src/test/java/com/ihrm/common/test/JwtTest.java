package com.ihrm.common.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

/**
 * @author : HK意境
 * @ClassName : JwtTest
 * @date : 2021/11/14 20:42
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class JwtTest {

    private static final String salt = "ihrm_system" ;


    @Test
    public void createJwtTokenTest(){

        // 生成 token
        JwtBuilder jwtBuilder = Jwts.builder().setId("88").setSubject("HK意境")
                .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, salt);
        String token = jwtBuilder.compact();
        System.out.println(token);

    }

    @Test
    public void parsingTokenTest(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4OCIsInN1YiI6IkhL5oSP5aKDIiwiaWF0IjoxNjM2ODkzNjUxfQ.SBzlrq5iJVC8824kESVRftBXzQ0Hs5SKK6pZ2oXF-q0" ;
        Claims body = Jwts.parser().setSigningKey(salt).parseClaimsJws(token).getBody();

        String id = body.getId();
        Date issuedAt = body.getIssuedAt();
        String subject = body.getSubject();
        System.out.println(id);
        System.out.println(subject);
        System.out.println(issuedAt);

    }


}
