package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Employee;
import com.atguigu.springcloud.entities.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeDao {
    //根据code查询用户
    @Select("select * from EMPLOYEE where isDelete = 1 and code = #{code}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column ="id", property ="roleIds", many =@Many(select ="com.atguigu.springcloud.dao.EmployeeDao.getRoleIdListById"))
    })
    Employee findByName(String code);
    //查询所有用户
    List<Employee> findAll();
    //添加用户
    void addEmployee(Employee employee);
    //根据code删除用户
    void alertStatus(String code);
    //根据姓名查询用户
    Employee findById(String qname);
    //根据用户名查询用户id
    int findIdByName(String name);

    @Select("select role_id from emp_role where emp_id =#{empId}")
    int[] getRoleIdListById(Integer empId);
    
    @Delete("delete from emp_role where emp_id =#{empId}")
    void cleanReferRoles(Integer empId);

    @Insert("insert into emp_role(emp_id, role_id) values(#{arg0}, #{arg1})")
    void refer(Integer emp_id, Integer role_id);

    @Select("select * from menu where id in (select distinct menu_id from role_menu where role_id in (select role_id from emp_role where emp_id = #{id}))")
    List<Menu> findMenuById(int id);
}
