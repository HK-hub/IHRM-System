package com.ihrm.attendance.service;


import com.ihrm.attendance.mapper.ArchiveMonthlyDao;
import com.ihrm.attendance.mapper.ArchiveMonthlyInfoDao;
import com.ihrm.attendance.mapper.AttendanceDao;
import com.ihrm.attendance.mapper.UserDao;
import com.ihrm.common.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ArchiveService {

	@Autowired
	private AttendanceDao attendanceDao;

	@Autowired
	private ArchiveMonthlyDao atteArchiveMonthlyDao;

	@Autowired
	private ArchiveMonthlyInfoDao archiveMonthlyInfoDao;


	@Autowired
	private UserDao userDao;

	@Autowired
	private IdWorker idWorkker;
}
