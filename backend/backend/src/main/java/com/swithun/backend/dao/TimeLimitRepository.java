/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-25 19:29:29
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-25 20:54:29
 */
package com.swithun.backend.dao;

import com.swithun.backend.entity.TimeLimitEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeLimitRepository extends JpaRepository<TimeLimitEntity, Integer>{
    public List<TimeLimitEntity> findAll();
    public void delete(TimeLimitEntity timeLimitEntity);
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-25 19:29:29
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-25 19:29:29
 */
