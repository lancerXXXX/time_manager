/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-07 10:25:04
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-08 15:09:15
 */
package com.swithun.backend.dao;

import java.sql.Date;
import java.util.List;

import com.swithun.backend.entity.PlanEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanitemRepository extends JpaRepository<PlanEntity, Integer>{
    PlanEntity findById(int id);
    List<PlanEntity> findByExpectedStartDate(Date date);
}
