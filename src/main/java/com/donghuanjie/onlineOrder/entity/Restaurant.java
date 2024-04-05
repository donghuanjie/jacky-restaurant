package com.donghuanjie.onlineOrder.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private int id;
    private String address;
    private String name;
    private String phone;
    private String imageUrl;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MenuItem> menuItemList;
//  单向@OneToMany关系默认可能不如双向关系高效，因为JPA实现通常使用一个中间连接表来维护这种单向关系。
//  不过，如果@OneToMany关系在menuItem上定义了mappedBy属性，那么JPA会使用menuItem的外键列来直接管理这种关系，而不是一个额外的表。
//  这里的cascade意思就是如果我这个restaurant删除或者改变了menu，在menuItem中也会改变

//  fetch有eager和lazy两个选项
//  eager是当主实体加载到内存中时，与之关联的实体也会立即被加载。适用于那些关联实体总是一起使用，且关联数量不多，不会造成明显性能问题的场景。
//  默认情况下，推荐使用FetchType.LAZY以提高应用程序的性能和资源利用效率。


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }
}
