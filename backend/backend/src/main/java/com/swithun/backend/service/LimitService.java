/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-22 10:09:44
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-23 13:58:55
 */
package com.swithun.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.swithun.backend.DTO.getOrderLimitDTO;
import com.swithun.backend.dao.OrderLimitRepository;
import com.swithun.backend.entity.OrderLimitEntity;
import com.swithun.backend.entity.PlanTypeEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LimitService {

    @Autowired
    private OrderLimitRepository orderLimitRepository;

    public void addOrderLimit(OrderLimitEntity orderLimitEntity) {
        orderLimitRepository.save(orderLimitEntity);
    }

    public List<getOrderLimitDTO> getAllOrderLimit() {
        List<OrderLimitEntity> ol = orderLimitRepository.findAll();
        List<getOrderLimitDTO> getOrderLimitDTOs = new ArrayList<getOrderLimitDTO>();
        for (OrderLimitEntity orderLimitEntity : ol) {
            PlanTypeEntity banned = orderLimitEntity.getPlanTypeByBannedPlanTypeId();
            PlanTypeEntity based = orderLimitEntity.getPlanTypeByBaseedPlanTypeId();
            List<Integer> bannedList = new ArrayList<Integer>();
            List<Integer> basedList = new ArrayList<Integer>();
            while (banned != null && banned.getId() != null) {
                bannedList.add(0, banned.getId());
                banned = banned.getPlanTypeByParentId();
            }
            while (based != null && based.getId() != null) {
                basedList.add(0, based.getId());
                based = based.getPlanTypeByParentId();
            }
            getOrderLimitDTOs.add(new getOrderLimitDTO(orderLimitEntity.getId(), orderLimitEntity.getOrderType(),
                    bannedList, basedList));
        }
        return getOrderLimitDTOs;
    }

    public void deleteOrderLimitById(OrderLimitEntity orderLimitEntity) {
        orderLimitRepository.delete(orderLimitEntity);
    }

}