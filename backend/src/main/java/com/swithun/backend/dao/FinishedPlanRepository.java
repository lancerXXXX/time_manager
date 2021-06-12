/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-28 16:35:47
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-28 16:36:39
 */
package com.swithun.backend.dao;

import com.swithun.backend.entity.FinishedPlanEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinishedPlanRepository extends JpaRepository<FinishedPlanEntity, Integer> {
    
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-28 16:35:47
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-28 16:35:48
 */
