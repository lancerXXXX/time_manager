/*
 * @Descripttion: 任务模块Controller
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-08 10:14:30
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-12 17:11:14
 */
package com.swithun.backend.controller;

import java.util.List;

import com.swithun.backend.entity.PlanEntity;
import com.swithun.backend.service.PlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class taskController {
    @Autowired 
    PlanService planService;

    /**
     * @description: 获取当天的task
     * @param {*}
     * @return {List<PlanEntity>}
     */
    @GetMapping(value = "/gettaskbydate")
    public List<PlanEntity> tasktest(@RequestParam String date) {
        return planService.gettaskbydate(date);
    }
    /**
     * @description: 获取本周计划
     * @param {String date} date = "" 则默认取当日
     * @return {List<PlanEntity>}
     */
    @GetMapping(value = "/getweekplan")
    public List<PlanEntity> getWeekPlan(@RequestParam String date) {
        return planService.getWeekPlan(date);
    }

    /**
     * @description: 添加一个任务(日期为当前)
     * @param {*}
     * @return {*}
     */
    @PostMapping(value = "/addtask")
    public String addPlan(@RequestBody PlanEntity planEntity) {
        planService.addPlan(planEntity);
        return "添加成功";
    }

    /**
     * @description: 获取所有未完成计划
     * @param {*}
     * @return {List<PlanEntity>}
     */
    @GetMapping(value = "/getallplan")
    public List<PlanEntity> getAllPlan() {
        return planService.getAllPlan();
    }

}