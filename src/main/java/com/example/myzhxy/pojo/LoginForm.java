package com.example.myzhxy.pojo;

import lombok.Data;

/**
 * 表单登录实体类
 *
 * @Author hongxiaobin
 * @Time 2022/10/11-20:08
 */
@Data
public class LoginForm {
    private String username;
    private String password;
    private String verifiCode;
    private Integer userType;
}
