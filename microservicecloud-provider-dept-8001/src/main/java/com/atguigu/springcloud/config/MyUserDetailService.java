package com.atguigu.springcloud.config;

import com.atguigu.springcloud.entities.Employee;
import com.atguigu.springcloud.entities.Menu;
import com.atguigu.springcloud.service.impl.UserServiceImpl;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee e = userService.findByUsername(username);
        if (e != null) {
            log.info("登陆信息为： " + e.toString());
            List<Menu> menus = userService.findMenusByUsername(username);
            log.info("权限信息为： " + menus.toString());
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (Menu menu : menus) {
                authorities.add(new SimpleGrantedAuthority(menu.getCode()));
            }
            return new User(e.getQname(), e.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("找不到" + username + "用户");
    }
}
