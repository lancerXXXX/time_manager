package com.swithun.backend.service;

import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.swithun.backend.dao.OrderLimitRepository;
import com.swithun.backend.entity.OrderLimitEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LimitService {

    @Autowired
    private OrderLimitRepository orderLimitRepository;

    public void addOrderLimit(OrderLimitEntity orderLimitEntity) {
        orderLimitRepository.save(orderLimitEntity);
    }

    public List<OrderLimitEntity> getAllOrderLimit() {
        return orderLimitRepository.findAll();
    }

    public void deleteOrderLimitById(OrderLimitEntity orderLimitEntity) {
        orderLimitRepository.delete(orderLimitEntity);
    }

}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-22 10:09:44
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-22 10:09:45
 */
