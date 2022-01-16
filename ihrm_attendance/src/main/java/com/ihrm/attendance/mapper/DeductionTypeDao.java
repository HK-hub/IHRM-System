package com.ihrm.attendance.mapper;

import com.ihrm.domain.attendance.entity.DeductionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeductionTypeDao extends JpaRepository<DeductionType, String>, JpaSpecificationExecutor<DeductionType> {


}
