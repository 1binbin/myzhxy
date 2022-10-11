package com.example.myzhxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myzhxy.mapper.AdminMapper;
import com.example.myzhxy.pojo.Admin;
import com.example.myzhxy.service.AdminServer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:14
 */

@Service("adminServiceImpl")
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminServer {

}
