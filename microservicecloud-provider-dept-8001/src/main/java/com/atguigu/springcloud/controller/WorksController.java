package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Employee;
import com.atguigu.springcloud.entities.Works;
import com.atguigu.springcloud.http.MessageCode;
import com.atguigu.springcloud.http.SoftworksResponse;
import com.atguigu.springcloud.service.EmployeeService;
import com.atguigu.springcloud.service.WorksService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Log4j
@RequestMapping("/works")
public class WorksController {

    @Autowired
    private WorksService worksService;
    @Autowired
    private EmployeeService employeeService;

    /**
     * 新增
     * @param works
     * @return
     */
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody SoftworksResponse<Boolean> save(@RequestBody Works works, HttpServletRequest request){
        log.info("保存信息");
        String username = (String) request.getSession().getAttribute("username");
        if(null != username){
            int emp_id = employeeService.findIdByName(username);
            works.setEmpId(emp_id);
            Boolean result = worksService.addWorkservice(works);
            if (result)
                return SoftworksResponse.success(result);
        }
        return SoftworksResponse.failure(MessageCode.COMMON_FAILURE);
    }

    /**
     * 根据日期分页查询
     * @param start
     * @param end
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value="/findByCreateDate", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody SoftworksResponse<PageInfo> detail(@RequestParam(value = "start", required = false) String start,
                                                             @RequestParam(value = "end", required = false) String end,
                                                             @RequestParam(value = "pageNum", required = false) Integer pageNum,
                                                             @RequestParam(value = "pageSize", required = false) Integer pageSize){
        log.info("初始日期 start = " + start);
        log.info("结束日期 end = " + end);
        if (null == pageNum) pageNum = 0;
        if (null == pageSize) pageSize = 10;
        // 1.引入PageHelper分页插件
        // 2.在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pageNum, pageSize);
        // 3.startPage后面紧跟的这个查询就是一个分页查询
        List<Works> worksList;
        if(null == start || null == end){
            worksList = worksService.findAllervice();
        }else{
             worksList = worksService.findByDateService(start, end);
        }
        // 4.使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(worksList, 5);
        if (0 != worksList.size())
            return SoftworksResponse.success(page);
        return SoftworksResponse.failure(MessageCode.COMMON_NO_DATA);
    }

    /**
     * 查询所有
     * @param pageNum
     * @param pageSize
     * @return
     */
/*    @GetMapping(value = "page_list", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody SoftworksResponse<PageInfo> page(
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        log.info("根据条件获取业务事项分页列表");
        if (null == pageNum) pageNum = 0;
        if (null == pageSize) pageSize = 10;
        // 1.引入PageHelper分页插件
        // 2.在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pageNum, pageSize);
        // 3.startPage后面紧跟的这个查询就是一个分页查询
        List<Works> worksListAll = worksService.findAllervice();
        // 4.使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(worksListAll, 5);
        if (0 != worksListAll.size())
            return SoftworksResponse.success(page);
        return SoftworksResponse.failure(MessageCode.COMMON_NO_DATA);
    }*/


}
