package com.dave.admin.mapper;

import com.dave.admin.bean.Alg;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Xu Rui
 * @date 2021/1/15 11:24
 */
@Mapper
public interface AlgMapper {
    public Alg getAlg(Long id);
}
