package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Works;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorksDao
{
    //根据时间查询
    List<Works> findByDate(@Param("start") String start, @Param("end")String end);
    //查询所有
    List<Works> findAll();
    //添加信息
    void addWorks(Works works);

    List<Works> findByEmpId(Integer empId);
}
