package com.example.myzhxy.controller;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:50
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myzhxy.pojo.Teacher;
import com.example.myzhxy.service.TeacherService;
import com.example.myzhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "教师控制器")
@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /** 分页查询教师信息
     * @Param:
     * @Return:
     */
    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachersByOpr(@PathVariable("pageNo") Integer pageNo,
                                   @PathVariable("pageSize") Integer pageSize,
                                   Teacher teacher){
        Page<Teacher> page = new Page<>(pageNo,pageSize);
        IPage<Teacher> iPage = teacherService.getTeachersByOpr(page,teacher);
        return Result.ok(iPage);
    }

    /** 添加与修改教师信息
     * @Param:
     * @Return:
     */
    @ApiOperation("添加与修改教师信息")
    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(@ApiParam("JSON的Teacher格式对象，如果有id属性则为修改，否则为添加") @RequestBody Teacher teacher){
        teacherService.saveOrUpdate(teacher);
        return Result.ok();
    }

    /** 删除与批量删除教师信息
     * @Param:
     * @Return:
     */
    @ApiOperation("删除与批量删除教师信息")
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeachers(@ApiParam("要删除的所有grade的id的JSON集合") @RequestBody List<Integer> ids){
        teacherService.removeByIds(ids);
        return Result.ok();
    }
}
