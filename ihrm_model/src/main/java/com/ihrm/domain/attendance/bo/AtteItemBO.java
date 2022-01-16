package com.ihrm.domain.attendance.bo;



import com.ihrm.domain.attendance.entity.Attendance;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import java.util.List;

@Data
@Proxy(lazy = false)
public class AtteItemBO {

    //编号
    private String	id;
    //名称
    private String	username;
    //工号
    private String	workNumber;
    //部门
    private String	departmentName;
    //手机
    private String	mobile;
    //考勤记录
    private List<Attendance> attendanceRecord ;
    //部门ID
    private String departmentId;

}
