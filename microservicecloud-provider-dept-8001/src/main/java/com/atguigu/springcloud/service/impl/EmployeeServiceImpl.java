package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.EmployeeDao;
import com.atguigu.springcloud.entities.Employee;
import com.atguigu.springcloud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    //根据code查询用户基本信息
    public Employee findByNameService(String code){
        return employeeDao.findByName(code);
    }

    //查询所有用户
    public List<Employee> findAllService(){
        return employeeDao.findAll();
    }

    //添加用户
    public Boolean addEmployeeService(Employee employee){
        try {
            employee.setCreateDate(new Date());
            employeeDao.addEmployee(employee);
            if(null !=employee.getRoleIds())
                saveRoles(employee.getId(),employee.getRoleIds());
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

    //根据code删除用户
    public Boolean removeByNameService(String code){
        try {
            employeeDao.alertStatus(code);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //根据name查询id
    @Override
    public Employee findByIdService(String qname) {
        return employeeDao.findById(qname);
    }

    @Override
    public int findIdByName(String name) {
        return employeeDao.findIdByName(name);
    }

    private void saveRoles(Integer empId,Integer[] roleIds){
        //先删除关联表中该员工的所有角色
        employeeDao.cleanReferRoles(empId);
        for(int i=0;i<roleIds.length;i++)
            employeeDao.refer(empId,roleIds[i]);
    }
}
