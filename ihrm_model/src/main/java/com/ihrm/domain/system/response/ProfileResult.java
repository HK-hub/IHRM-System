package com.ihrm.domain.system.response;

import com.ihrm.domain.system.Permission;
import com.ihrm.domain.system.Role;
import com.ihrm.domain.system.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.*;

/**
 * @author HK意境
 */
@Data
@NoArgsConstructor
public class ProfileResult implements Serializable, AuthCachePrincipal {

    private String mobile;
    private String username;
    private String company;
    private String companyId ;
    private String userId ;
    private Map<String,Object> roles = new HashMap<>();


    public ProfileResult(User user, List<Permission> perms){

        this.mobile = user.getMobile();
        this.username = user.getUsername();
        this.company = user.getCompanyName();
        this.companyId = user.getCompanyId() ;
        this.userId = user.getId() ;

        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();

        // 设置分类权限
        for (Permission perm : perms) {
            // 获取权限编码
            String code = perm.getCode();
            // 权限类型
            if(perm.getType() == 1) {
                menus.add(code);
            }else if(perm.getType() == 2) {
                points.add(code);
            }else {
                apis.add(code);
            }
        }

        this.roles.put("menus",menus);
        this.roles.put("points",points);
        this.roles.put("apis",apis);

    }


    public ProfileResult(User user) {
        this.mobile = user.getMobile();
        this.username = user.getUsername();
        this.company = user.getCompanyName();
        this.companyId = user.getCompanyId() ;
        this.userId = user.getId() ;

        // 获取用户角色
        Set<Role> roles = user.getRoles();
        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();

        for (Role role : roles) {
            // 获取用户权限
            Set<Permission> perms = role.getPermissions();
            // 设置分类权限
            for (Permission perm : perms) {
                // 获取权限编码
                String code = perm.getCode();
                // 权限类型
                if(perm.getType() == 1) {
                    menus.add(code);
                }else if(perm.getType() == 2) {
                    points.add(code);
                }else {
                    apis.add(code);
                }
            }
        }

        this.roles.put("menus",menus);
        this.roles.put("points",points);
        this.roles.put("apis",apis);
    }

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
