package com.xiaoke.blog.controller;


import com.xiaoke.blog.common.Result;
import com.xiaoke.blog.service.BannerService;
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
 * @since 2020-07-05
 */
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @GetMapping("/list")
    public Result getBanner(){
        return  Result.success(bannerService.list());
    }
}
