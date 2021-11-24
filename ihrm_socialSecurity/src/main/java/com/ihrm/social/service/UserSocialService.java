package com.ihrm.social.service;

import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.domain.social.UserSocialSecurity;
import com.ihrm.social.feign.SystemFeignClient;
import com.ihrm.social.mapper.UserSocialSecurityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * @ClassName : UserSocialService
 * @author : HK意境
 * @date : 2021/11/24
 * @description : 用户社保service
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Service
public class UserSocialService {
	
    @Autowired
    private UserSocialSecurityDao userSocialSecurityDao;

    /**
     * @methodName : 查询企业下的员工的社保信息
     * @author : HK意境
     * @date : 2021/11/24 15:57
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
    public PageResult findAll(Integer page, Integer pageSize, String companyId) {

        Page socialSecurityInfoPage = userSocialSecurityDao.findPage(companyId, PageRequest.of(page - 1, pageSize));

        // 构造返回数据
        PageResult pageResult = new PageResult(socialSecurityInfoPage.getTotalElements(), socialSecurityInfoPage.getContent());
        return pageResult ;
    }


    public UserSocialSecurity findById(String userId) {
        Optional<UserSocialSecurity> optional = userSocialSecurityDao.findById(userId);

        return optional.orElse(null);
    }

    public void save(UserSocialSecurity uss) {
        userSocialSecurityDao.save(uss);
    }
}
