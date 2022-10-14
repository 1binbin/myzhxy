package com.example.myzhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myzhxy.pojo.Admin;
import com.example.myzhxy.pojo.LoginForm;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:13
 */
public interface AdminService extends IService<Admin> {
    Admin login(LoginForm loginForm);

    Admin getAdminById(Long userId);

    IPage<Admin> getAdminByOpr(Page<Admin> page, String adminName);

    Admin getOnePwd(Long userId, String oldPwd);
}
