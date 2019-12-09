package com.atguigu.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //  启用方法级别的权限认证
public class SerurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/employee/","/employee/page_list","/works/page_list_all").hasRole("ADMIN");
        //开启自动配置的登陆功能
        http.formLogin().and().sessionManagement().invalidSessionUrl("/");
        //开启自动配置的注销功能
        http.logout().logoutUrl("/logout2").logoutSuccessUrl("/").deleteCookies("JSESSIONID");*/
        http.csrf().disable();
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //在内存中查找用户
        /*auth.inMemoryAuthentication()
                .withUser("zhangsan").password("123").roles("ADMIN")
                .and()
                .withUser("lisi").password("1234").roles("USER");*/
        //
        /*auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select qname,password,enabled from employee where qname = ?")
                .authoritiesByUsernameQuery()*/
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
