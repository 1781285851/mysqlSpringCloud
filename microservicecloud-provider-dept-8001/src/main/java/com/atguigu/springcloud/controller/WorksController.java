package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Employee;
import com.atguigu.springcloud.entities.Works;
import com.atguigu.springcloud.http.MessageCode;
import com.atguigu.springcloud.http.SoftworksResponse;
import com.atguigu.springcloud.service.EmployeeService;
import com.atguigu.springcloud.service.WorksService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody
    SoftworksResponse<Boolean> save(@RequestBody Works works) {
        log.info("保存信息" + works.toString());
        if (works.getUsername() == null) {
            log.error("没有获取到用户名");
            return SoftworksResponse.failure("没有获取到用户名");
        }
        int emp_id = employeeService.findIdByName(works.getUsername());
        works.setEmpId(emp_id);
        Boolean result = worksService.addWorkservice(works);
        if (result)
            return SoftworksResponse.success(result);
        return SoftworksResponse.failure(MessageCode.COMMON_FAILURE);
    }

    /**
     * 删除
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody
    SoftworksResponse<Boolean> deleteworks(@RequestBody Works works) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        if (works.getCreateDate() != null) {
            Date date=sdf.parse(sdf.format(works.getCreateDate()));
            Date today=sdf.parse(sdf.format(new Date()));
            if(date.equals(today)){
                log.info("删除工作记录" + works.toString());
                Boolean result = worksService.deletework(works);
                if (result)
                    return SoftworksResponse.success(result);
            }
            }else{
            log.info("撤销失败日期不允许");
        }

        return SoftworksResponse.failure(MessageCode.COMMON_FAILURE);
    }


    /**
     * 根据日期分页查询(ADMIN)
     *
     * @param start
     * @param end
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/page_list_all", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody
    SoftworksResponse<PageInfo> detail(
            @RequestParam(value = "itemId", required = false) Integer itemId,
            @RequestParam(value = "start", required = false) String start,
            @RequestParam(value = "end", required = false) String end,
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) throws ParseException {
        log.info("初始日期 start = " + start);
        log.info("结束日期 end = " + end);
        try {
            if (null == pageNum) pageNum = 0;
            if (null == pageSize) pageSize = 10;
            // 1.引入PageHelper分页插件
            // 2.在查询之前只需要调用，传入页码，以及每页的大小
            PageHelper.startPage(pageNum, pageSize);
            // 3.startPage后面紧跟的这个查询就是一个分页查询
            List<Works> worksList;
            //如果没有传入日期，默认按本月查询
            if (null == start || null == end) {
                worksList = worksService.findByThisMonth(itemId);
            } else {
                Date startDate = new Date(start);
                Date endDate = new Date(end);
                worksList = worksService.findByDateService(startDate, endDate, itemId);
                log.info(worksList.toString());
            }
            // 4.使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
            // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
            PageInfo page = new PageInfo(worksList, 5);
            if (0 != worksList.size())
                return SoftworksResponse.success(page);
            return SoftworksResponse.failure(MessageCode.COMMON_NO_DATA);
        } catch (Exception e) {
            e.printStackTrace();
            return SoftworksResponse.failure(MessageCode.COMMON_PARAMETER_ERROR);
        }
    }

    /**
     * 根据用户名查询工作信息列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "page_list", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody
    SoftworksResponse<PageInfo> page(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "itemId", required = false) Integer itemId,
            @RequestParam(value = "start", required = false) String start,
            @RequestParam(value = "end", required = false) String end,
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            HttpServletRequest request) {
        log.info(itemId + " " + username + " " + pageNum + " " + pageSize);
        log.info("根据条件获取业务事项分页列表");
        try {
            if (null == pageNum) pageNum = 0;
            if (null == pageSize) pageSize = 10;
            // 1.引入PageHelper分页插件
            // 2.在查询之前只需要调用，传入页码，以及每页的大小
            log.error(pageNum + " " + pageSize);
            // 3.startPage后面紧跟的这个查询就是一个分页查询
            List<Works> worksListAll = null;
            if (null != username) {
                int idByName = employeeService.findIdByName(username);
                //分页下面要紧跟需要分页的语句
                PageHelper.startPage(pageNum, pageSize);
                if (null == start || null == end || "".equals(start) || "".equals(end)) {
                    worksListAll = worksService.findByThisMonthAndEmpId(itemId, idByName);
                } else {
                    Date startDate = new Date(start);
                    Date endDate = new Date(end);
                    worksListAll = worksService.findByEmpIdAndDate(idByName, startDate, endDate, itemId);
                }
            } else {
                return SoftworksResponse.failure(MessageCode.COMMON_PARAMETER_ERROR);
            }
            if (0 != worksListAll.size()) {
                // 4.使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
                // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
                PageInfo page = new PageInfo(worksListAll, 5);
                return SoftworksResponse.success(page);
            }
            return SoftworksResponse.failure(MessageCode.COMMON_NO_DATA);
        } catch (Exception e) {
            e.printStackTrace();
            return SoftworksResponse.failure(MessageCode.COMMON_PARAMETER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody
    SoftworksResponse<Works> detail(@RequestParam(value = "id") Integer id) {
        log.info("根据id获取工作详细信息 id = " + id);
        Works works = worksService.findById(id);
        if (null != works)
            return SoftworksResponse.success(works);
        return SoftworksResponse.failure(MessageCode.COMMON_USER_NOT_EXIST);
    }
}

