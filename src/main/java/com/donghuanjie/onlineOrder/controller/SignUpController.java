package com.donghuanjie.onlineOrder.controller;

import com.donghuanjie.onlineOrder.entity.Customer;
import com.donghuanjie.onlineOrder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SignUpController {
    private CustomerService customerService;

    @Autowired
    public SignUpController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void signUp(@RequestBody Customer customer) {
        // RequestBody 自动将传来的json string转换为customer
        customerService.signUp(customer);
    }

    // 以下这种方法适用于自定是否成功时的 request return code
    // public void signUp(@RequestBody Customer customer, HttpServletResponse req) {
    //        // RequestBody 自动将传来的json string转换为customer
    //        System.out.println("connected");
    //        req.setStatus(201);
    //        req.setStatus(400);
//    }
}
