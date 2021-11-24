package com.ihrm.social.mapper;



/**
 * @ClassName : ArchiveDao
 * @author : HK意境
 * @date : 2021/11/24
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */

import com.ihrm.domain.social.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 自定义dao接口继承
 *      JpaRepository<实体类，主键>
 *      JpaSpecificationExecutor<实体类>
 */
public interface ArchiveDao extends JpaRepository<Archive,String>, JpaSpecificationExecutor<Archive> {

    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/11/24 18:18
     * @description :
     * @Todo :
     * @params :
         * @param : companyId
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    Archive findByCompanyIdAndYearsMonth(String companyId, String yearMonth);

    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/11/24 20:55
     * @description : 模糊查询，查询月份
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    List<Archive> findByCompanyIdAndYearsMonthLike(String companyId, String month);
}
