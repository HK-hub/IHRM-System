package com.ihrm.system.realm;

import com.ihrm.domain.system.response.ProfileResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

/**
 * @author : HK意境
 * @ClassName : PublicAuthorizeRealm
 * @date : 2021/11/17 13:57
 * @description : 公共的获取授权信息，安全信息的 realm 域，主要完成授权，获取安全数据任务
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class PublicAuthorizeRealm extends AuthorizingRealm {


    /***
     * 指定 realm 名称
     * @param name
     */
    @Override
    public void setName(String name) {
        super.setName("ihrmRealm");
    }

    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/11/17 13:59
     * @description : 授权，获取安全数据
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
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 获取安全数据
        ProfileResult result = (ProfileResult) principalCollection.getPrimaryPrincipal();
        // 获取权限信息
        Set<String> apis = (Set<String>) result.getRoles().get("apis");
        // 构造权限信息，返回值
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(apis);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
