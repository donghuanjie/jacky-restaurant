
// 这个文件和 ApplicationConfig.java 合并也可以，为了简洁美观分开了

package com.donghuanjie.onlineOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import javax.swing.*;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .failureForwardUrl("/login?error=true");
        http
                .authorizeRequests()
                .antMatchers("/order/*", "/cart", "/checkout").hasAuthority("ROLE_USER").anyRequest().permitAll();
    }
    // cross-site request forgery 指的是前后端分开 deploy 的时候出现了跨域的交互，有可能会被hack，但这个项目前后端都在EC2部署因此disable
    // 不disable的话前段会被block，没法测试
    // formLogin 就是 session based 方式的 authentication，之后设置了登录失败的跳转页面
    // 第二个http function定义了怎样才有权限对order，cart和checkout进行操作即需要是"ROLE_USER"

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("SELECT email, password, enabled FROM customers WHERE email = ? ")
                .authoritiesByUsernameQuery("SELECT email, authorities FROM authorities WHERE email = ? ");
    }
    //    jdbcAuthentication()：配置基于JDBC的认证，这意味着Spring Security将使用数据库来验证用户。
    //    dataSource(dataSource)：设置了用于查找用户信息的DataSource。
    //    passwordEncoder(passwordEncoder)：设置了密码的编码方式，Spring Security在比较用户输入的密码与存储在数据库中的密码时会使用这个编码器。
    //    usersByUsernameQuery：定义了查找用户信息的SQL查询。当用户尝试登录时，
    //    Spring Security将执行这个查询来获取用户的详细信息。
    //    authoritiesByUsernameQuery：定义了查找用户权限的SQL查询。这个查询用于确定用户有哪些权限。
}
