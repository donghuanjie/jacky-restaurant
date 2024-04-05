package com.donghuanjie.onlineOrder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

//@Configuration: 表明该类是一个配置类，Spring容器会在启动的时候自动扫描加载这个类中的Bean定义和服务。
//@EnableWebMvc: 启用Spring MVC，Spring的一个模块，用于构建Web应用程序。这个注解会自动注册所需的Spring MVC基础设施。
@Configuration
@EnableWebMvc
public class ApplicationConfig {
    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource()); // 在单独方法中定义
        sessionFactory.setPackagesToScan("com.donghuanjie.onlineOrder.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }
//  @Bean(name = "sessionFactory")注解表示这个方法会返回一个对象，该对象将被注册为Spring应用程序上下文中的一个Bean，Bean的名称是"sessionFactory"。
//  方法内部创建了一个LocalSessionFactoryBean对象，是Spring对Hibernate SessionFactory的封装，用于创建Hibernate会话。
//  SessionFactory是Hibernate中用于创建Session的工厂，而Session则是用于实现数据库操作的接口。

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        String RDS_INSTANCE = "onlineorder-instance.chkmygeyatw0.us-west-1.rds.amazonaws.com";
        String USERNAME = "admin";
        String PASSWORD = "86515305";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + RDS_INSTANCE +
                ":3306/onlineOrder?createDatabaseIfNotExist=true&serverTimezone=UTC");
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }
//    这个方法定义了一个名为dataSource的Bean，用于配置数据库连接。
//    使用的是Spring的DriverManagerDataSource，这是一个简单的数据源实现，主要用于测试和简单的应用程序。
//    设置数据库连接驱动类名为com.mysql.cj.jdbc.Driver，这是MySQL数据库的JDBC驱动。

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect",
                "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        return hibernateProperties;
    }
//    hibernate.hbm2ddl.auto: 设置为"update"，Hibernate会根据实体类中的注解自动更新数据库架构，而不是重新创建数据库。
//    hibernate.dialect: 指定了数据库方言，这里是MySQL5的InnoDB引擎方言，这让Hibernate知道如何生成针对MySQL数据库的SQL。
//    hibernate.show_sql: 设置为"true"，Hibernate会将执行的SQL语句打印到控制台，有助于调试。
}

