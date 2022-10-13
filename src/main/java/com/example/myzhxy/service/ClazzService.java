package com.example.myzhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myzhxy.pojo.Clazz;

import java.util.List;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:44
 */
public interface ClazzService extends IService<Clazz> {
    IPage<Clazz> getClazzByOpr(Page<Clazz> page, Clazz clazz);

    List<Clazz> getClazz();
}
