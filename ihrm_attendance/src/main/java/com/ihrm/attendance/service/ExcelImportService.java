package com.ihrm.attendance.service;


import com.ihrm.attendance.mapper.AttendanceConfigDao;
import com.ihrm.attendance.mapper.AttendanceDao;
import com.ihrm.attendance.mapper.UserDao;
import com.ihrm.common.utils.IdWorker;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class ExcelImportService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private AttendanceConfigDao attendanceConfigDao;

    @Autowired
    private IdWorker idWorker;

    @Value("attendance.workingDays")
    private String workingDays;

	@Value("attendance.holidays")
	private String holidays;
}
