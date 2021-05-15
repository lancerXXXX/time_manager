/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-20 14:58:49
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-14 09:55:12
 */

package com.swithun.backend.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.swithun.backend.DTO.AddPlanTypeDTO;
import com.swithun.backend.entity.PlanEntity;
import com.swithun.backend.entity.PlanTypeEntity;
import com.swithun.backend.entity.UnfinishedPlanEntity;
import com.swithun.backend.service.PlanService;
import com.swithun.backend.utils.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
public class planController {

    @Autowired
    PlanService planS;

    Logger logger = LoggerFactory.getLogger(planController.class);

    /**
     * 添加
     */

    // 添加 日计划
    @PostMapping(value = "/plan/addDateTimePlan")
    public Integer addDayPlan(@RequestBody PlanEntity plan) {
        return planS.addPlan(plan, true);
    }

    // 添加 周，月计划
    @PostMapping(value = "/plan/addDatePlan")
    public Integer addWeekMonthPlan(@RequestBody PlanEntity plan) {
        return planS.addPlan(plan, false);
    }

    /**
     * 更新
     */

    // 更新 日计划
    @PostMapping(value = "/plan/updateDateTimePlan")
    public void updateDayPlan(@RequestBody PlanEntity plan) {
        planS.addPlan(plan, true);
    }

    // 更新 周，月计划
    @PostMapping(value = "/plan/updateDatePlan")
    public void updateDatePlan(@RequestBody PlanEntity plan) {
        planS.addPlan(plan, false);
    }

    // 更新 顺序限制 计划 位置
    @PostMapping(value = "/plan/updatePlanPos")
    public void updatePlanPos(@RequestBody Map<String, Object> mp) {
        planS.updatePlanPos(mp);
    }

    /**
     * 获取
     */

    // 获取 无限制计划

    @GetMapping(value = "/plan/getAllUnlimitedPlan")
    public Map<String, Object> getAllUnlimitedPlan() {
        return planS.getAllUnlimitedPlan();
    }

    // 获取 日计划
    @GetMapping(value = "/plan/getDateTimePlan")
    public Map<String, Object> getLimitedPlanByDate(@RequestParam String date) {
        String dateStr = DateUtil.TimeStamp2LocalDateStr(date);
        return planS.dealWithGetAllLimitedPlan(dateStr);
    }

    // 获取月计划
    // TODO
    @GetMapping(value = "/plan/getDatePlan")
    public List<PlanEntity> getDatePlan(@RequestParam String date) {
        return planS.getDatePlan(date);
    }

    // 删除 计划
    @PostMapping(value = "/plan/removeDateTimePlan")
    public Map<String, Set<String>> deletePlan(@RequestBody Map<String, Object> mp) {
        Integer id = (Integer) mp.get("id");
        String date = DateUtil.TimeStamp2LocalDateStr((String) mp.get("date"));
        logger.info(date);
        return planS.deleteDayPlan(id, date);
    }

    @PostMapping(value="/plan/removeDatePlan")
    public void deleteWeekMonthPlan(@RequestBody Map<String, Object> mp) {
        planS.deleteWeekMonthPlan(mp);
    }
    
}
