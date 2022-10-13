package com.example.myzhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myzhxy.pojo.Clazz;
import com.example.myzhxy.service.ClazzService;
import com.example.myzhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:50
 */
@Api(tags = "班级控制器")
@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /** 分页查询班级信息
     * @Param: Integer pageNo , Integer pageSize , Clazz clazz
     * @Return: Result
     */
    @ApiOperation("分页查询班级信息")
    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzsByOpr(@ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
                                 @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
                                 @ApiParam("查询条件") Clazz clazz){
//        设置分页信息
        Page<Clazz> page = new Page<>(pageNo,pageSize);
        IPage<Clazz> iPage = clazzService.getClazzByOpr(page,clazz);
        return Result.ok(iPage);
    }

    /** 添加与修改班级信息
     * @Param: Clazz clazz
     * @Return: Result
     */
    @ApiOperation("添加与修改班级信息")
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateGrades(@ApiParam("将请求头JSON转为Clazz") @RequestBody Clazz clazz){
        clazzService.saveOrUpdate(clazz);
        return Result.ok();
    }

    /** 删除与批量删除班级信息
     * @Param: List<Integer> ids
     * @Return: Result
     */
    @ApiOperation("删除与批量删除班级信息")
    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(@ApiParam("获取请求头信息的id集合") @RequestBody List<Integer> ids){
        clazzService.removeByIds(ids);
        return Result.ok();
    }

    /** 教师管理中回显班级信息
     * @Param: null
     * @Return: Result
     */
    @ApiOperation("教师管理中回显班级信息")
    @GetMapping("/getClazzs")
    public Result getClazz(){
        List<Clazz> list = clazzService.getClazz();
        return Result.ok(list);
    }
}
