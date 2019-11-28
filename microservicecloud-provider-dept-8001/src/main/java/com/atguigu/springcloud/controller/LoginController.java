package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.http.MessageCode;
import com.atguigu.springcloud.http.SoftworksResponse;
import com.atguigu.springcloud.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @GetMapping("/login")
    public SoftworksResponse<Boolean> login(
            @RequestParam(value = "username",required = true)String username,
            @RequestParam(value = "password",required = true)String password, HttpServletRequest request){
        Boolean result = loginService.login(username,password);
        if (result){
            request.getSession().setAttribute("username",username);
            return SoftworksResponse.success(result);
        }
        return SoftworksResponse.failure(MessageCode. COMMON_USER_LOGIN_FAIL);
    }

    @GetMapping("/clear")
    public SoftworksResponse<Boolean> clear(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        return SoftworksResponse.success(true);
    }

}
