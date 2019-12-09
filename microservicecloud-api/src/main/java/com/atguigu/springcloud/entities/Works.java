package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Works implements Serializable {
    //主键id
    private Integer id;
    //创建时间
    private Date createDate;
    //工作详情
    private String workContent;
    //用户id
    private Integer empId;
    //项目id
    private Integer itemId;
    //用户名
    private String username;
}