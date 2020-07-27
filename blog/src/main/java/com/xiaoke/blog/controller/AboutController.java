package com.xiaoke.blog.controller;


import com.xiaoke.blog.common.Result;
import com.xiaoke.blog.mapper.AboutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ma Ke
 * @since 2020-07-07
 */
@RestController
@RequestMapping("/about")
public class AboutController {

    @Autowired
    AboutMapper aboutMapper;

    @GetMapping("/detail")
    public Result getAbout(){
        return Result.success(aboutMapper.selectById(1));
    }
}
