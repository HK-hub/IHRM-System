package com.ihrm.social.service;


import com.ihrm.domain.social.CityPaymentItem;
import com.ihrm.social.mapper.CityPaymentItemDao;
import com.ihrm.social.mapper.PaymentItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentItemService {
	
    @Autowired
    private PaymentItemDao paymentItemDao;
	
    @Autowired
    private CityPaymentItemDao cityPaymentItemDao;

    //根据城市id获取缴费项目
	public List<CityPaymentItem> findAllByCityId(String id) {
		return cityPaymentItemDao.findAllByCityId(id);
	}
}
