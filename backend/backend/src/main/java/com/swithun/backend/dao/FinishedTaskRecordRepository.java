/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-16 10:45:42
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-20 14:46:33
 */
package com.swithun.backend.dao;

import com.swithun.backend.entity.FinishedTaskRecordEntity;
import com.swithun.backend.entity.UnfinishedPlanEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinishedTaskRecordRepository extends JpaRepository<UnfinishedPlanEntity, Integer> {
    public void save(FinishedTaskRecordEntity finishedTaskRecordEntity);
}
