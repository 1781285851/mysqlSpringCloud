package com.atguigu.springcloud.config;

import com.atguigu.springcloud.entities.Employee;
import com.atguigu.springcloud.entities.Menu;
import com.atguigu.springcloud.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee e = userService.findByUsername(username);
        if (e!=null){
            System.out.println("登陆信息为： "+e.toString());
            List<Menu> menus = userService.findMenusByUsername(username);
            System.out.println("权限信息为： "+ menus.toString());
            //
            /*String password = passwordEncoder.encode(e.getPassword());*/
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (Menu menu : menus) {
                authorities.add(new SimpleGrantedAuthority(menu.getCode()));
            }
            return new User(e.getQname(),e.getPassword(),authorities);
        }
       throw new UsernameNotFoundException("找不到"+username+"用户");
    }
}
