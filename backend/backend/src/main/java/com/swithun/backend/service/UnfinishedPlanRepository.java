/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-13 16:45:44
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-13 16:46:52
 */
package com.swithun.backend.service;

import com.swithun.backend.entity.UnfinishedPlanEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnfinishedPlanRepository extends JpaRepository<UnfinishedPlanEntity, Integer>{
    
}