package com.example.myzhxy.controller;

import com.example.myzhxy.pojo.Admin;
import com.example.myzhxy.pojo.LoginForm;
import com.example.myzhxy.pojo.Student;
import com.example.myzhxy.pojo.Teacher;
import com.example.myzhxy.service.AdminService;
import com.example.myzhxy.service.StudentService;
import com.example.myzhxy.service.TeacherService;
import com.example.myzhxy.utils.CreateVerifiCodeImage;
import com.example.myzhxy.utils.JwtHelper;
import com.example.myzhxy.utils.Result;
import com.example.myzhxy.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 对于非表格操作的控制层
 *
 * @Author hongxiaobin
 * @Time 2022/10/11-20:54
 */
@RestController
@RequestMapping("/sms/system")
public class SystemController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 校验登录是否成功
     *
     * @Param: LoginForm loginForm 接收请求体数据
     * @Return: Result<LoginForm>
     */
    // TODO: 2022/10/12 校验登录是否成功
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
//        验证码校验
        HttpSession session = request.getSession();
        String verifiCode = (String) session.getAttribute("verifiCode");
        String formVerifiCode = loginForm.getVerifiCode();
        if ("".equals(formVerifiCode) || null == formVerifiCode) {
            return Result.fail().message("验证码失效，请刷新后重试");
        }
        if (!verifiCode.equalsIgnoreCase(formVerifiCode)) {
            return Result.fail().message("验证码有误，请重新输入");
        }
//        从session域对象中移除这个验证码
        session.removeAttribute("verifiCode");

//        分用户类型进行校验
//        准备一个map用于存放响应的数据
        Map<String, Object> map = new HashMap<>();
        switch (loginForm.getUserType()) {
            case 1:
                try {
//                管理员
                    Admin admin = adminService.login(loginForm);
                    if (null != admin) {
                        //                    用户的类型和用户id转换成一个密文，以token的名称向客户端反馈
                        String token = JwtHelper.createToken(Long.valueOf(admin.getId()), 1);
                        map.put("token", token);
                    } else {
                        throw new RuntimeException("用户名或者密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    return Result.fail().message(e.getMessage());
                }
            case 2:
//                学生
                try {
                    Student student = studentService.login(loginForm);
                    if (null != student) {
                        //                    用户的类型和用户id转换成一个密文，以token的名称向客户端反馈
                        String token = JwtHelper.createToken(Long.valueOf(student.getId()), 2);
                        map.put("token", token);
                    } else {
                        throw new RuntimeException("用户名或者密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    return Result.fail().message(e.getMessage());
                }
            case 3:
//                教师
                try {
                    Teacher teacher = teacherService.login(loginForm);
                    if (null != teacher) {
                        //                    用户的类型和用户id转换成一个密文，以token的名称向客户端反馈
                        String token = JwtHelper.createToken(Long.valueOf(teacher.getId()), 3);
                        map.put("token", token);
                    } else {
                        throw new RuntimeException("用户名或者密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    return Result.fail().message(e.getMessage());
                }
            default:
                return Result.fail().message("查无此用户");
        }
    }

    /** 登录成功响应数据
     * @Param:
     * @Return:
     */
    // TODO: 2022/10/12 登录成功响应数据
    @GetMapping("/getInfo")
    public Result getInfoByToken(@RequestHeader("token") String token){
//        验证token是否过期
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
//        解析出用户名和用户类型
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);
//        根据不同的用户类型跳转
        Map<String ,Object> map= new LinkedHashMap<>();
        switch (userType){
            case 1:
//                获取用户信息
                Admin admin = adminService.getAdminById(userId);
                map.put("userType",1);
                map.put("user",admin);
                break;
            case 2:
                Student student = studentService.getStudentById(userId);
                map.put("userType",2);
                map.put("user",student);
                break;
            case 3:
                Teacher teacher = teacherService.getTeacherById(userId);
                map.put("userType",3);
                map.put("user",teacher);
                break;
            default:
        }
        return Result.ok(map);
    }

    /**
     * 获取验证码图片
     *
     * @Param:
     * @Return:
     */
    // TODO: 2022/10/11 获取验证码图片
    @GetMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response) {
//        获取图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
//        获取图片上的验证码
        String verifiCode = new String(CreateVerifiCodeImage.getVerifiCode());
//        将验证码文本放入session域对象中，为下一次验证做准备
        HttpSession session = request.getSession();
        session.setAttribute("verifiCode", verifiCode);
//        将验证码响应给浏览器
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(verifiCodeImage, "JPEG", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
