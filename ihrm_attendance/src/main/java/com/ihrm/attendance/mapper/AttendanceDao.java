package com.ihrm.attendance.mapper;


import com.ihrm.domain.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AttendanceDao extends CrudRepository<Attendance,Long>, JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {

}
