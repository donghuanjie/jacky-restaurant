package com.donghuanjie.onlineOrder.entity;

import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.security.acl.Owner;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    // 这里的 auto generator 也是 javax.persistence 中的 class，自动生成 primary key
    // generation type中有多种选择

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItemList;
//  拥有端（Owner Side）：通常使用@ManyToOne注解的一方是关系的拥有端（拥有依赖的主键属性）。
//  因为在数据库层面，外键关系是通过在“多”的一端的表中使用外键列来实现的。因此，这一端负责维护关系，包括更新外键列以指向“一”的一端的正确记录。
//  被维护端（Non-owning Side）：使用@OneToMany注解的一方是被维护端，它通过mappedBy属性指向关系的拥有端的属性名。
//  这表明该方向的关系是通过反向端的字段（拥有端的实体属性）来映射的，自己拥有对应的外键。
//  如果在两端都有关系表示，这需要在OneToMany这端加上mappedBy属性

//  这里使用的CascadeType.ALL确实意味着如果你删除了一个Cart实例，那么与之关联的所有OrderItem实例也会被自动删除。
//  同样，如果你持久化（保存）一个Cart实例，那么与之关联的所有OrderItem实例也会被自动持久化。

    private double totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
