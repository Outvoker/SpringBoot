package com.dave.admin.service;

import com.dave.admin.bean.City;
import org.springframework.stereotype.Service;

/**
 * @author Xu Rui
 * @date 2021/1/15 14:07
 */
public interface CityService {
    public City getById(Long id);

    public void saveCity(City city);
}
