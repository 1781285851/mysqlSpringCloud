package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.WorksDao;
import com.atguigu.springcloud.entities.Works;
import com.atguigu.springcloud.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class WorksServiceImpl implements WorksService {
    @Autowired
    private WorksDao worksDao;

    //根据日期查询
    @Override
    public List<Works> findByDateService(Date start, Date end,Integer itemId) {
        return worksDao.findByDateAndItemId(start, end,itemId);
    }

    //查询所有
    @Override
    public List<Works> findAllervice() {
        return worksDao.findAll();
    }

    //添加信息
    @Override
    public Boolean addWorkservice(Works works) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//获取系统时间
            works.setCreateDate(date);
            worksDao.addWorks(works);
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

    //从session获取当前登录的用户查询所有
    @Override
    public List<Works> findByEmpIdService(Integer empId) {
        return worksDao.findByEmpId(empId);
    }

    //从session获取当前登录的用户和条件查询
    @Override
    public List<Works> findByEmpIdAndDate(Integer empId, Date start, Date end,Integer itemId) {
        return worksDao.findByEmpIdAndDateAndItemId(empId, start, end, itemId);
    }

    @Override
    public List<Works> findByThisMonth(Integer itemId) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = sdf.parse(sdf.format(getmindate()));
        Date end = sdf.parse(sdf.format(getmaxdate()));
        return worksDao.findByDateAndItemId(start,end,itemId);
    }

    @Override
    public List<Works> findByThisMonthAndEmpId(Integer itemId,Integer empId) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = sdf.parse(sdf.format(getmindate()));
        Date end = sdf.parse(sdf.format(getmaxdate()));
        return worksDao.findByEmpIdAndDateAndItemId(empId,start,end,itemId);
    }

    /**
     * 根据id查询工作详情
     * @param id
     * @return
     */
    @Override
    public Works findById(Integer id) {
        return worksDao.findById(id);
    }

    /**
     * 获取本月第一天
     * @return
     */
    public  Date getmindate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();
    }

    /**
     * 获取本月最后一天
     * @return
     */
    public  Date getmaxdate(){
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        calendar2.set(Calendar.DAY_OF_MONTH, calendar2.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar2.getTime();
    }
}
