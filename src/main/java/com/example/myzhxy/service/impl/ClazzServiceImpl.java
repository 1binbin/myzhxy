package com.example.myzhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myzhxy.mapper.ClazzMapper;
import com.example.myzhxy.pojo.Clazz;
import com.example.myzhxy.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:47
 */
@Service("clazzServiceImpl")
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {

    /** 查询班级信息，带分页条件
     * @Param:
     * @Return:
     */
    @Override
    public IPage<Clazz> getClazzByOpr(Page<Clazz> page, Clazz clazz) {
        QueryWrapper<Clazz> objectQueryWrapper = new QueryWrapper<>();
        String gradeName = clazz.getGradeName();
        String name = clazz.getName();
        if (!StringUtils.isEmpty(gradeName)){
            objectQueryWrapper.eq("grade_name",gradeName);
        }
        if (!StringUtils.isEmpty(name)) {
            objectQueryWrapper.like("name",name);
        }
        objectQueryWrapper.orderByDesc("id");
        objectQueryWrapper.orderByAsc("name");
        return baseMapper.selectPage(page,objectQueryWrapper);
    }

    /** 在教师管理中显示班级信息
     * @Param:
     * @Return:
     */
    @Override
    public List<Clazz> getClazz() {
        return baseMapper.selectList(null);
    }
}
