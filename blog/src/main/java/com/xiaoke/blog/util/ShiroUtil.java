package com.xiaoke.blog.util;

import org.apache.shiro.SecurityUtils;


public class ShiroUtil {

    public static String getProfile() {

        String token = (String) SecurityUtils.getSubject().getPrincipal();
        String username = JWTUtil.getUsername(token);
        return username;
    }
}
