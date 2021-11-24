package com.ihrm.system.service;


import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.system.City;
import com.ihrm.system.mapper.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @ClassName : CityService
 * @author : HK意境
 * @date : 2021/11/23
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Service
public class CityService {

    @Autowired
    private CityDao cityDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存
     */
    public void add(City city) {
        //基本属性的设置
        String id = idWorker.nextId()+"";
        city.setId(id);
        cityDao.save(city);
    }

    /**
     * 更新
     */
    public void update(City city) {
        cityDao.save(city);
    }

    /**
     * 删除
     */
    public void deleteById(String id) {
        cityDao.deleteById(id);
    }

    /**
     * 根据id查询
     */
    public City findById(String id) {
        return cityDao.findById(id).get();
    }

    /**
     * 查询列表
     */
    public List<City> findAll() {
        return cityDao.findAll();
    }
}
