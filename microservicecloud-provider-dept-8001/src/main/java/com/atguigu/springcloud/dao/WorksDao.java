package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Works;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Mapper
public interface WorksDao
{
    //根据时间查询
    List<Works> findByDateAndItemId(@Param("start") Date start, @Param("end")Date end, @Param("itemId")Integer itemId);
    //查询所有
    List<Works> findAll();
    //添加信息
    void addWorks(Works works);
    //从session获取当前登录的用户查询所有
    List<Works> findByEmpId(Integer empId);
    //从session获取当前登录的用户和条件查询
    List<Works>  findByEmpIdAndDateAndItemId(@Param("empId")Integer empId, @Param("start") Date start, @Param("end")Date end,@Param("itemId")Integer itemid);
    //根据id查询详情
    @Select("select * from works where id = #{id}")
    Works findById(Integer id);
}
