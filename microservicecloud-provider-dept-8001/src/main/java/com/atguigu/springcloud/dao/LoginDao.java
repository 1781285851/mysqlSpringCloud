package com.atguigu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {

    int login(String username, String password);
}
