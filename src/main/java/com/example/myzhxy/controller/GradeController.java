package com.example.myzhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myzhxy.pojo.Grade;
import com.example.myzhxy.service.GradeService;
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
@Api(tags = "年级控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    /**
     * 年级信息展示
     *
     * @Param: pageNo pageSize gradeName
     * @Return: Result
     */
    // TODO: 2022/10/12 年级信息显示
    @ApiOperation("搜索与显示年级信息")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
//    gradeName的名字与模糊查询时请求参数一致即可自动匹配，不写@RequestParam因为没有查询时没有这个请求参数
    public Result getGrades(@ApiParam("起始页码") @PathVariable("pageNo") Integer pageNo,
                            @ApiParam("每一页有几条数据") @PathVariable("pageSize") Integer pageSize,
                            String gradeName) {
//        分页带条件查询
        Page<Grade> page = new Page<>(pageNo, pageSize);
//        通过service查询
        IPage<Grade> iPage = gradeService.getGradeByOpr(page, gradeName);
//        封装Result对象并返回
        return Result.ok(iPage);
    }

    /**
     * 修改与保存业务，带有id为修改
     *
     * @Param: Grade grade
     * @Return: Result
     */
    // TODO: 2022/10/12  修改与保存业务，带有id为修改
    @ApiOperation("新增或修改grade")
    @PostMapping("/saveOrUpdateGrade")
//   请求体中拿到JSON数据转为Grade
    public Result saveOrUpdateGrade(@ApiParam("JSON的Grade格式对象，如果有id属性则为修改，否则为添加") @RequestBody Grade grade) {
//        接受参数并调用service完成添加或修改
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }

    /**
     * 删除与批量删除业务
     *
     * @Param: List<Integer> ids
     * @Return: Result
     */
    @ApiOperation("删除Grade年级信息")
    @DeleteMapping("/deleteGrade")
    public Result deleteGrade(@ApiParam("要删除的所有grade的id的JSON集合") @RequestBody List<Integer> ids) {
        gradeService.removeByIds(ids);
        return Result.ok();
    }

    /** 班级管理中回显年级信息
     * @Param:
     * @Return:
     */
    @ApiOperation("班级管理回显年级信息")
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> listGrades = gradeService.getGrades();
        return Result.ok(listGrades);
    }
}
