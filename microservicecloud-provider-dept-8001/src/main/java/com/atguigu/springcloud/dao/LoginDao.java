package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoginDao {

    int login(@Param("username") String username, @Param("password") String password);

    List<Menu> Menus_Uid(Integer uid);
}
