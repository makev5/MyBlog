package com.xiaoke.blog.service.impl;

import com.xiaoke.blog.entity.About;
import com.xiaoke.blog.mapper.AboutMapper;
import com.xiaoke.blog.service.AboutService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ma Ke
 * @since 2020-07-07
 */
@Service
public class AboutServiceImpl extends ServiceImpl<AboutMapper, About> implements AboutService {

}
