package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.EmployeeDao;
import com.atguigu.springcloud.dao.LoginDao;
import com.atguigu.springcloud.entities.Employee;
import com.atguigu.springcloud.entities.Menu;
import com.atguigu.springcloud.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

    @Autowired
    private EmployeeDao employeeDao;


    @Override
    public Boolean login(String username, String password) {
        int i = loginDao.login(username, password);
        if (i!=1)
            return false;
        return true;
    }

    public Map<Employee,List<Menu>> getMenusByUsername(String username){
        Map map = new HashMap();
        int id = employeeDao.findIdByName(username);
        Employee employee = employeeDao.findByUsername(username);
        List<Menu> menus = employeeDao.findMenuById(id);
        map.put(employee,menus);
        return map;
    }
}
