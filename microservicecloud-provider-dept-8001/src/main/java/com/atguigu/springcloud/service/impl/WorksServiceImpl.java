package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.WorksDao;
import com.atguigu.springcloud.entities.Works;
import com.atguigu.springcloud.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorksServiceImpl implements WorksService {
    @Autowired
    private WorksDao worksDao;

    //根据日期查询
    @Override
    public List<Works> findByDateService(String start, String end) {
        return worksDao.findByDate(start, end);
    }

    //查询所有
    @Override
    public List<Works> findAllervice() {
        return worksDao.findAll();
    }

    //添加信息
    @Override
    public Boolean addWorkservice(Works works) {
        try {
            works.setCreateDate(new Date());
            worksDao.addWorks(works);
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
}
