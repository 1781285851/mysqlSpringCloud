package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.LoginDao;
import com.atguigu.springcloud.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;


    @Override
    public Boolean login(String username, String password) {
        int i = loginDao.login(username, password);
        if (i!=1)
        return false;
        return true;
    }
}
