package com.ihrm.attendance.service;


import com.ihrm.attendance.mapper.*;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.attendance.entity.AttendanceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


/**
 * @ClassName : ConfigurationService
 * @author : HK意境
 * @date : 2021/11/24
 * @description : 用来进行综合配置的 service，
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Service
public class ConfigurationService{

    @Autowired
    private AttendanceConfigDao attendanceConfigDao;

    @Autowired
    private LeaveConfigDao leaveConfigDao;

    @Autowired
    private DeductionDictDao deductionDictDao;

    @Autowired
    private ExtraDutyConfigDao extraDutyConfigDao;

    @Autowired
    private ExtraDutyRuleDao extraDutyRuleDao;

    @Autowired
    private DayOffConfigDao dayOffConfigDao;

    @Autowired
    private IdWorker idWorker;



    /**
     * @methodName : 获取考勤配置信息
     * @author : HK意境
     * @date : 2021/11/24 21:55
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
    public AttendanceConfig getAttendanceConfig(String companyId, String departmentId) {

        return attendanceConfigDao.findByCompanyIdAndDepartmentId(companyId, departmentId);

    }



    /**
     * @methodName : 保存或更新考勤设置
     * @author : HK意境
     * @date : 2021/11/25 10:28
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
    public void saveAttendanceConfig(AttendanceConfig attendanceConfig) {

        // 查询是否存在
        AttendanceConfig byCompanyIdAndDepartmentId = attendanceConfigDao.findByCompanyIdAndDepartmentId(attendanceConfig.getCompanyId(), attendanceConfig.getDepartmentId());

        if (byCompanyIdAndDepartmentId != null) {
            // 如果存在——> 更新
            attendanceConfig.setId(byCompanyIdAndDepartmentId.getId());
            attendanceConfig.setUpdateDate(new Date());
            attendanceConfig.setUpdateBy(byCompanyIdAndDepartmentId.getCompanyId() + "-" + byCompanyIdAndDepartmentId.getDepartmentId());
        }else{
            // 不存在——> 保存，
            attendanceConfig.setId(idWorker.nextId() + "");
            attendanceConfig.setCreateDate(new Date());
            attendanceConfig.setCreateBy(byCompanyIdAndDepartmentId.getCompanyId() + "-" + byCompanyIdAndDepartmentId.getDepartmentId());
            attendanceConfig.setUpdateDate(attendanceConfig.getCreateDate());
            attendanceConfig.setUpdateBy(byCompanyIdAndDepartmentId.getCompanyId() + "-" + byCompanyIdAndDepartmentId.getDepartmentId());

        }

        attendanceConfigDao.save(attendanceConfig);

    }
}
