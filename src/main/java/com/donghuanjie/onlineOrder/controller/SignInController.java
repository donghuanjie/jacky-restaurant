package com.donghuanjie.onlineOrder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SignInController {
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 这里只处理登录失败的情况，如果登录成功，会被跳转
    @RequestMapping(value = "/login")
    public void login(@RequestParam(value = "error") String error, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put("message", "bad credentials");
        response.getOutputStream()
                .println(objectMapper.writeValueAsString(data));
    }
}

//当使用Spring Security时，登录过程是通过Spring Security的过滤器链来管理的。
//这通常包括身份验证过滤器，如 UsernamePasswordAuthenticationFilter，它拦截登录表单的提交并尝试对用户进行身份验证。
//
//根据认证成功或失败，Spring Security将采取不同的行动：

//登录成功：如果用户成功认证，Spring Security将根据配置重定向到应用程序指定的页面。
//这通常通过配置在 HttpSecurity 配置中的 .defaultSuccessUrl("/home") 方法来指定。您不需要在控制器中手动处理成功的登录，因为Spring Security已经为您处理好了。

//登录失败：如果认证失败，例如由于提供了错误的用户名或密码，Spring Security将重定向到登录页面，并且可以添加一个错误参数，
//如 /login?error。这就是您在 SignInController 中检查的 error 参数。
//在这种情况下，您可以自定义错误处理，像您提供的代码那样，通过设置响应状态和返回自定义的错误信息。