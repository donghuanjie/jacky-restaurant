package com.donghuanjie.onlineOrder.controller;

import com.donghuanjie.onlineOrder.entity.Customer;
import com.donghuanjie.onlineOrder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class SignUpController {
    //    Servlet容器（如Tomcat）负责管理HTTP请求的底层细节，根据请求的URL，将请求转发给匹配的Servlet。
//    在Spring MVC中，通常有一个DispatcherServlet，它作为前端控制器来处理所有进入应用程序的HTTP请求。
//    DispatcherServlet根据请求信息（如URL和HTTP方法）将请求映射到相应的Controller上的处理器方法。
//    这是通过@Controller注解的类和其内部使用@RequestMapping（或@GetMapping、@PostMapping等）注解的方法来完成的。
//    Controller层的方法处理请求，调用Service层来执行业务逻辑，并返回一个响应（通常是一个模型和视图名称，或者对于REST API，可能是JSON或XML格式的数据）。
//    Controller层通常不直接处理业务逻辑，而是将这些逻辑委托给Service层。
    private final CustomerService customerService;

    @Autowired
    public SignUpController(CustomerService customerService) {

        this.customerService = customerService;
    }
//    在这里如果简单的就在customerService中注入依赖则可能导致运行时才能检测出缺少依赖的问题。更推荐使用构造函数注入。chatgpt详细回答如下：
//    这里使用构造函数注入，字段注入通常不推荐因为以下几个原因：
//    测试难度: 使用字段注入时，不能在单元测试中（不使用spring的环境下），轻易地实例化该类并为其依赖赋值。使用构造器注入，可以手动创建依赖并传递给构造器，使得单元测试更加简单。
//    不可变性: 通过构造器注入，你可以保证依赖项一旦被设置就不可更改。这有助于确保对象的状态不会在创建后被修改，从而保持了类的不可变性。
//    强制依赖: 构造器注入强制要求依赖项在对象创建时就必须提供，而字段注入则没有这个强制性。这可以在编译时期就暴露出缺失依赖项的问题，而不是等到运行时才发现。
//    依赖关系的清晰: 构造器注入可以一目了然地表明一个类需要哪些依赖项，便于debug时的检查。
//    循环依赖: 如果有循环依赖的问题，使用构造器注入会在应用启动时立即暴露出问题，因为Spring无法处理构造器注入时的循环依赖。字段注入可能会隐藏这个问题直到第一次调用方法时。


//    @RequestMapping(value = "/signup", method = RequestMethod.POST)
//    public ResponseEntity<?> signUp(@RequestBody Customer customer) {
//        customerService.signUp(customer); // 不需要显式处理异常
//        return new ResponseEntity<>(HttpStatus.CREATED); // 如果没有异常，返回201
//    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void signUp(@RequestBody Customer customer) {
        customerService.signUp(customer);
    }

//    上面两种方式都可以，第一种方式可以控制返回的主体，但因为我们已经有 global exception handler 了，所以这里两者都可以用
}
