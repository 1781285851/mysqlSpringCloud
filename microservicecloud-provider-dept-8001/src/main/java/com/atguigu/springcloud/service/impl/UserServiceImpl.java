package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.EmployeeDao;
import com.atguigu.springcloud.entities.Employee;
import com.atguigu.springcloud.entities.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    private EmployeeDao employeeDao;

    public List<Menu> findMenusByUsername(String username){
        int uid = employeeDao.findIdByName(username);
        return employeeDao.findMenuById(uid);
    }
    public Employee findByUsername(String username){
        Employee employee = employeeDao.findByUsername(username);
        return employee;
    }
}
