package com.ihrm.domain.attendance.bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 考勤统计结果
 */
@Entity
@Proxy(lazy = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtteStatisBO implements Serializable {

    @Id
    private String id;


    private String day;


    private Integer adtStatu;

}
