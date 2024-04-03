package com.donghuanjie.onlineOrder;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.json.JSONObject;
import org.apache.commons.io.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.donghuanjie.onlineOrder.entity.Customer;

// webservlet 就是 annotation package 底下的一个类，在不使用 spring 的时候需要自己定义，
// 现在已经在 web.xml 中打包了 servlet 不再需要自己定义
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    // 当Servlet被加载时，容器（如Tomcat）会首先调用init()方法。这是一个生命周期方法，只被调用一次，用于初始化工作。
    // 如果在init方法中初始化资源（如数据库连接、配置文件读取等），这些资源可以被所有请求共享，而不必为每个请求单独创建。这有助于资源利用率和性能。
    // init方法可以抛出ServletException，这允许你在初始化过程中处理异常情况。
//    private String message;
//    public void init() {
//        message = "Hello World!";
//    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 这里的 doGet 是个override function，如果要实现多个 get，则需要在这个 function 中实现各种 switch case 非常麻烦
        // 于是在 web.xml 中添加了 spring framework 的 servlet，servlet 的 mapping 是从 / 开始的，也可以设置为 /app

        // 告诉客户端（浏览器或任何发出HTTP请求的客户端），response 的数据类型是JSON格式
        // 当客户端向服务器发送请求时（浏览器、Postman、或任何HTTP客户端），它在HTTP请求头中指定Content-Type来告诉服务器它正在发送什么类型的数据Content-Type: application/json。
        // 当服务器准备好响应客户端时，通过response.setContentType("application/json") 来响应头中的Content-Type，告诉客户端它应该期待JSON格式的数据作为响应。
        response.setContentType("application/json");

        Customer customer = new Customer();
        customer.setEmail("jj@gmail.com");
        customer.setPassword("123456");
        customer.setFirstName("Jacky");
        customer.setLastName("Dong");
        customer.setEnabled(true);
        System.out.println(customer.getFirstName());

        ObjectMapper mapper = new ObjectMapper();
        // response.getWriter().print(customer); 这样不行，必须用 mapper
        response.getWriter().print(mapper.writeValueAsString(customer));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // ObjectMapper 是 jackson 中的 class，用来序列化和反序列化 json 和 Java object
        // mapper 用来 Serialize Java objects to JSON 也可以用来 Deserialize JSON into Java objects
        // mapper.writeValueAsString(customer) 和 mapper.readValue(jsonData, Customer.class) 来实现这个效果
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = mapper.readValue(request.getReader(), Customer.class);
        // 以下这行方法会先将 reader 内容转换成字符串，在调试时便于查看请求体内容，考虑性能建议使用上一行
        // Customer customer = mapper.readValue(IOUtils.toString(request.getReader()), Customer.class);
        System.out.println(customer.getEmail());
        System.out.println(customer.getLastName());
        System.out.println(customer.getPassword());

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().println(jsonResponse);
    }

    public void destroy() {
    }
}