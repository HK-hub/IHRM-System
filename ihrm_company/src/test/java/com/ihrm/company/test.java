package com.ihrm.company;

import com.ihrm.company.mapper.CompanyDao;
import com.ihrm.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author : HK意境
 * @ClassName : test
 * @date : 2021/11/13 14:53
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class test {

    @Autowired
    private CompanyDao companyDao ;


    @Test
    public void testCompanyDaoFindById(){

        Company company = companyDao.findById("1").get();
        System.out.println(company.toString());

    }


    @Test
    public void testCompanyDaoFindAll(){

        List<Company> all = companyDao.findAll();
        for (Company company : all) {
            System.out.println(company);
        }

    }



}
