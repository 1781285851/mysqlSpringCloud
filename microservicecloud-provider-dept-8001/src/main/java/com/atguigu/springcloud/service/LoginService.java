package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Menu;

import java.util.List;

public interface LoginService {
    Boolean login(String username, String password);

    List<Menu> getMenusByUsername(String username);
}
