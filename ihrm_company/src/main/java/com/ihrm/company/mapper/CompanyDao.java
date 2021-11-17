package com.ihrm.company.mapper;

import com.ihrm.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : HK意境
 * @ClassName : CompanyDao
 * @date : 2021/11/13 14:50
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface CompanyDao extends JpaSpecificationExecutor<Company> , JpaRepository<Company, String> {




}
