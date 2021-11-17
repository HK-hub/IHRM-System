package com.ihrm.company.mapper;

import com.ihrm.domain.company.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 部门dao接口
 * @author HK意境
 */
public interface DepartmentDao extends JpaRepository<Department,String> ,JpaSpecificationExecutor<Department> {
}
