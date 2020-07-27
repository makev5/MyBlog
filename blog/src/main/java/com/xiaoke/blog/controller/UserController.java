package com.xiaoke.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoke.blog.common.Result;
import com.xiaoke.blog.common.dto.UserDto;
import com.xiaoke.blog.entity.User;
import com.xiaoke.blog.mapper.RoleMapper;
import com.xiaoke.blog.mapper.UserMapper;
import com.xiaoke.blog.service.UserService;
import com.xiaoke.blog.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Ma Ke
 * @since 2020-06-25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取所有用户
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Ma Ke
     * @date 2020-06-25
     */
    @RequiresPermissions(logical = Logical.AND, value = {"user:view"})
    @RequiresRoles("管理员")
    @GetMapping("/list")
    public Result user(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "5") Integer pageSize){
        Page<User> userPage = new Page<>(currentPage, pageSize);
        Page<User> pageDate = userService.page(userPage, new QueryWrapper<User>().orderByAsc("created"));
       // List<User> userList = userService.list();
        return Result.success(pageDate);
    }

    /**
     * 获取指定用户
     * @param id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Ma Ke
     * @date 2020-06-25
     */
    @RequiresPermissions(logical = Logical.AND, value = {"user:view"})
    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }

    /**
     * 新增用户
     * @param user
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Ma Ke
     * @date 2020-06-25
     */
    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        int count = userMapper.insert(user);
        if (count>0){
            return Result.success(null);
        }
        return Result.fail("操作失败");
    }

    /**
     * 更新用户
     * @param user
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Ma Ke
     * @date 2020-06-25
     */
    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
    @PutMapping("/edit")
    public Result editUser(@RequestBody User user){
        // 密码加密
        if (user.getPassword() != null){
            user.setPassword(SecureUtil.md5(user.getPassword()));
        }
        User temp = null;
        // 编辑
        if (user.getId()!= null){
            temp = userService.getById(user.getId());
        } else {
            temp = new User();
            temp.setCreated(new Timestamp(System.currentTimeMillis()));
        }
        BeanUtil.copyProperties(user, temp, "id", "created");
        userService.saveOrUpdate(temp);
        return Result.success(null);
    }

    /**
     * 删除用户
     * @param id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Ma Ke
     * @date 2020-06-25
     */
    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        int count = userMapper.deleteById(id);
        if (count>0){
            return Result.success(null);
        }
        return  Result.fail("操作失败");
    }

    /**
     * 登录授权
     * @param userDto
     * @return com.xiaoke.blog.common.Result
     * @author Ma Ke
     * @date 22020-06-25
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody UserDto userDto, HttpServletResponse response) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", userDto.getUsername()));
        Assert.notNull(user, "用户不存在");
        if (!user.getPassword().equals(SecureUtil.md5(userDto.getPassword()))) {
            return Result.fail("密码错误");
        }

        // 获取token
        String jwt = JWTUtil.sign(user);
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        List<String> roleNames = roleMapper.getRoleNames(user.getId());

        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("sex", user.getSex())
                .put("iphone", user.getIphone())
                .put("email", user.getEmail())
                .put("token", jwt)
                .put("roles", roleNames)
                .map()
        );
    }

    /**
     *  获取用户信息
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/info")
    public Result userInfo(){
        String token = SecurityUtils.getSubject().getPrincipal().toString(); // 获取当前登录用户
        String username = JWTUtil.getUsername(token);
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        List<String> roleNames = roleMapper.getRoleNames(user.getId());
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("nickname", user.getNickname())
                .put("avatar", user.getAvatar())
                .put("sex", user.getSex())
                .put("iphone", user.getIphone())
                .put("email", user.getEmail())
                .put("role", roleNames)
                .put("created", user.getCreated())
                .map()
        );
    }

    /**
     * 退出登录
     * @param
     * @return com.xiaoke.blog.common.Result
     * @author Ma Ke
     * @date 22020-06-25
     */
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }
    /**
     * 测试登录
     * @param
     * @return com.xiaoke.blog.common.Result
     * @author Ma Ke
     * @date 2020-06-25
     */
    @GetMapping("/article")
    public Result article() {
        Subject subject = SecurityUtils.getSubject();
        // 登录了返回true
        if (subject.isAuthenticated()) {
            return Result.success(200, "您已登录", null);
        } else {
            return Result.success(200, "您是游客", null);
        }
    }

    /**
     * @RequiresAuthentication和subject.isAuthenticated()返回true一个性质
     * @param
     * @return com.xiaoke.blog.common.Result
     * @author Ma Ke
     * @date 2020-06-25
     */
    @RequiresAuthentication
    @GetMapping("/require_auth")
    public Result requireAuth() {

        return Result.success(200, "您已登录", null);
    }

    @RequiresRoles("管理员")
    @GetMapping("/require_role")
    public Result requireRole() {
        return  Result.success(200, "您正在访问角色是admin的页面", null);
    }

    @RequiresPermissions("user:view")
    @GetMapping("/require_permission")
    public Result requirePermission() {
        return Result.success(200, "您正在访问权限是view和edit的页面", null);
    }

    /**
     * 401没有权限异常
     * @param
     * @return com.xiaoke.blog.common.Result
     * @author Ma Ke
     * @date 2020-06-25
     */
    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result unauthorized() {
        return Result.fail(401, "没有权限", null);
    }

}
