/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-22 10:11:35
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-23 15:50:04
 */
package com.swithun.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.swithun.backend.DTO.AddOrderLimitDTO;
import com.swithun.backend.DTO.getOrderLimitDTO;
import com.swithun.backend.entity.OrderLimitEntity;
import com.swithun.backend.entity.PlanTypeEntity;
import com.swithun.backend.service.LimitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin
public class LimitController {

    @Autowired
    private LimitService limitService;

    @PostMapping(value="/rule/order/add")
    public String addOrderLimit(@RequestBody AddOrderLimitDTO addOrderLimit) {
        OrderLimitEntity orderLimitEntity = new OrderLimitEntity();
        orderLimitEntity.setOrderType(addOrderLimit.getOrder());
        orderLimitEntity.setPlanTypeByBannedPlanTypeId(new PlanTypeEntity(addOrderLimit.getBannedTaskType()));
        orderLimitEntity.setPlanTypeByBaseedPlanTypeId(new PlanTypeEntity(addOrderLimit.getBasedTaskType()));
        limitService.addOrderLimit(orderLimitEntity);
        return "添加成功";
    }

    @GetMapping(value="/rule/order/getAllRule")
    public List<getOrderLimitDTO> getMethodName() {
        return limitService.getAllOrderLimit();
    }

    @PostMapping(value="/rule/order/remove")
    public String deleteOrderLimitById(@RequestBody OrderLimitEntity orderLimitEntity) {
        limitService.deleteOrderLimitById(orderLimitEntity);
        return "删除成功";
    }
    
    @PostMapping(value="/rule/order/update")
    public String updateOrderLimitBydId(@RequestBody OrderLimitEntity orderLimitEntity) {
        limitService.addOrderLimit(orderLimitEntity);
        return "更新成功";
    }
    
    
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-22 10:11:35
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-22 10:11:36
 */
