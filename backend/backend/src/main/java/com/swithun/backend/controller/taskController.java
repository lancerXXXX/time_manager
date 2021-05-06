/*
 * @Descripttion: 任务模块Controller
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-08 10:14:30
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-06 09:31:08
 */
package com.swithun.backend.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import com.swithun.backend.DTO.addTaskDTO;
import com.swithun.backend.entity.FinishedTaskRecordEntity;
import com.swithun.backend.entity.PlanEntity;
import com.swithun.backend.entity.UnfinishedPlanEntity;
import com.swithun.backend.service.PlanService;
import com.swithun.backend.utils.ClassConvert;
import com.swithun.backend.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin
public class taskController {
    @Autowired 
    private PlanService planS;

    @Autowired
    private ClassConvert converter;

    /**
     * @description: 获取当天的task
     * @param {*}
     * @return {List<PlanEntity>}
     */
    @GetMapping(value = "/plan/gettaskbydate")
    public List<UnfinishedPlanEntity> tasktest(@RequestParam String date) {
        String dateStr = DateUtil.TimeStamp2LocalDateStr(date);
        return planS.gettaskbydate(dateStr);
    }
    /**
     * @description: 获取本周计划
     * @param {String date} date = "" 则默认取当日
     * @return {List<PlanEntity>}
     */
    @GetMapping(value = "/plan/getweekplan")
    public List<UnfinishedPlanEntity> getWeekPlan(@RequestParam String date) {
        return planS.getWeekPlan(date);
    }

    /**
     * @description: 获取所有未完成计划
     * @param {*}
     * @return {List<PlanEntity>}
     */
    @GetMapping(value = "/getallplan")
    public List<UnfinishedPlanEntity> getAllPlan() {
        return planS.getAllPlan();
    }

    @PostMapping(value="/finishplanbyonce")
    public String postMethodName(@RequestBody FinishedTaskRecordEntity finishedTaskRecordEntity) {
        // Timestamp 格式 2021-04-16 12:35:34.0
        planS.finishPlanByOnce(finishedTaskRecordEntity);
        return "添加成功";
    }
    
    @PostMapping(value="/task/add")
    public String addTask(@RequestBody addTaskDTO dto) {
        System.out.println(dto.getName());
        planS.addtask(dto);
        return "添加成功";
    }
    
}