package com.example.myzhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myzhxy.pojo.Admin;
import com.example.myzhxy.service.AdminService;
import com.example.myzhxy.utils.MD5;
import com.example.myzhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:50
 */
@Api(tags = "管理员控制")
@RestController
@RequestMapping("/sms/adminController")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 分页查询管理员信息
     *
     * @Param: Integer pageNo , Integer pageSize , String adminName
     * @Return: Result
     */
    @ApiOperation("分页查询管理员信息")
    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAdminByOpr(@ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
                                @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
                                String adminName) {
        Page<Admin> page = new Page<>(pageNo, pageSize);
        IPage<Admin> iPage = adminService.getAdminByOpr(page, adminName);
        return Result.ok(iPage);
    }

    /**
     * 添加与修改Admin信息
     *
     * @Param: Admin admin
     * @Return: Result
     */
    @ApiOperation("添加与修改Admin信息")
    @PostMapping("/saveOrUpdateAdmin")
    public Result saveOrUpdateAdmin(@ApiParam("JSON格式的Admin对象") @RequestBody Admin admin) {
        if (!StringUtils.isEmpty(admin.getPassword())) {
            admin.setPassword(MD5.encrypt(admin.getPassword()));
        }
        adminService.saveOrUpdate(admin);
        return Result.ok();
    }

    /**
     * 删除与批量删除管理员信息
     *
     * @Param: List<Integer> ids
     * @Return: Result
     */
    @ApiOperation("删除与批量删除管理员信息")
    @DeleteMapping("/deleteAdmin")
    public Result deleteAdmins(@ApiParam("要删除的所有admin的JSON数据集合id") @RequestBody List<Integer> ids) {
        adminService.removeByIds(ids);
        return Result.ok();
    }
}
