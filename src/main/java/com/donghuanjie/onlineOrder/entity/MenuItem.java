package com.donghuanjie.onlineOrder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "menuitem")
public class MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private int id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;
//  不设置cascade级联属性是很常见的，特别是在多对一（@ManyToOne）的关系中。
//  如果你不设置cascade属性，这意味着你需要手动管理关联实体的生命周期，JPA不会自动传播状态变化。

//  当两个类实例化有双向关系时，会出现循环引用的问题，这里使用JsonIgnore来解决
//  当你标注一个类的属性或者方法使用@JsonIgnore，那么在使用Jackson进行JSON序列化或反序列化时，该属性或方法不会被包含在JSON中。这常用于以下几种情况：
//  隐藏敏感信息：例如，你可能不希望将密码或个人身份信息等敏感数据暴露在通过API传输的JSON数据中。
//  避免循环引用：在双向关联的实体间（如双向的一对多关系），使用@JsonIgnore可以帮助避免无限递归问题，该问题发生在当Jackson试图序列化相互引用的对象时。
//
//  这里就是restaurant class会有菜的list，而如果菜的list中又有餐馆那么就会无限套娃，因此我们不希望菜品的json中带上restaurant
//
//  那是否可以不在这个class中定义restaurant以及ManyToOne的关联？
//  数据访问方向：定义了单向@OneToMany关系后，你可以从Restaurant访问其所有menuItem，
//  但如果在这不定义ManyToOne，从menuItem访问其Restaurant将不再包含在通过JPA(Java Persistence API)自动管理的关系中，
//  但是，由于你在menuItem中使用了@JsonIgnore，在通过API进行序列化时，menuItem不会序列化其restaurant信息。
//
//  性能考虑：单向@OneToMany关系默认可能不如双向关系高效，因为JPA实现通常使用一个中间连接表来维护这种单向关系。
//  不过，如果@OneToMany关系在menuItem上定义了mappedBy属性，那么JPA会使用menuItem的外键列来直接管理这种关系，而不是一个额外的表。
//
//  更新和删除操作：在没有双向关系的情况下，你需要小心处理删除或更新操作，确保数据库的完整性和一致性。
//  特别是在删除Restaurant时，可能需要手动处理或删除相关的menuItem，以避免违反数据库约束。

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
