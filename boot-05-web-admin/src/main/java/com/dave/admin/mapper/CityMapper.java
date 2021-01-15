package com.dave.admin.mapper;

import com.dave.admin.bean.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Xu Rui
 * @date 2021/1/15 13:20
 */
@Mapper
public interface CityMapper {
    @Select("select * from city where id=#{id}")
    public City getById(Long id);

    public void insert(City city);
}
