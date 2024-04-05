package com.donghuanjie.onlineOrder.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Cart cart;
//  这里举出6个中的4个例子，cascade为级联的意思，这里就代表着父子之间的关系，这里的父类就是customer，子类是cart
//  这里的All代表如果你保存（persist）当前实体到数据库，关联的Cart实体也会被保存。
//  如果你删除当前实体，关联的Cart实体也会从数据库中被删除。
//  这为管理实体间的关系提供了方便，因为你不需要手动去管理每一个关联实体的生命周期；所有相关的操作都会自动地被应用到Cart实体上。
//
//  ALL: 这是所有级联操作的组合。如果对一个实体进行任何操作（如保存、更新、删除、刷新、合并、分离），这些操作也会应用到所有映射的关联实体上。
//
//  PERSIST: 当父实体被持久化（保存）到数据库时，关联的子实体也会被自动保存。
//  这对于新创建的实体对象关系非常有用，可以确保当保存父实体时，所有的子实体也会被保存。
//
//  MERGE: 如果父实体状态变更被合并到当前的持久化上下文中，那么关联的子实体的状态也会被合并。
//  这在将实体的更改从一个持久化上下文传播到另一个持久化上下文时非常有用。
//
//  REMOVE: 当父实体被删除时，关联的子实体也会被自动删除。这适用于严格的父子关系，其中子实体没有独立于父实体存在的情况。

//  这里的@JoinColumn(unique = true)表示了连接到cart class的时候id必须不同，这里可以删除这句，因为cart中本就含有主键id
//  在JPA中，当你使用@JoinColumn注解来指定一个实体与另一个实体之间的关系时，JPA框架默认使用被引用实体的主键作为外键。
//  这意味着，如果Cart类中定义了一个主键ID，那么在实体中使用@JoinColumn annotation来建立与Cart的一对一关系时，它默认会指向Cart表的ID列。
//  如果cart中没有设置主键，则会抛出异常，根据JPA规范，每个实体类都必须有一个主键，这是因为JPA使用主键来唯一标识每个实体实例。
//  如果Cart类没有定义主键，JPA就无法生成表之间正确的关系映射和约束，也无法准确执行CRUD操作。

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
