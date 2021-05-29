/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-20 14:32:30
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-21 16:09:51
 */
package com.swithun.backend.dao;

import java.util.List;

import com.swithun.backend.entity.PlanTypeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanTypeRepository extends JpaRepository<PlanTypeEntity, Integer>{
    public List<PlanTypeEntity> findAll();
    public List<PlanTypeEntity> findAllByPlanTypeByParentId(PlanTypeEntity planTypeEntity);
    public void deleteById(Integer id);
    public PlanTypeEntity findOneByName(String name);
}
