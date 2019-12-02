package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Item;

import java.util.List;

public interface ItemService {

    //查询所有项目信息
    List<Item> findAllService();
    //添加项目信息
    Boolean addItemService(Item item);
    //修改项目信息的状态
    Boolean alertIsDeleteService(String code);

}
