package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Works;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface WorksService {

    //根据时间查询
    List<Works> findByDateService(Date start,Date end,Integer itemId);
    //查询所有
    List<Works> findAllervice();
    //添加信息
    Boolean addWorkservice(Works works);
    //从session获取当前登录的用户查询所有
    List<Works> findByEmpIdService(Integer empId);
    //从session获取当前登录的用户和条件查询
    List<Works>  findByEmpIdAndDate(Integer empId, Date start, Date end,Integer itemId);
    //查询本月的工作记录
    List<Works> findByThisMonth (Integer itemId)throws Exception;
    //根据本月查询
    List<Works> findByThisMonthAndEmpId(Integer itemId,Integer empId)throws Exception;

}
