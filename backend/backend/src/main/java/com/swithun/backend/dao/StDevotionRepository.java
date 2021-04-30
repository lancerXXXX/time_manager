package com.swithun.backend.dao;

import com.swithun.backend.entity.StDevotionEntity;
import com.swithun.backend.entity.StatisticTemplateEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StDevotionRepository extends JpaRepository<StDevotionEntity, Integer> {

	void deleteByStatisticTemplateByStId(StatisticTemplateEntity st);
    
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:20:29
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 15:20:30
 */
