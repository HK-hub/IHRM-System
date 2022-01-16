package com.ihrm.attendance.service;



import com.ihrm.attendance.mapper.ArchiveMonthlyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author HK意境
 */
@Service
public class ReprortService {



    @Autowired
    private ArchiveMonthlyDao archiveMonthlyDao;

}
