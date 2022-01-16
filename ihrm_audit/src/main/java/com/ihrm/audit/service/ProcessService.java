package com.ihrm.audit.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author : HK意境
 * @ClassName : ProcessService
 * @date : 2022/1/16 21:41
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface ProcessService {


    /**
     * 流程部署
     * @param file  上传bpmn文件
     * @param companyId  企业id
     */
    public void deployProcess(MultipartFile file, String companyId) throws IOException;
}
