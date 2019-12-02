package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Item;
import com.atguigu.springcloud.http.MessageCode;
import com.atguigu.springcloud.http.SoftworksResponse;
import com.atguigu.springcloud.service.ItemService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody SoftworksResponse<Boolean> save(@RequestBody Item item){
        log.info("保存新项目");
        Boolean result = itemService.addItemService(item);
        if(result)
            return SoftworksResponse.success(result);
        return SoftworksResponse.failure(MessageCode.COMMON_FAILURE);
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody SoftworksResponse<List<Item>> list() {
        log.info("获取项目列表");
        List<Item> allService = itemService.findAllService();
        if (0 != itemService.findAllService().size())
        return SoftworksResponse.success(allService);
        return SoftworksResponse.failure(MessageCode.COMMON_NO_DATA);
    }

    //根据code删除（修改状态）项目信息
    @DeleteMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody SoftworksResponse<Boolean> remove( @RequestParam(value = "code") String code){
        log.info("删除用户 = " + code);
        Boolean result = itemService.alertIsDeleteService(code);
        return SoftworksResponse.success(result);
    }


}
