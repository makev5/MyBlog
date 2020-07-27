package com.xiaoke.blog.controller;


import com.xiaoke.blog.common.Result;
import com.xiaoke.blog.service.ToolService;
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
 * @since 2020-07-23
 */
@RestController
@RequestMapping("/tool")
public class ToolController {

    @Autowired
    ToolService toolService;

    @GetMapping("/list")
    public Result getToolList(){
        return Result.success(toolService.list());
    }

}
