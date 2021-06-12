/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-22 10:06:11
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-22 10:26:25
 */
package com.swithun.backend.dao;
import java.util.List;

import com.swithun.backend.entity.OrderLimitEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLimitRepository extends JpaRepository<OrderLimitEntity, Integer>{
    public List<OrderLimitEntity> findAll();
    public void delete(OrderLimitEntity orderLimitEntity);
}