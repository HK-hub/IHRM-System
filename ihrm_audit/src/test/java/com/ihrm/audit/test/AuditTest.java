package com.ihrm.audit.test;

import com.ihrm.audit.entity.ProcUserGroup;
import com.ihrm.audit.mapper.ProcUserGroupDao;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author : HK意境
 * @ClassName : AuditTest
 * @date : 2022/1/16 20:50
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@SpringBootTest()
@RunWith(SpringJUnit4ClassRunner.class)

public class AuditTest {

    /**
     * 测试业务数据库
     *
     *
     *
     */
    @Autowired
    private ProcUserGroupDao procUserGroupDao ;



    /**
     * @methodName :
     * @author : HK意境
     * @date : 2022/1/16 20:54
     * @description : 业务数据库
     * @Todo : 多数据源数据库访问测试
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Test
    public void testDatabaseAccess(){

        List<ProcUserGroup> all = procUserGroupDao.findAll();
        System.out.println(all.size());
        all.forEach(System.out::println);

    }
    
    
    @Autowired
    private RepositoryService repositoryService ;

    /**
     * @methodName : 
     * @author : HK意境
     * @date : 2022/1/16 21:25
     * @description : 测试activiti 数据库 ， 使用 activiti 提供的接口测试， activiti 提供的对象： RepositoryService
     * @Todo :
     * @params : 
         * @param : null 
     * @return : null
     * @throws: 
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Test
    public void testActivitiDabaseAccess(){

        // 测试查看 activiti 中的所有数据
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        System.out.println(list.size());
        list.forEach(System.out::println);

    }


}
