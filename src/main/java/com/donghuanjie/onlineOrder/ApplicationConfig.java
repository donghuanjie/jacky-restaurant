package com.donghuanjie.onlineOrder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class ApplicationConfig {

//    @Bean(name = "sessionFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("com.donghuanjie.onlineOrder.entity");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        return sessionFactory;
//    }
//
//    @Bean(name = "dataSource")
//    public DataSource dataSource() {
//        String RDS_INSTANCE = "";
//        String USERNAME = "";
//        String PASSWORD = "";
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://" + RDS_INSTANCE +
//                ":3306/onlineOrder?createDatabaseIfNotExist=true&serverTimezone=UTC");
//        dataSource.setUsername(USERNAME);
//        dataSource.setPassword(PASSWORD);
//
//        return dataSource;
//    }
}

//@Configuration 注解表明该类是一个Spring配置类，并且可能包含一个或多个@Bean定义。Spring将扫描这些注解，以注册Bean定义到Spring IoC容器中。

//@EnableWebMvc 是一个便捷的注解，它自动配置Spring MVC的基本组件。它导入了WebMvcConfigurationSupport，为Spring MVC应用程序提供了基本的配置。
// 例如，它配置了ContentNegotiatingViewResolver、MessageConverter等，这是Spring MVC处理请求和响应的核心。

//@Bean 注解标记在方法上，表明返回的对象应该被注册为Spring应用上下文中的Bean。通常，方法名将作为Bean的名称。

