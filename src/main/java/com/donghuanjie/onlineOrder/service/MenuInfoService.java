package com.donghuanjie.onlineOrder.service;

import com.donghuanjie.onlineOrder.dao.MenuInfoDao;
import com.donghuanjie.onlineOrder.entity.MenuItem;
import com.donghuanjie.onlineOrder.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuInfoService {

    private final MenuInfoDao menuInfoDao;

    @Autowired
    public MenuInfoService(MenuInfoDao menuInfoDao) {
        this.menuInfoDao = menuInfoDao;
    }

    public List<Restaurant> getRestaurants() {
        return menuInfoDao.getRestaurants();
    }

    public List<MenuItem> getAllMenuItem(int restaurantId) {
        return menuInfoDao.getAllMenuItem(restaurantId);
    }

    public MenuItem getMenuItem(int id) {
        return menuInfoDao.getMenuItem(id);
    }
}
