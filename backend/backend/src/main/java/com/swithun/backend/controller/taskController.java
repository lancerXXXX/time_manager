/*
 * @Descripttion: 任务模块Controller
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-08 10:14:30
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-12 16:31:06
 */
package com.swithun.backend.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

import com.swithun.backend.dao.PlanRepository;
import com.swithun.backend.entity.PlanEntity;
import com.swithun.backend.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class taskController {
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private DateUtil dateUtil;

    /**
     * @description: 获取当天的task
     * @param {*}
     * @return {List<PlanEntity>}
     */
    @GetMapping(value = "/gettask")
    public List<PlanEntity> tasktest() {
        LocalDate localDate = LocalDate.now();

        // For 非重复计划 2020-01-01
        String ymd = dateUtil.todayYMD();
        System.out.println("today: " + ymd);
        // For 周重复计划 -- Monday 1
        String dayOfWeek = dateUtil.todayOfWeek();
        System.out.println("today is: " + dayOfWeek);
        // For 月重复计划 -- 01
        String d = dateUtil.todayD();
        // For 年重复计划 -- 01-01
        String md = dateUtil.todayMD();

        List<PlanEntity> ans;
        // 获取 非重复计划 -- 开始日期 <= 今日日期 < 结束日期
        ans = planRepository.findByRepeatTypeAndExpectedStartDateLessThanEqualAndExpectedEndDateGreaterThanEqual(0,
                localDate.toString(), localDate.toString());
        // 获取 周重复计划 -- 周几开始 <= 今天周几 <= 周几结束
        ans.addAll(planRepository.findByRepeatTypeAndExpectedStartDateLessThanEqualAndExpectedEndDateGreaterThanEqual(1,
               dayOfWeek , dayOfWeek));
        // 获取 月重复计划 -- 几号开始 <= 今天几号 <= 几号结束
        ans.addAll(planRepository.findByRepeatTypeAndExpectedStartDateLessThanEqualAndExpectedEndDateGreaterThanEqual(2,
                d, d));
        // 获取 年重复计划 -- 几号开始 <= 今天几号 <= 几号结束
        ans.addAll(planRepository.findByRepeatTypeAndExpectedStartDateLessThanEqualAndExpectedEndDateGreaterThanEqual(3,
                md, md));
        return ans;
    }
    /**
     * @description: 获取本周计划
     * @param {*}
     * @return {*}
     */
    @GetMapping(value = "/getweekplan")
    public List<PlanEntity> getWeekPlan() {
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.US).dayOfWeek();
        // For 非重复计划
        String last_sunday = now.with(fieldISO, 1).plusDays(-1).toString();
        String next_monday = now.with(fieldISO, 7).plusDays(1).toString();
        // For 月重复计划
        String start_day_pre = last_sunday.substring(8);
        String end_day_next = next_monday.substring(8);
        // For 年重复计划
        String start_month_day_pre = last_sunday.substring(5);
        String end_month_day_next = next_monday.substring(5);

        // 查找非重复计划
        List<PlanEntity> ans = planRepository.findByRepeatTypeAndExpectedStartDateBetweenOrRepeatTypeAndExpectedEndDateBetween(0, last_sunday, next_monday, 0, last_sunday, next_monday);
        // 查找周重复计划
        ans.addAll(planRepository.findByRepeatType(1));
        // 查找月重复计划
        ans.addAll(planRepository.findByRepeatTypeAndExpectedStartDateBetweenOrRepeatTypeAndExpectedEndDateBetween(2, start_day_pre, end_day_next, 2, start_day_pre, end_day_next));
        // 查找年重复计划
        ans.addAll(planRepository.findByRepeatTypeAndExpectedStartDateBetweenOrRepeatTypeAndExpectedEndDateBetween(3, start_month_day_pre, end_month_day_next, 3, start_month_day_pre, end_month_day_next));
        return ans;
    }

    /**
     * @description: 添加一个任务(日期为当前)
     * @param {*}
     * @return {*}
     */
    @PostMapping(value = "/addtask")
    public String postMethodName(@RequestBody PlanEntity planEntity) {
        planRepository.save(planEntity);
        return "添加成功";
    }

    /**
     * @description: 获取所有未完成计划
     * @param {*}
     * @return {List<PlanEntity>}
     */
    @GetMapping(value = "/getallplan")
    public List<PlanEntity> getAllPlan() {
        return planRepository.findAll();
    }

}