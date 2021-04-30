package com.swithun.backend.dao;

import com.swithun.backend.entity.StatisticTemplateEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticTemplateRepository extends JpaRepository<StatisticTemplateEntity, Integer> {
    
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:19:10
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 15:19:11
 */
