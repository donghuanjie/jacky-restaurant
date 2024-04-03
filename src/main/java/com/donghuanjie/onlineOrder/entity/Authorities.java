package com.donghuanjie.onlineOrder.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {
//  @Table 里的name是在数据库里存的table name，如果和class name一样则可以省略
//  serializable 指存到硬盘后再把他取出来时将传输数据变为对象的过程
//  网络之间的传输是序列化 json，这里则implements serializable 实现

    private static final long serialVersionUID = 1L;
//  serialVersionUID是Java序列化机制中非常重要的一个版本控制标识符。
//  如果一个可序列化的类没有显式地声明一个serialVersionUID，那么Java运行时会基于类的详细信息自动生成一个。
//  但这个自动生成的值对类的细微变化非常敏感，例如，仅仅添加一个方法就可能改变自动生成的值。
//  每个通过实现java.io.Serializable接口可以序列化的类都被强烈建议声明这样一个serialVersionUID字段。
//  它用于验证序列化的对象和对应类定义时是否版本匹配。
//  如果更新了类中的变量或者方法，如果不更新serialVersionUID，尽管类已经变化，当旧版本的应用尝试反序列化新版本User类（带有更新的字段）的对象时，
//  由于serialVersionUID没有变化，旧版本的应用会认为它是相同版本的User对象并尝试反序列化。
//  但因为旧版本的User类不包含age字段，这可能导致反序列化过程失败，抛出InvalidClassException，或者在反序列化后得到的对象状态不正确。

    @Id
    private String email;
    private String authorities;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
