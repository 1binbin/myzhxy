package com.example.myzhxy.controller;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myzhxy.pojo.Student;
import com.example.myzhxy.service.StudentService;
import com.example.myzhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:50
 */
@Api(tags = "学生控制器")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /** 分页查询学生信息
     * @Param:
     * @Return:
     */
    @ApiOperation("分页查询学生信息")
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(@ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
                                  @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
                                  Student student){
        Page<Student> studentPage = new Page<>(pageNo,pageSize);
        IPage<Student> iPage = studentService.getStudentByOpr(student,studentPage);
        return Result.ok(iPage);
    }


}
