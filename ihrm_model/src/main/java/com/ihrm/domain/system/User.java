package com.ihrm.domain.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ihrm.domain.poi.ExcelAttribute;
import lombok.*;
import org.crazycake.shiro.AuthCachePrincipal;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 * @author HK意境
 */
@Entity
@Table(name = "bs_user")
@Proxy(lazy = false)
@Data
@NoArgsConstructor
@ToString
public class User implements Serializable , AuthCachePrincipal {
    private static final long serialVersionUID = 4297464181093070302L;
    /**
     * ID
     */
    @Id
    private String id;
    /**
     * 手机号码
     */
    @ExcelAttribute(sort = 2)
    private String mobile;

    /**
     * 用户名称
     */
    @ExcelAttribute(sort = 1)
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 启用状态 0为禁用 1为启用
     */
    private Integer enableState;
    /**
     * 创建时间
     */
    private Date createTime;

    private String companyId;

    private String companyName;

    /**
     * 部门ID
     */
    @ExcelAttribute(sort = 6)
    private String departmentId;

    /**
     * 入职时间
     */
    @ExcelAttribute(sort = 5)
    private Date timeOfEntry;

    /**
     * 聘用形式
     */
    @ExcelAttribute(sort = 4)
    private Integer formOfEmployment;

    /**
     * 工号
     */
    @ExcelAttribute(sort = 3)
    private String workNumber;

    /**
     * 管理形式
     */
    private String formOfManagement;

    /**
     * 工作城市
     */
    private String workingCity;

    /**
     * 转正时间
     */
    private Date correctionTime;

    /**
     * 在职状态 1.在职  2.离职
     */
    private Integer inServiceStatus;

    private String departmentName;

    /**
     * level : String
     *      saasAdmin : SaaS管理员具备所有权限
     *      coAdmin : 企业管理员（创建租户企业的时候添加）
     *      user : 普通用户，需要分配角色
     */
    private String level ;

    private String staffPhoto ;

    private String timeOfDimission ;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="pe_user_role",joinColumns={@JoinColumn(name="user_id",referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="id")}
    )
    private Set<Role> roles = new HashSet<Role>();//用户与角色   多对多

    @Override
    public String getAuthCacheKey() {
        return null;
    }


    /**
     * @methodName : 有参构造函数
     * @author : HK意境
     * @date : 2021/11/18 22:27
     * @description :
     * @Todo : 用来导入 excel 文件中的文件数据，来设置user 属性
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    public User(Object[] values){

        // Excel 每一列为 ： 用户名，手机号，工号，聘用形式，入职时间，部门编码
        this.username = values[1].toString() ;
        this.mobile = values[2].toString() ;
        this.workNumber = new DecimalFormat("#").format(values[3]);
        this.formOfEmployment = ((Double)values[4]).intValue() ;
        this.timeOfEntry = (Date) values[5];
        // 部门编码 != 部门id
        this.departmentId = values[6].toString() ;

    }



}
