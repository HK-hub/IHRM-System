package com.ihrm.audit.service.impl;

import com.ihrm.audit.service.ProcessService;
import com.netflix.discovery.converters.Auto;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author : HK意境
 * @ClassName : ProcessServiceImpl
 * @date : 2022/1/16 21:42
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private RepositoryService repositoryService ;


    /**
     * 流程部署
     * @param file  上传bpmn文件
     * @param companyId  企业id
     */
    @Override
    public void deployProcess(MultipartFile file, String companyId) throws IOException {
        //1.获取上传的文件名
        String fileName = file.getOriginalFilename();
        //2.通过repositoryService进行流程部署
        //DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        //文件名称,文件的bytes数组
        //deploymentBuilder.addBytes(fileName,file.getBytes()); //部署流程
        //deploymentBuilder.tenantId(companyId);
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addBytes(fileName, file.getBytes()).tenantId(companyId);
        Deployment deploy = deploymentBuilder.deploy();
        //3.打印部署结果
        System.out.println(deploy);
    }

}
