package com.ihrm.domain.system;


import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName : City
 * @author : HK意境
 * @date : 2021/11/23
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Entity
@Data
@Proxy(lazy = false)
@Table(name = "bs_city")
public class City implements Serializable {

    @Id
    private String id;
    private String name;
    private Date createTime;
}
