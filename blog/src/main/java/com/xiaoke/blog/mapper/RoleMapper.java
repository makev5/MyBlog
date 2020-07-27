package com.xiaoke.blog.mapper;

import com.xiaoke.blog.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Ma Ke
 * @since 2020-06-25
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT `name` FROM role WHERE id=(\n" +
            "\tSELECT role_id FROM user_role WHERE user_id=#{userId}\n" +
            ")")
    List<String> getRoleNames(Integer userId);
}
