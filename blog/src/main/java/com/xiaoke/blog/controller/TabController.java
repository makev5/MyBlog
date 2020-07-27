package com.xiaoke.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoke.blog.common.Result;
import com.xiaoke.blog.entity.Tab;
import com.xiaoke.blog.mapper.TabMapper;
import com.xiaoke.blog.service.TabService;
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
@RequestMapping("/tab")
public class TabController {

    @Autowired
    TabService tabService;

    @Autowired
    TabMapper tabMapper;

    /**
     *  获取标签列表
     * @return
     */
    @GetMapping("/list")
    public Result getTabList(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "5") Integer pageSize){
        Page page = new Page(currentPage, pageSize);
        Page pageData = tabService.page(page, new QueryWrapper<Tab>().orderByAsc("created"));
        return Result.success(pageData);
    }

    /**
     *  添加或修改标签
     * @param tab
     * @return
     */
    @PostMapping("/edit")
    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
    public Result editTab(@RequestBody Tab tab){
        Tab temp = null;
        // 编辑
        if(tab.getId()!=null){
            temp = tabService.getById(tab.getId());
            temp.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        } else {
            temp = new Tab();
            temp.setCreated(new Timestamp(System.currentTimeMillis()));
            temp.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        }
        BeanUtil.copyProperties(tab, temp, "id", "created", "updateTime");
        tabService.saveOrUpdate(temp);
        return Result.success(null);
    }

    /**
     *  删除标签
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
    public Result deleteTab(@PathVariable Integer id){
        boolean b = tabService.removeById(id);
        if (b){
            return Result.success(null);
        }
        return  Result.fail("操作失败");
    }

    @GetMapping("/getBlogCountByTag")
    public Result getBlogCountByTag(){
        List<Map<String,String>> blogCountByTag = tabMapper.getBlogCountByTag();

        return Result.success(MapUtil.builder()
                .put("blogCountByTag", blogCountByTag)
                .map()
        );
    }
}
