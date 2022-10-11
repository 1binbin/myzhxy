package com.example.myzhxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myzhxy.pojo.Admin;
import org.springframework.stereotype.Repository;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:09
 */

// 便于注解扫描，可以不加这个注解
@Repository
public interface AdminMapper extends BaseMapper<Admin> {

}
