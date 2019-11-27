package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private Integer id;
    private String code;
    private String qname;
    private Integer gender;
    private String password;
    private String phone;
    private String description;
    private Integer auth;
    private Integer status;
}
