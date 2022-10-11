package com.example.myzhxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myzhxy.pojo.LoginForm;
import com.example.myzhxy.pojo.Student;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:46
 */
public interface StudentService extends IService<Student> {
    Student login(LoginForm loginForm);
}
