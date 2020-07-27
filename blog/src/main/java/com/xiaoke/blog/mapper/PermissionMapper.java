package com.xiaoke.blog.mapper;

import com.xiaoke.blog.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author Ma Ke
 * @since 2020-06-25
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("SELECT per_code FROM permission WHERE id in (\n" +
            "\tSELECT permission_id FROM role_permission WHERE role_id=(\n" +
            "\t\tSELECT role_id FROM user_role WHERE user_id=#{userId}\n" +
            "\t)\n" +
            ")")
    List<String> getPermissions(Integer userId);
}
