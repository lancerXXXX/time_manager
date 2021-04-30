package com.swithun.backend.dao;

import com.swithun.backend.entity.StTimeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StTimeRepository extends JpaRepository<StTimeEntity, Integer> {
    
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:22:20
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 15:22:20
 */
