package com.example.myzhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myzhxy.mapper.GradeMapper;
import com.example.myzhxy.pojo.Grade;
import com.example.myzhxy.service.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:47
 */
@Service("gradeServiceImpl")
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {
    @Override
    public IPage<Grade> getGradeByOpr(Page<Grade> page, String gradeName) {
//        查询条件
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
//        不使用 !gradeName.isEmpty() 因为gradeName可能出现为空的情况，不能调用isEmpty()
        if (!StringUtils.isEmpty(gradeName)) {
            queryWrapper.like("name", gradeName);
        }
        queryWrapper.orderByDesc("id");
        queryWrapper.orderByAsc("name");
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<Grade> getGrades() {
        return baseMapper.selectList(null);
    }
}
