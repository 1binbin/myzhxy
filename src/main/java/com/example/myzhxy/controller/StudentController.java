package com.example.myzhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myzhxy.pojo.Student;
import com.example.myzhxy.service.StudentService;
import com.example.myzhxy.utils.MD5;
import com.example.myzhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @Param: Integer pageNo , Integer pageSize , Student student
     * @Return: Result
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

    /** 添加与修改学生信息
     * @Param: Student student
     * @Return: Result
     */
    @ApiOperation("添加与修改学生信息")
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(@ApiParam("将JSON数据转为Student对象") @RequestBody Student student){
        Integer id = student.getId();
        if (null == id || 0 == id) {
            student.setPassword(MD5.encrypt(student.getPassword()));
        }
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

    /** 删除与批量删除学生信息
     * @Param: List<Integer> ids
     * @Return: Result
     */
    @ApiOperation("删除与批量删除学生信息")
    @DeleteMapping("/delStudentById")
    public Result deleteStudent(@ApiParam("要删除学生的id的JSON集合") @RequestBody List<Integer> ids){
        studentService.removeByIds(ids);
        return Result.ok();
    }
}
