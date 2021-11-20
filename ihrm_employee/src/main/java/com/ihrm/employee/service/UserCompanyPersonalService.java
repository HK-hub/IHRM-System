package com.ihrm.employee.service;

import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.employee.UserCompanyPersonal;
import com.ihrm.domain.employee.response.EmployeeReportResult;
import com.ihrm.employee.dao.UserCompanyPersonalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 */
@Service
public class UserCompanyPersonalService {
    @Autowired
    private UserCompanyPersonalDao userCompanyPersonalDao;

    public void save(UserCompanyPersonal personalInfo) {
        userCompanyPersonalDao.save(personalInfo);
    }

    public UserCompanyPersonal findById(String userId) {
        return userCompanyPersonalDao.findByUserId(userId);
    }

    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/11/19 15:49
     * @description :
     * @Todo : 查询某月的入职，离职人事情况
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    public List<EmployeeReportResult> findByReport(String companyId, String month) {

        List<EmployeeReportResult> results = userCompanyPersonalDao.findByReport(companyId,month);
        return results ;
    }
}
