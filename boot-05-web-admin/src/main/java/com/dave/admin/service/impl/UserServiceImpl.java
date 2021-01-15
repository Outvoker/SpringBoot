package com.dave.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dave.admin.bean.User;
import com.dave.admin.mapper.UserMapper;
import com.dave.admin.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Xu Rui
 * @date 2021/1/15 14:06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
