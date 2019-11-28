package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Employee;

import java.util.List;

public interface EmployeeService {

    //根据code查询用户基本信息
    public Employee findByNameService(String code);

    //查询所有用户
    public List<Employee> findAllService();

    //添加用户
    public Boolean addEmployeeService(Employee employee);

    //根据code删除用户
    public Boolean removeByNameService(String code);

    //根据name查询用户基本信息
    public Employee findByIdService(String qname);

    int findIdByName(String name);
}
