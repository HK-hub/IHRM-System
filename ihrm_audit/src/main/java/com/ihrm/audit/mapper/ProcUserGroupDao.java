package com.ihrm.audit.mapper;

import com.ihrm.audit.entity.ProcUserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author itcast
 */
public interface ProcUserGroupDao extends JpaRepository<ProcUserGroup,String>,
		JpaSpecificationExecutor<ProcUserGroup> {
}
