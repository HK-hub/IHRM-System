package com.ihrm.employee.dao;

import com.ihrm.domain.employee.UserCompanyPersonal;
import com.ihrm.domain.employee.response.EmployeeReportResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * 数据访问接口
 */
public interface UserCompanyPersonalDao extends JpaRepository<UserCompanyPersonal, String>, JpaSpecificationExecutor<UserCompanyPersonal> {
    UserCompanyPersonal findByUserId(String userId);

    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/11/19 15:51
     * @description :
     * @Todo : 查询企业某月的人事变化情况
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Query(value = "select new com.ihrm.domain.employee.response.EmployeeReportResult(u,r) from UserCompanyPersonal as u " +
            "left join EmployeeResignation as r on u.userId = r.userId " +
            "where u.companyId=?1 and u.timeOfEntry like?2 or " +
            "r.resignationTime like ?2")
    List<EmployeeReportResult> findByReport(String companyId, String month);
}