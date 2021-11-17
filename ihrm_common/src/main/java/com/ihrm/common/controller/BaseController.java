package com.ihrm.common.controller;


import com.ihrm.domain.system.response.ProfileResult;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : HK意境
 * @ClassName : BaseController
 * @date : 2021/11/13 20:30
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class BaseController {


    public HttpServletRequest httpServletRequest ;
    public HttpServletResponse httpServletResponse ;

    protected String companyId ;
    protected String companyName ;
    protected Claims claims ;



    // 使用 JWT 方式获取
   /* @ModelAttribute
    public void setResponseAndRequest(HttpServletRequest request , HttpServletResponse response){

        this.httpServletRequest = request ;
        this.httpServletResponse = response ;

        Object user_claims = request.getAttribute("user_claims");

        if (user_claims != null) {
            this.claims = (Claims) user_claims ;
            this.companyName = (String) claims.get("companyName") ;
            this.companyId = (String) claims.get("companyId");
        }


        //this.companyId = "1" ;
        //this.companyName = "重庆理工大学";
    }
*/


    // 使用 shiro 获取
    @ModelAttribute
    public void setResponseAndRequest(HttpServletRequest request, HttpServletResponse response){

        this.httpServletRequest = request ;
        this.httpServletResponse = response ;

        // 获取session 中的安全数据
        Subject subject = SecurityUtils.getSubject();

        // 通过 subject 获取所有的安全数据集合
        PrincipalCollection principals = subject.getPrincipals();

        if (principals != null && !principals.isEmpty()) {
            // 获取主体安全数据
            ProfileResult primaryPrincipal = (ProfileResult) principals.getPrimaryPrincipal();

            this.companyId = primaryPrincipal.getCompanyId() ;
            this.companyName = primaryPrincipal.getCompany() ;

        }
    }



}
