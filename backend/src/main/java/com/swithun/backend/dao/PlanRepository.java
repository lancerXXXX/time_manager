/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-07 10:25:04
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-08 09:33:21
 */
package com.swithun.backend.dao;

import java.util.List;

import com.swithun.backend.entity.PlanEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<PlanEntity, Integer>{
    PlanEntity findById(int id);
    List<PlanEntity> findByExpectedStartDate(String date);

    List<PlanEntity> findByRepeatTypeAndExpectedStartDate(Integer repeatType, String date);
    /**
     * @description: 获取 时间范围 包含今日 的所有 Plan
     * @param {Integer} repeatType
     * @param {String} dateBegin
     * @param {String} dateEnd
     * @return {*}
     */
    List<PlanEntity> findByRepeatTypeAndExpectedStartDateLessThanEqualAndExpectedEndDateGreaterThanEqual(Integer repeatType, String dateBegin, String dateEnd);
    /**
     * @description: 获取所有未完成
     * @param {*}
     * @return {*}
     */
    List<PlanEntity> findAll();
    /**
     * @description: 获取 在一个时间范围内的所有 Plan
     * @param {Integer} repeatType
     * @param {String} startDateBegin
     * @param {String} startDateEnd
     * @param {Integer} repeatType_copy
     * @param {String} endDateBegin
     * @param {String} endDateEnd
     * @return {*}
     */
    List<PlanEntity> findByRepeatTypeAndExpectedStartDateBetweenOrRepeatTypeAndExpectedEndDateBetween(Integer repeatType, String startDateBegin, String startDateEnd, Integer repeatType_copy, String endDateBegin, String endDateEnd);
    /**
     * @description: 获取 某种 重复类型的所有 Plan
     * @param {Integer} repeatType
     * @return {*}
     */
    List<PlanEntity> findByRepeatType(Integer repeatType);
    PlanEntity findOneById(Integer integer);
}
