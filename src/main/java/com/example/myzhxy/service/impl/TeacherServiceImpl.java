package com.example.myzhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myzhxy.mapper.TeacherMapper;
import com.example.myzhxy.pojo.Admin;
import com.example.myzhxy.pojo.LoginForm;
import com.example.myzhxy.pojo.Teacher;
import com.example.myzhxy.service.TeacherService;
import com.example.myzhxy.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:49
 */
@Service("teacherServiceImpl")
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    /**
     * 教师登录验证
     *
     * @Param: LoginForm loginForm
     * @Return: Teacher
     */
    @Override
    public Teacher login(LoginForm loginForm) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginForm.getUsername());
//        密码需要进行MD5加密
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Teacher getTeacherById(Long userId) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        return baseMapper.selectOne(queryWrapper);
    }

    /** 分页查询教师信息
     * @Param:
     * @Return:
     */
    @Override
    public IPage<Teacher> getTeachersByOpr(Page<Teacher> page, Teacher teacher) {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        String clazzName = teacher.getClazzName();
        String name = teacher.getName();
        if (!StringUtils.isEmpty(clazzName)) {
            teacherQueryWrapper.eq("clazz_name",clazzName);
        }
        if (!StringUtils.isEmpty(name)) {
            teacherQueryWrapper.like("name",name);
        }
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.orderByAsc("name");
        return baseMapper.selectPage(page,teacherQueryWrapper);
    }
}

