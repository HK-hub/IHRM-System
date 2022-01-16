package com.ihrm.domain.attendance.bo;

import lombok.Data;
import org.hibernate.annotations.Proxy;

/**
 * @author HK意境
 */
@Proxy(lazy = false)
@Data
public class AtteTotalsBO {

    //待处理审批数量
    private Integer tobeTaskCount;

    //当前报表月份
    private Integer monthOfReport;


}
