package com.donghuanjie.onlineOrder.service;

import com.donghuanjie.onlineOrder.entity.Cart;
import com.donghuanjie.onlineOrder.entity.Customer;
import com.donghuanjie.onlineOrder.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
//    Service 层位于 DAO 层和 Controller 层之间，负责业务逻辑的实现。
//    这个层次通常会调用一个或多个DAO来获取、处理和组合数据，实现特定的业务规则或计算。
    private final CustomerDao customerDao;
    private final PasswordEncoder passwordEncoder;
//    PasswordEncoder实例的最合适位置就是在这层，服务层（Service layer），而不是数据访问层（DAO layer）。
//    服务层负责应用程序的业务逻辑，而数据访问层主要负责与数据库进行交互。
//    密码编码（加密）是一种业务逻辑，因为它定义了如何安全地处理用户密码，将密码编码的逻辑放在服务层可以保持数据访问层的专注于其主要职责——即数据持久化

    @Autowired
    public CustomerService(CustomerDao customerDao, PasswordEncoder passwordEncoder) {
        this.customerDao = customerDao;
        this.passwordEncoder = passwordEncoder;
    }
//    构造函数用来构造CustomerService的方式相比 CustomerDao 中更好

    public void signUp(Customer customer) {
        Cart cart = new Cart();
        customer.setCart(cart);
        customer.setEnabled(true);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerDao.signUp(customer);
    }

    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }
}
