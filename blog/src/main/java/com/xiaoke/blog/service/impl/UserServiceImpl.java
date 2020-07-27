package com.xiaoke.blog.service.impl;

import com.xiaoke.blog.entity.User;
import com.xiaoke.blog.mapper.UserMapper;
import com.xiaoke.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Ma Ke
 * @since 2020-06-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
