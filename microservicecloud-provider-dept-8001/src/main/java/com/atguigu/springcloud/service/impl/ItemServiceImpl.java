package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.ItemDao;
import com.atguigu.springcloud.entities.Item;
import com.atguigu.springcloud.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    /**
     * 查询项目信息列表
     * @return
     */
    @Override
    public List<Item> findAllService() {
        return itemDao.findAll();
    }

    /**
     * 添加项目信息
     * @param item
     */
    @Override
    public Boolean addItemService(Item item) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//获取系统时间
            item.setCreateDate(date);
            itemDao.addItem(item);
            return true;
        } catch (Exception e) {
            //1.获取异常信息，参数
            System.out.println(e.getMessage());
            //2.获取异常类名和异常信息
            System.out.println(e.toString());
            //3.获取异常类名和异常信息，及出现异常的位置
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据code删除用户（修改用户状态）
     * @param code
     */
    @Override
    public Boolean alertIsDeleteService(String code) {
        try {
            itemDao.alertIsDelete(code);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
