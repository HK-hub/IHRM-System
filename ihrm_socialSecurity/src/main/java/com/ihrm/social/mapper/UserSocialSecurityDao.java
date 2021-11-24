package com.ihrm.social.mapper;
import com.ihrm.domain.social.UserSocialSecurity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface UserSocialSecurityDao extends JpaRepository<UserSocialSecurity, String>, JpaSpecificationExecutor<UserSocialSecurity> {


    /***
     *  基于sql 的分页：
     *      返回值： Page<T>
     *      参数：Pageable, 封装分页参数，page, pagesize
     *
     *
     */
    @Query(nativeQuery = true,
            value = "SELECT bu.id,\n" +
                    "       bu.username,\n" +
                    "       bu.mobile,\n" +
                    "       bu.work_number  workNumber,\n" +
                    "       bu.department_name departmentName,\n" +
                    "       bu.time_of_entry timeOfEntry,\n" +
                    "       bu.time_of_dimission leaveTime,\n" +
                    "       ssuss.participating_in_the_city participatingInTheCity,\n" +
                    "       ssuss.participating_in_the_city_id participatingInTheCityId,\n" +
                    "       ssuss.provident_fund_city_id providentFundCityId,\n" +
                    "       ssuss.provident_fund_city providentFundCity,\n" +
                    "       ssuss.social_security_base socialSecurityBase,\n" +
                    "       ssuss.provident_fund_base providentFundBase \n" +
                    "FROM \n" +
                    "     bs_user bu \n" +
                    "         LEFT JOIN ss_user_social_security ssuss \n" +
                    "             ON bu.id=ssuss.user_id \n" +
                    "WHERE bu.company_id=?1",

            countQuery = "select count(*) " +
                            "from bs_user as u " +
                            "left join ss_user_social_security as s on u.id = s.user_id " +
                            "where company_id=?1"
    )
    public Page<Map> findPage(String companyId , Pageable pageable);


}
