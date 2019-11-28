package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Employee;
import com.atguigu.springcloud.http.MessageCode;
import com.atguigu.springcloud.http.SoftworksResponse;
import com.atguigu.springcloud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //测试成功
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody SoftworksResponse<Boolean> save(@RequestBody Employee employee){
        log.info("保存新增用户1");
        Boolean result = employeeService.addEmployeeService(employee);
        if(result)
            return SoftworksResponse.success(result);
        return SoftworksResponse.failure(MessageCode.COMMON_FAILURE);
    }

    //测试成功---------------------》》》http://localhost:8001/employee/?code=string13
    @GetMapping(value="/code", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody SoftworksResponse<Employee> detail(@RequestParam(value = "code") String code){
        log.info("根据code获取用户表详细信息 code = " + code);
        Employee employee = employeeService.findByNameService(code);
        if (null != employee)
        return SoftworksResponse.success(employeeService.findByNameService(code));
        return SoftworksResponse.failure(MessageCode.COMMON_USER_NOT_EXIST);
    }

    //测试成功---------------------》》》http://localhost:8001/employee/page_list
    @GetMapping(value = "page_list", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody SoftworksResponse<PageInfo> page(
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        log.info("根据条件获取用户分页列表");
        if (null == pageNum) pageNum = 0;
        if (null == pageSize) pageSize = 10;
        // 1.引入PageHelper分页插件
        // 2.在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pageNum, pageSize);
        // 3.startPage后面紧跟的这个查询就是一个分页查询
        List<Employee> emps = employeeService.findAllService();
        // 4.使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(emps, 5);
        return SoftworksResponse.success(page);
    }

    @DeleteMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody SoftworksResponse<Boolean> remove( @RequestParam(value = "code") String code){
        log.info("删除用户 = " + code);
        Boolean result = employeeService.removeByNameService(code);
        return SoftworksResponse.success(result);
    }

    //测试成功---------------------》》》http://localhost:8001/employee/list
    @GetMapping(value = "list")
    private @ResponseBody SoftworksResponse<List<Employee>> page() {
        log.info("根据条件获取用户分页列表");
        List<Employee> emps = employeeService.findAllService();
        return SoftworksResponse.success(emps);
    }


    @GetMapping(value="/qname", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody SoftworksResponse<Employee> detailId(@RequestParam(value = "qname") String qname){
        log.info("根据姓名获取用户详细信息 name = " + qname);
        Employee employee = employeeService.findByIdService(qname);
        if (null != employee)
            return SoftworksResponse.success(employeeService.findByIdService(qname));
        return SoftworksResponse.failure(MessageCode.COMMON_USER_NOT_EXIST);
    }


}
