/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-13 16:45:44
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-14 10:37:26
 */
package com.swithun.backend.dao;

import com.swithun.backend.entity.PlanEntity;
import com.swithun.backend.entity.UnfinishedPlanEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UnfinishedPlanRepository extends JpaRepository<UnfinishedPlanEntity, Integer>, JpaSpecificationExecutor<UnfinishedPlanEntity>{
    public UnfinishedPlanEntity findOneById(Integer id);
    public void deleteById(Integer id);
    public UnfinishedPlanEntity findByplanByPlanId(PlanEntity plan);
}