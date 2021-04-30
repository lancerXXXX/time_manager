package com.swithun.backend.dao;

import com.swithun.backend.entity.StNameEntity;
import com.swithun.backend.entity.StatisticTemplateEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StNameRepository extends JpaRepository<StNameEntity, Integer> {

	void deleteByStatisticTemplateByStId(StatisticTemplateEntity st);
    
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:21:07
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 15:21:08
 */
