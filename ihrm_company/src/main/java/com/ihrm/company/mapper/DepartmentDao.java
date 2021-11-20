package com.ihrm.company.mapper;

import com.ihrm.domain.company.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 部门dao接口
 * @author HK意境
 */
public interface DepartmentDao extends JpaRepository<Department,String> ,JpaSpecificationExecutor<Department> {

    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/11/19 13:41
     * @description :
     * @Todo :
     * @params :
         * @param : code
         * @param : companyId
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    Department findByCodeAndCompanyId(String code, String companyId);
}
