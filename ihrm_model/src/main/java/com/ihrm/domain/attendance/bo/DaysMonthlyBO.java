package com.ihrm.domain.attendance.bo;


import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Proxy(lazy = false)
@Data
public class DaysMonthlyBO   {


    @Id
    private String day;
}
