package com.xiaoke.blog.controller;

import com.xiaoke.blog.common.Result;
import com.xiaoke.blog.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class ElasticSearchController {


    @Autowired
    ElasticSearchService elasticSearchService;

    @GetMapping("/search/{keyword}")
    public Result searchPage(@PathVariable String keyword,
                             @RequestParam(defaultValue = "1") Integer currentPage,
                             @RequestParam(defaultValue = "5") Integer pageSize) throws IOException {

        Map<Object, Object> blogMap = elasticSearchService.searchPage(keyword,currentPage,pageSize);
        return Result.success(blogMap);
    }

}
