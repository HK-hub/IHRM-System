package com.ihrm.domain.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Department)实体类
 */
@Entity
@Table(name = "co_department")
@Proxy(lazy = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {
    private static final long serialVersionUID = -9084332495284489553L;
    //ID
    @Id
    private String id;
    /**
     * 父级ID
     */
    @Column(name = "parent_id", nullable = true)
    private String pid;
    /**
     * 企业ID
     */
    private String companyId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门编码，同级部门不可重复
     */
    private String code;

    /**
     * 负责人ID
     */
    private String managerId;
    /**
	*  负责人名称
	*/
    private String manager;

    /**
     * 介绍
     */
    private String introduce;
    /**
     * 创建时间
     */
    private Date createTime;
}
