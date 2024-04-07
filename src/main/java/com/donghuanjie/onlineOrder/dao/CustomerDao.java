package com.donghuanjie.onlineOrder.dao;

import com.donghuanjie.onlineOrder.entity.Authorities;
import com.donghuanjie.onlineOrder.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class CustomerDao {
//    DAO 层负责与数据库直接交互。它提供了一系列方法来执行CRUD（创建、读取、更新、删除）操作和其他数据库操作。
//    这个层次隐藏了数据访问的细节，使上层的服务不需要知道底层使用的是什么数据库或者是JDBC、Hibernate或者JPA等技术。

//    @Repository是一个特化的@Component注解，它被用来标记数据访问对象（DAO），主要用于与数据库的交互。
//    使用@Repository注解的类被认为是Repository层在Domain-Driven Design（领域驱动设计）中的一部分。
//    @Repository还拥有将JDBC的数据访问异常转换为Spring的DataAccessException层次结构的能力，为开发者提供了一致的异常处理策略。

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // create
    public void signUp(Customer customer) {
        Authorities authorities = new Authorities();
        authorities.setAuthorities("ROLE_USER");
        authorities.setEmail(customer.getEmail());

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(customer);
            session.getTransaction().commit();
//            出错回滚，可以用spring的@Transactional来实现
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback();
            throw ex;
//            throw ex 保证如果保存出错可以把exception throw出去，然后让spring来handle
        } finally {
            if (session != null) {
                session.close();
            }
//            不论transaction成功还是失败，都需要把session关掉
        }
    }

    // read
    public Customer getCustomer(String email) {
        Customer customer = null;
        try (Session session = sessionFactory.openSession()) {
            customer = session.get(Customer.class, email);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customer;
    }
//    这是一种Java的“try-with-resources”语法，将openSession放入try的括号中，则不需要像上面的语法一样close，当然也可以写成上面那样
//    这种方式提供了一种更简洁的方式，当退出try块的作用域时，会自动调用Session对象的close方法，无需手动编写代码来关闭
}
