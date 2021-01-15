package com.dave.admin.service.impl;

import com.dave.admin.bean.Alg;
import com.dave.admin.mapper.AlgMapper;
import com.dave.admin.service.AlgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Xu Rui
 * @date 2021/1/15 11:32
 */
@Service
public class AlgServiceImpl implements AlgService {
    @Autowired
    AlgMapper algMapper;

    public Alg getAlgById(Long id){
        return algMapper.getAlg(id);
    }
}
