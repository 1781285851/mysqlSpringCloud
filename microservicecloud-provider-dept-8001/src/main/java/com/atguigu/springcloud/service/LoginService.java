package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Employee;
import com.atguigu.springcloud.entities.Menu;

import java.util.List;
import java.util.Map;

public interface LoginService {
    Boolean login(String username, String password);

    Map<Employee,List<Menu>> getMenusByUsername(String username);
}
