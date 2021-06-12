/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-26 11:17:13
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-26 13:27:53
 */
package com.swithun.backend.dao;

import java.util.List;

import com.swithun.backend.entity.FrequencyLimitEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FrequencyLimitRepository extends JpaRepository<FrequencyLimitEntity, Integer> {
    public List<FrequencyLimitEntity> findAll();
    public void delete(FrequencyLimitEntity frequencyLimitEntity);
}
