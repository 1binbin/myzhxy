package com.example.myzhxy.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 解析request请求中token口令的工具
 *
 * @Author hongxiaobin
 * @Time 2022/10/11-15:26
 */
public class AutoContextHolder {
    /** 请求头token获取userid
     * @Param:  HttpServletRequest request
     * @Return: Long
     */
    public static Long getUserIdToken(HttpServletRequest request){
//        请求头token
        String token = request.getHeader("token");
//        调用工具类，解析
        return JwtHelper.getUserId(token);
    }

    /** 请求头token获取name
     * @Param: HttpServletRequest request
     * @Return: String
     */
    public static String getUserName(HttpServletRequest request){
//        从header获取token
        String token = request.getHeader("token");
//        解析得到username
        return JwtHelper.getUserName(token);
    }
}
