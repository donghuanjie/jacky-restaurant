package com.donghuanjie.onlineOrder.controller;

import com.donghuanjie.onlineOrder.entity.MenuItem;
import com.donghuanjie.onlineOrder.entity.Restaurant;
import com.donghuanjie.onlineOrder.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuInfoController {

    private final MenuInfoService menuInfoService;

    @Autowired
    public MenuInfoController(MenuInfoService menuInfoService) {
        this.menuInfoService = menuInfoService;
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    @ResponseBody
    public List<Restaurant> getRestaurants() {

        return menuInfoService.getRestaurants();
    }

    @RequestMapping(value = "/restaurant/{restaurantId}/menu", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuItem> getMenuItems(@PathVariable("restaurantId") int restaurantId) {
        return menuInfoService.getAllMenuItem(restaurantId);
    }
//    这里会有一个restaurantId对应不同的restaurant
//  @PathVariable("restaurantId") int restaurantId会自动把restaurantId parse成int给到method
}

//  方法使用@ResponseBody注解，这意味着返回值将直接写入HTTP响应正文中。因为方法返回的是List<MenuItem>类型的对象，
//  Spring MVC会自动将其序列化为JSON（或XML，取决于配置）格式。
//  使用@ResponseBody当你需要返回数据给客户端。
//  使用@ResponseStatus当你想要明确设置HTTP响应的状态码。
