package com.example.myzhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myzhxy.mapper.StudentMapper;
import com.example.myzhxy.pojo.Admin;
import com.example.myzhxy.pojo.LoginForm;
import com.example.myzhxy.pojo.Student;
import com.example.myzhxy.service.StudentService;
import com.example.myzhxy.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:48
 */
@Service("studentServiceImpl")
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    /**
     * 学生登录校验
     *
     * @Param: LoginForm loginForm
     * @Return: Student
     */
    @Override
    public Student login(LoginForm loginForm) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginForm.getUsername());
//        密码需要进行MD5加密
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Student getStudentById(Long userId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<Student> getStudentByOpr(Student student, Page<Student> studentPage) {
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        String clazzName = student.getClazzName();
        String name = student.getName();
        if (!StringUtils.isEmpty(clazzName)) {
            studentQueryWrapper.like("clazz_name", clazzName);
        }
        if (!StringUtils.isEmpty(name)) {
            studentQueryWrapper.like("name", name);
        }
        studentQueryWrapper.orderByDesc("id");
        studentQueryWrapper.orderByAsc("name");
        return baseMapper.selectPage(studentPage, studentQueryWrapper);
    }

    @Override
    public Student getOnePwd(Long userId, String oldPwd) {
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("id",userId.intValue()).eq("password",oldPwd);
        return baseMapper.selectOne(studentQueryWrapper);
    }
}
