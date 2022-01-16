package com.ihrm.attendance.mapper;


import com.ihrm.domain.attendance.entity.ExtraDutyConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExtraDutyConfigDao  extends JpaRepository<ExtraDutyConfig, Long>, JpaSpecificationExecutor<ExtraDutyConfig> {

    /**
     * @return 根据公司和部门查询加班配置信息
     */
    ExtraDutyConfig findByCompanyIdAndDepartmentId(String companyId, String departmentId);
}
