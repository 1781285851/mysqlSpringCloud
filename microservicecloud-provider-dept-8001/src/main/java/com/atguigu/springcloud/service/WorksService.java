package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Works;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

public interface WorksService {

    //根据时间查询
    List<Works> findByDateService(String start,String end);
    //查询所有
    List<Works> findAllervice();
    //添加信息
    Boolean addWorkservice(Works works);

    List<Works> findByEmpIdService(Integer empId);

}
