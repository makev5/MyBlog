package com.xiaoke.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoke.blog.common.Result;
import com.xiaoke.blog.entity.Sort;
import com.xiaoke.blog.mapper.SortMapper;
import com.xiaoke.blog.service.SortService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ma Ke
 * @since 2020-07-01
 */
@RestController
@RequestMapping("/sort")
public class SortController {

    @Autowired
    SortService sortService;

    @Autowired
    SortMapper sortMapper;

    /**
     *  获取分类列表
     * @return
     */
    @GetMapping("/list")
    public Result getSortList(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "5") Integer pageSize){
        Page page = new Page(currentPage, pageSize);
        Page pageData = sortService.page(page, new QueryWrapper<Sort>().orderByAsc("created"));
        return Result.success(pageData);
    }

    /**
     *  创建或修改分类
     * @param sort
     * @return
     */
    @PostMapping("/edit")
    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
    public Result editSort(@RequestBody Sort sort){
        Sort temp = null;
        // 编辑
        if (sort.getId()!=null){
            temp = sortService.getById(sort.getId());
            temp.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        } else {
            temp = new Sort();
            temp.setCreated(new Timestamp(System.currentTimeMillis()));
            temp.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        }
        BeanUtil.copyProperties(sort, temp, "id", "created", "updateTime");
        sortService.saveOrUpdate(temp);
        return Result.success(null);
    }

    /**
     *  删除分类
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
    public Result deleteSort(@PathVariable Integer id){
        boolean b = sortService.removeById(id);
        if (b){
            return Result.success(null);
        }
        return Result.fail("删除失败");
    }

    @GetMapping("/getBlogCountBySort")
    public Result getBlogCountBySort(){
        List<Map<String, String>> blogCountBySort = sortMapper.getBlogCountBySort();
        return Result.success(MapUtil.builder()
                .put("blogCountBySort", blogCountBySort)
                .map()
        );
    }
}
