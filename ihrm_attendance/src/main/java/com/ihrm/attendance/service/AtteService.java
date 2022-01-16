package com.ihrm.attendance.service;


import com.ihrm.attendance.mapper.AttendanceConfigDao;
import com.ihrm.attendance.mapper.AttendanceDao;
import com.ihrm.attendance.mapper.DeductionDictDao;
import com.ihrm.attendance.mapper.UserDao;
import com.ihrm.common.utils.IdWorker;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



@Service
public class AtteService  {

	@Autowired
	private IdWorker idWorker;

    @Autowired
    private AttendanceDao attendanceDao;



    @Autowired
    private DeductionDictDao deductionDictDao;


    @Autowired
    private UserDao userDao;

    @Autowired
    private AttendanceConfigDao attendanceConfigDao;
}
