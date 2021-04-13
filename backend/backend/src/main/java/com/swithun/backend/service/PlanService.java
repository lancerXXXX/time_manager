/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-12 16:42:46
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-13 16:49:59
 */
package com.swithun.backend.service;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

import com.swithun.backend.dao.PlanRepository;
import com.swithun.backend.entity.PlanEntity;
import com.swithun.backend.entity.UnfinishedPlanEntity;
import com.swithun.backend.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {
    @Autowired
    PlanRepository planRepository;
    @Autowired
    private DateUtil dateUtil;
    @Autowired
    private UnfinishedPlanRepository unfinishedPlanRepository;

    /**
     * @description: 获取本周计划
     * @param {String date} date = "" 则默认取当日
     * @return {List<PlanEntity>}
     */
    public List<PlanEntity> getWeekPlan(String date) {
        LocalDate now;
        if (date == "") {
            now = LocalDate.now();
        } else {
            now = LocalDate.parse(date);
        }
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
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
        List<PlanEntity> ans = planRepository
                .findByRepeatTypeAndExpectedStartDateBetweenOrRepeatTypeAndExpectedEndDateBetween(0, last_sunday,
                        next_monday, 0, last_sunday, next_monday);
        // 查找周重复计划
        ans.addAll(planRepository.findByRepeatType(1));
        // 查找月重复计划
        ans.addAll(planRepository.findByRepeatTypeAndExpectedStartDateBetweenOrRepeatTypeAndExpectedEndDateBetween(2,
                start_day_pre, end_day_next, 2, start_day_pre, end_day_next));
        // 查找年重复计划
        ans.addAll(planRepository.findByRepeatTypeAndExpectedStartDateBetweenOrRepeatTypeAndExpectedEndDateBetween(3,
                start_month_day_pre, end_month_day_next, 3, start_month_day_pre, end_month_day_next));
        return ans;
    }

    /**
     * @description: 获取当天的task
     * @param {*}
     * @return {List<PlanEntity>}
     */
    public List<PlanEntity> gettaskbydate(String date) {
        LocalDate localDate;
        if (date == "") {
            localDate = LocalDate.now();
        } else {
            localDate = LocalDate.parse(date);
        }

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
                dayOfWeek, dayOfWeek));
        // 获取 月重复计划 -- 几号开始 <= 今天几号 <= 几号结束
        ans.addAll(planRepository.findByRepeatTypeAndExpectedStartDateLessThanEqualAndExpectedEndDateGreaterThanEqual(2,
                d, d));
        // 获取 年重复计划 -- 几号开始 <= 今天几号 <= 几号结束
        ans.addAll(planRepository.findByRepeatTypeAndExpectedStartDateLessThanEqualAndExpectedEndDateGreaterThanEqual(3,
                md, md));
        return ans;
    }

    /**
     * @description: 获取所有未完成计划
     * @param {*}
     * @return {List<PlanEntity>}
     */
    public List<PlanEntity> getAllPlan() {
        return planRepository.findAll();
    }

    /**
     * @description: 添加一个任务(日期为当前)
     * @param {*}
     * @return {*}
     */
    public void addPlan(PlanEntity planEntity) {
        UnfinishedPlanEntity unfinishedPlanEntity = new UnfinishedPlanEntity();
        unfinishedPlanEntity.setPlanByPlanId(planEntity);
        unfinishedPlanRepository.save(unfinishedPlanEntity);
        // planRepository.save(planEntity);
    }

}
