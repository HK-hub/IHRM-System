package com.ihrm.attendance.controller;

import com.ihrm.attendance.service.ConfigurationService;
import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.attendance.entity.AttendanceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : HK意境
 * @ClassName : ConfigController
 * @date : 2021/11/24 21:50
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/cfg")
public class ConfigController extends BaseController {

    @Autowired
    private ConfigurationService configurationService ;


    /**
     * @methodName : 获取考勤设置
     * @author : HK意境
     * @date : 2021/11/24 21:53
     * @description :
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @RequestMapping(value = "/atte/item", method = RequestMethod.POST)
    public Result attendanceConfig(String departmentId){

        AttendanceConfig attendanceConfig = configurationService.getAttendanceConfig(this.companyId, departmentId);

        return new Result(ResultCode.SUCCESS, attendanceConfig);

    }


    @RequestMapping(value = "/atte" , method = RequestMethod.PUT)
    public Result saveConfig(@RequestBody AttendanceConfig attendanceConfig){

        attendanceConfig.setCompanyId(this.companyId);

        configurationService.saveAttendanceConfig(attendanceConfig);
        return new Result(ResultCode.SUCCESS);
    }





}
