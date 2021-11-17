package com.ihrm.system.mapper;

import com.ihrm.domain.system.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : HK意境
 * @ClassName : UserDao
 * @date : 2021/11/14 14:25
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface UserDao extends JpaRepository<User, String> , JpaSpecificationExecutor<User> {
    
    /**
     * @methodName : 根据手机号查询用户
     * @author : HK意境
     * @date : 2021/11/14 21:26
     * @description :
     * @Todo :
     * @params : 
         * @param : null 
     * @return : null
     * @throws: 
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    User findByMobile(String mobile);
}
