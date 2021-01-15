package com.dave.admin.service.impl;

import com.dave.admin.bean.City;
import com.dave.admin.mapper.CityMapper;
import com.dave.admin.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Xu Rui
 * @date 2021/1/15 13:22
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityMapper cityMapper;

    public City getById(Long id){
        return cityMapper.getById(id);
    }

    public void saveCity(City city){
        cityMapper.insert(city);
    }
}
