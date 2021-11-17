package com.ihrm.domain.system;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IDEA
 *
 *
 * Description: 菜单权限实体类
 * @author HK意境
 */
@Entity
@Table(name = "pe_permission_menu")
@Proxy(lazy = false)
@Getter
@Setter
public class PermissionMenu implements Serializable {
    private static final long serialVersionUID = -1002411490113957485L;

    /**
     * 主键
     */
    @Id
    private String id;

    //展示图标
    private String menuIcon;

    //排序号
    private String menuOrder;
}