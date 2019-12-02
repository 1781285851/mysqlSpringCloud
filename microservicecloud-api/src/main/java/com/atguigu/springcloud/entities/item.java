package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class item implements Serializable {
    //id
    private Integer id;
    //项目编号
    private String code;
    //项目名称
    private String qname;
    //项目描述
    private String describe ;
    //创建时间
    private Date createDate;
    //更新时间
    private Date updateDate;
    //项目状态(1:正常，0：删除)
    private Integer isDelete;
}
