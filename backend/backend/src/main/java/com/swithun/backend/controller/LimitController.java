/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-22 10:11:35
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-28 20:51:33
 */
package com.swithun.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import com.swithun.backend.DTO.getFrequencyLimitDTO;
import com.swithun.backend.DTO.getTimeLimitDTO;
import com.swithun.backend.DTO.getOrderLimitDTO;
import com.swithun.backend.entity.FrequencyLimitEntity;
import com.swithun.backend.entity.OrderLimitEntity;
import com.swithun.backend.entity.PlanTypeEntity;
import com.swithun.backend.entity.TimeLimitEntity;
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

    /**
     * 顺序限制
     */

    @PostMapping(value="/rule/order/add")
    public Integer addOrderLimit(@RequestBody Map<String, Object> mp) {
        OrderLimitEntity orderLimitEntity = new OrderLimitEntity();
        orderLimitEntity.setOrderType((Integer)mp.get("order"));
        var bannedTaskType = (List<Integer>)mp.get("bannedTaskType");
        var basedTaskType = (List<Integer>)mp.get("basedTaskType");
        orderLimitEntity.setPlanTypeByBannedPlanTypeId(new PlanTypeEntity( bannedTaskType.get(bannedTaskType.size() - 1)));
        orderLimitEntity.setPlanTypeByBaseedPlanTypeId(new PlanTypeEntity(basedTaskType.get(basedTaskType.size() - 1)));
        return limitService.addOrderLimit(orderLimitEntity);
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
    public String updateOrderLimitBydId(@RequestBody Map<String, Object> mp) {
        var bannedTaskType = (List<Integer>)mp.get("bannedTaskType");
        var basedTaskType = (List<Integer>)mp.get("basedTaskType");
        System.out.println((Integer)mp.get("id"));
        OrderLimitEntity ol = new OrderLimitEntity();
        ol.setId((Integer)mp.get("id"));
        ol.setPlanTypeByBannedPlanTypeId(new PlanTypeEntity(bannedTaskType.get(bannedTaskType.size() - 1)));
        ol.setPlanTypeByBaseedPlanTypeId(new PlanTypeEntity(basedTaskType.get(basedTaskType.size() - 1)));
        ol.setOrderType((Integer)mp.get("order"));
        limitService.addOrderLimit(ol);
        return "更新成功";
    }
    
    /**
     * 时间限制
     */

    @PostMapping(value="/rule/time/add")
    public Integer addTimeLimit(@RequestBody Map<String, Object> mp) {
        var taskTypeList = (List<Integer>)mp.get("taskType");
        return limitService.addTimeLimit(new TimeLimitEntity((Integer)mp.get("id"), (String)mp.get("startTime"), (String)mp.get("endTime"), new PlanTypeEntity(taskTypeList.get(taskTypeList.size() - 1))));
    }

    @GetMapping(value="/rule/time/getAllRule")
    public List<getTimeLimitDTO> postMethodName() {
        return limitService.getAllTimeLimit();
    }
    
    @PostMapping(value="/rule/time/remove")
    public String deleteOrderlimitById(@RequestBody TimeLimitEntity timeLimitEntity) {
        limitService.deleteTimeLimitById(timeLimitEntity);
        return "删除成功";
    }
    
    @PostMapping(value="/rule/time/update")
    public String updateTimeLimitById(@RequestBody Map<String, Object> mp) {
        var taskTypeList = (List<Integer>)mp.get("taskType");
        limitService.addTimeLimit(new TimeLimitEntity((Integer)mp.get("id"), (String)mp.get("startTime"), (String)mp.get("endTime"), new PlanTypeEntity(taskTypeList.get(taskTypeList.size() - 1))));
        return "更新成功";
    }
    
    /**
     * 次数限制
     */

    @PostMapping(value="/rule/frequency/add")
    public Integer addFrequencyLimit(@RequestBody Map<String, Object> mp) {
        System.out.println(mp.get("min") + " " + mp.get("max") + " " +mp.get("time"));
        var taskTypeList = (List<Integer>)mp.get("taskType");
        return limitService.addFrequencyLimit(new FrequencyLimitEntity((Integer)mp.get("min"), (Integer)mp.get("max"), (Integer)mp.get("time"), new PlanTypeEntity(taskTypeList.get(taskTypeList.size() - 1))));
    }

    @GetMapping(value="/rule/frequency/getAllRule")
    public List<getFrequencyLimitDTO> getAllFrequncyLimit() {
        return limitService.getAllFrequencyLimit();
    }
    
    @PostMapping(value="/rule/frequency/remove")
    public String deleteFrequencylimitById(@RequestBody FrequencyLimitEntity frequencyLimitEntity) {
        limitService.deleteFrequencyLimitById(frequencyLimitEntity);
        return "删除成功";
    }
    
    @PostMapping(value="/rule/frequency/update")
    public String updateFrequencyLimitById(@RequestBody Map<String, Object> mp) {
        var taskTypeList = (List<Integer>)mp.get("taskType");
        limitService.addFrequencyLimit(new FrequencyLimitEntity((Integer)mp.get("id"), (Integer)mp.get("min"),(Integer)mp.get("max"),(Integer)mp.get("time"), new PlanTypeEntity(taskTypeList.get(taskTypeList.size() - 1))));
        return "更新成功";
    }
}