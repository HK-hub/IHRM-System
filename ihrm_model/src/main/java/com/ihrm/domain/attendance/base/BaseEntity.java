package com.ihrm.domain.attendance.base;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author HK意境
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {


    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新日期
     */
    private Date updateDate;

    /**
     * 备注
     */
    private String remarks;
}
