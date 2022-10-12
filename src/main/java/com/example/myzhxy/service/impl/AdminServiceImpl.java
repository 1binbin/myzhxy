package com.example.myzhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myzhxy.mapper.AdminMapper;
import com.example.myzhxy.pojo.Admin;
import com.example.myzhxy.pojo.LoginForm;
import com.example.myzhxy.service.AdminService;
import com.example.myzhxy.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:14
 */

@Service("adminServiceImpl")
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    /** 管理员登录校验
     * @Param: LoginForm loginForm
     * @Return: Admin
     */
    @Override
    public Admin login(LoginForm loginForm) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginForm.getUsername());
//        密码需要进行MD5加密
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        return baseMapper.selectOne(queryWrapper);
    }

    /** 获取管理员信息
     * @Param:
     * @Return:
     */
    @Override
    public Admin getAdminById(Long userId) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        return baseMapper.selectOne(queryWrapper);
    }
}
