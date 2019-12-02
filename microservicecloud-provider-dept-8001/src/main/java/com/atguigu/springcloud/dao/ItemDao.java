package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Item;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ItemDao {

    //查询所有项目信息
    @Select("SELECT * FROM item WHERE isDelete = 1")
    List<Item> findAll();
    //添加项目信息
    @Insert("INSERT INTO item(code,qname,description,createDate,isDelete) VALUES (#{code},#{qname},#{description},#{createDate},1)")
    void addItem(Item item);
    //修改项目信息的状态
    @Update("update item set isDelete = 0 where code = #{code}")
    void alertIsDelete(String code);

}
