package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    //主键id
    private Integer id;
    //用户编码
    private String code;
    //用户姓名
    private String qname;
    //性别 1：男 0：女
    private Integer gender;
    //密码
    private String password;
    //电话
    private String phone;
    //备注
    private String description;
    //权限 1：用户 0：管理员
    private Integer auth;
    //状态 1：正常 0：删除
    private Integer status;
}
