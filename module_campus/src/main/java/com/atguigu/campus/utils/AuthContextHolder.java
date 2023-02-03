package com.atguigu.campus.utils;

/**
 * ClassName: AuthContextHolder
 * Package: com.atgiugu.campus.utils
 * Description:
 *
 * @author ziqiu
 * @Create: 2023/2/3 - 21:28  21:28
 * @Version: v1.0
 */


import javax.servlet.http.HttpServletRequest;

public class AuthContextHolder {

    //从请求头token获取userid
    public static Long getUserIdToken(HttpServletRequest request) {
        //从请求头token
        String token = request.getHeader("token");
        //调用工具类
        Long userId = JwtHelper.getUserId(token);
        return userId;
    }

    //从请求头token获取name
    public static String getUserName(HttpServletRequest request) {
        //从header获取token
        String token = request.getHeader("token");
        //jwt从token获取username
        String userName = JwtHelper.getUserName(token);
        return userName;
    }
}
