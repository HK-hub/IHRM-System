package com.ihrm.employee.service;

import com.ihrm.common.utils.BeanMapUtils;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.employee.UserCompanyJobs;
import com.ihrm.domain.employee.UserCompanyPersonal;
import com.ihrm.domain.employee.response.EmployeeReportResult;
import com.ihrm.employee.dao.UserCompanyJobsDao;
import com.ihrm.employee.dao.UserCompanyPersonalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Service
public class UserCompanyPersonalService {
    @Autowired
    private UserCompanyPersonalDao userCompanyPersonalDao;
    @Autowired
    private UserCompanyJobsDao userCompanyJobsDao;

    @Value("${qiniu.oss.domain}")
    private String ossDomain;

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



    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/11/21 16:08
     * @description : 更具员工ID 导出个人信息 pdf
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    public Map<String, Object> createProfilePdf(String id) {

        // 2. 构造数据源
        // 用户详情数据
        UserCompanyPersonal personal = userCompanyPersonalDao.findById(id).get();
        // 用户岗位信息
        UserCompanyJobs jobs = userCompanyJobsDao.findById(id).get();
        // 用户头像: domain + imageName
        //                   imageName = user.getUsername()+"-"+user.getMobile();
        String username = personal.getUsername();
        String mobile = personal.getMobile();
        String staffPhoto = ossDomain + username + "-" + mobile ;

        // 3. 填充 pdf模板数据，并输出 pdf
        Map<String, Object> params = new HashMap<>();

        //填充数据
        Map<String, Object> personalMap = BeanMapUtils.beanToMap(personal);
        Map<String, Object> jobsMap= BeanMapUtils.beanToMap(jobs);
        params.put("staffPhoto", staffPhoto);
        params.putAll(personalMap);
        params.putAll(jobsMap);

        return params ;
    }
}
