package com.donghuanjie.onlineOrder.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    // 这里的 auto generator 也是 javax.persistence 中的 class，自动生成 primary key

    private double totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
