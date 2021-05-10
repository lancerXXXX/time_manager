/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-06 10:31:17
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-06 15:47:26
 */
package com.swithun.backend.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.swithun.backend.entity.PlanEntity;
import com.swithun.backend.entity.RelationEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends JpaRepository<RelationEntity, Integer> {
    @Transactional
    public void deleteAllByPlanByPlanIdAndPlanByPrePlanId(PlanEntity plan, PlanEntity prePlan);
    public RelationEntity findAllByPlanByPlanIdAndPlanByPrePlanId(PlanEntity plan, PlanEntity prePlan);
    public List<RelationEntity> findAllByPlanByPlanIdOrPlanByPrePlanId(PlanEntity plan, PlanEntity plan2);
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-06 10:31:17
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-06 10:31:18
 */
