package com.ihrm.system.mapper;

import com.ihrm.domain.system.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author HK意境
 */
public interface CityDao extends JpaRepository<City,String> ,JpaSpecificationExecutor<City> {
}
