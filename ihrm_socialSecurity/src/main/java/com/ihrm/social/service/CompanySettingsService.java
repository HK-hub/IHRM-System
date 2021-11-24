package com.ihrm.social.service;


import com.ihrm.domain.social.CompanySettings;
import com.ihrm.social.mapper.CompanySettingsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName : CompanySettingsService
 * @author : HK意境
 * @date : 2021/11/24
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Service
public class CompanySettingsService {
    @Autowired
    private CompanySettingsDao companySettingsDao;

    //根据企业id查询
	public CompanySettings findById(String companyId) {
		Optional<CompanySettings> optional = companySettingsDao.findById(companyId);
		return optional.isPresent() ? optional.get() : null;
	}

	//保存企业设置
	public void save(CompanySettings companySettings) {
		//已经完成当月设置
		companySettings.setIsSettings(1);
		companySettingsDao.save(companySettings);
	}
}
