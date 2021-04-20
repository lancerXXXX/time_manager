/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-20 14:32:30
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-20 16:24:56
 */
package com.swithun.backend.dao;

import java.util.List;

import com.swithun.backend.entity.PlanTypeEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanTypeRepository extends JpaRepository<PlanTypeEntity, Integer>{
    public List<PlanTypeEntity> findAll();
    public List<PlanTypeEntity> findAllByPlanTypeByParentId(PlanTypeEntity planTypeEntity);
}
