package com.swithun.backend.dao;

import com.swithun.backend.entity.StTypeEntity;
import com.swithun.backend.entity.StatisticTemplateEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StTypeRepository extends JpaRepository<StTypeEntity, Integer> {

	void deleteByStatisticTemplateByStId(StatisticTemplateEntity st);
    
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:23:06
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 15:23:07
 */
