package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Employee;
import com.atguigu.springcloud.entities.Menu;
import com.atguigu.springcloud.http.MessageCode;
import com.atguigu.springcloud.http.SoftworksResponse;
import com.atguigu.springcloud.service.LoginService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@Log4j
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public SoftworksResponse<Map<String,Object>> login(
            @RequestParam(value = "username",required = true)String username,
            @RequestParam(value = "password",required = true)String password, HttpServletRequest request){
        Boolean result = loginService.login(username,password);
        if (result){
            HttpSession session = request.getSession();
             session.setAttribute("username",username);
            session.setMaxInactiveInterval(2*60*60);
            System.out.println(session);
            return SoftworksResponse.success(loginService.getMenusByUsername(username));
        }
        return SoftworksResponse.failure(MessageCode. COMMON_USER_LOGIN_FAIL);
    }

    @GetMapping("/logout")
    public SoftworksResponse<Boolean> clear(@RequestParam(value = "username")String username,HttpServletRequest request){
        try {
            request.getSession().removeAttribute(username);
            log.info("退出");
            return SoftworksResponse.success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return SoftworksResponse.failure(e.getMessage());
        }
    }
}
