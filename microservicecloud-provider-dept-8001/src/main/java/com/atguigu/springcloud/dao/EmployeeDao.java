package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Employee;
import com.atguigu.springcloud.entities.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeDao {
    //根据id查询用户
    @Select("select * from EMPLOYEE where isDelete = 1 and id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "roleIds", many = @Many(select = "com.atguigu.springcloud.dao.EmployeeDao.getRoleIdListById"))
    })
    Employee findById(Integer id);

    //查询所有用户
    List<Employee> findAll();

    //添加用户
    void addEmployee(Employee employee);

    //根据id删除用户
    void deleteById(Integer id);

    //根据姓名查询用户
    Employee findByName(String qname);

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

    @Select("select * from employee where qname = #{name}")
    Employee findByUsername(String name);
}
