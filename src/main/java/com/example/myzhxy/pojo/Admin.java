package com.example.myzhxy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员实体类
 *
 * @Author hongxiaobin
 * @Time 2022/10/11-19:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_admin")
public class Admin {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Character gender;
    private String password;
    private String email;
    private String telephone;
    private String address;
    //    头像图片路径
    private String portraitPath;
}
