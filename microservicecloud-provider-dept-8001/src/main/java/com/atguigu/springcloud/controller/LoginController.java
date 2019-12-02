package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Menu;
import com.atguigu.springcloud.http.MessageCode;
import com.atguigu.springcloud.http.SoftworksResponse;
import com.atguigu.springcloud.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @GetMapping("/login")
    public SoftworksResponse<List<Menu>> login(
            @RequestParam(value = "username",required = true)String username,
            @RequestParam(value = "password",required = true)String password, HttpServletRequest request){
        Boolean result = loginService.login(username,password);
        if (result){
            HttpSession session = request.getSession();
           session.setAttribute("username",username);
            session.setMaxInactiveInterval(2*60*60);
            return SoftworksResponse.success(loginService.getMenusByUsername(username));
        }
        return SoftworksResponse.failure(MessageCode. COMMON_USER_LOGIN_FAIL);
    }

    @GetMapping("/logout")
    public SoftworksResponse<Boolean> clear(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        return SoftworksResponse.success(true);
    }

}
