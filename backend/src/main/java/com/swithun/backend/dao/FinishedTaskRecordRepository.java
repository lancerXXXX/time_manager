/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-16 10:45:42
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-10 09:02:36
 */
package com.swithun.backend.dao;

import com.swithun.backend.entity.FinishedTaskRecordEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FinishedTaskRecordRepository extends JpaRepository<FinishedTaskRecordEntity, Integer>, JpaSpecificationExecutor<FinishedTaskRecordEntity> {
}
