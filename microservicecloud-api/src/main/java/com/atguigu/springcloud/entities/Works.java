package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Works {
    //主键id
    private Integer id;
    //创建时间
    private Date createDate;
    //工作详情
    private String workContent;
    //用户id
    private Integer empId;
}