package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {
    private int id;
    private String url;
    private String path;
    private String component;
    private String name;
    private String iconCls;
    private int keepAlive;
    private int requireAuth;
    private String code;
}
