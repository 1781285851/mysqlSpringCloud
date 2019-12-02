package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.EmployeeDao;
import com.atguigu.springcloud.dao.LoginDao;
import com.atguigu.springcloud.entities.Menu;
import com.atguigu.springcloud.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Menu> getMenusByUsername(String username){
        int id = employeeDao.findIdByName(username);
        List<Menu> menus = employeeDao.findMenuById(id);
        return menus;
    }
}
