package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.EmployeeDao;
import com.atguigu.springcloud.entities.Employee;
import com.atguigu.springcloud.service.EmployeeService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
@Log4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private PasswordEncoder passwordEncoder;


    //根据id查询用户基本信息
    public Employee findById(Integer id){
        return employeeDao.findById(id);
    }

    //查询所有用户
    public List<Employee> findAllService(){
        return employeeDao.findAll();
    }

    //添加用户
    public Boolean addEmployeeService(Employee employee){
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//获取系统时间
            employee.setCreateDate(date) ;
            if (employeeDao.findByUsername(employee.getQname())!=null){
                log.warn("用户名已经存在！");
                return false;
            }
            String password = passwordEncoder.encode(employee.getPassword());
            employee.setPassword(password);
            employeeDao.addEmployee(employee);
            if(null !=employee.getRoleIds())
                saveRoles(employee.getId(),employee.getRoleIds());
            return true;
        } catch (Exception e) {
           log.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //根据id删除用户
    public Boolean deleteById(Integer id){
        try {
            employeeDao.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //根据name查询id
    @Override
    public Employee findByIdService(String qname) {
        return employeeDao.findByName(qname);
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
