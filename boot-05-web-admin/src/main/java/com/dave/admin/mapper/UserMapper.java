package com.dave.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dave.admin.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Xu Rui
 * @date 2021/1/15 13:58
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
