package com.ihrm.domain.attendance.bo;

import com.ihrm.domain.attendance.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Entity
@Proxy(lazy = false)
@Data
public class ArchiveListBO extends BaseEntity implements Serializable {

    @Id
    private String id;


    /**
     * 总人数
     */
    private String totalPeople;

    /**
     * 全勤认数
     */
    private String fullAttendancePeople;


    /**
     * 月份
     */
    private String month;


}
