/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-22 10:09:44
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-26 13:40:01
 */
package com.swithun.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.swithun.backend.DTO.getFrequencyLimitDTO;
import com.swithun.backend.DTO.getOrderLimitDTO;
import com.swithun.backend.DTO.getTimeLimitDTO;
import com.swithun.backend.dao.FrequencyLimitRepository;
import com.swithun.backend.dao.OrderLimitRepository;
import com.swithun.backend.dao.TimeLimitRepository;
import com.swithun.backend.entity.FrequencyLimitEntity;
import com.swithun.backend.entity.OrderLimitEntity;
import com.swithun.backend.entity.PlanTypeEntity;
import com.swithun.backend.entity.TimeLimitEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LimitService {

    @Autowired
    private OrderLimitRepository orderLimitRepository;

    @Autowired
    private TimeLimitRepository timeLimitRepository;

    @Autowired
    private FrequencyLimitRepository frequencyLimitRepository;

    /**
     * utility
     */

    public List<Integer> getAllParents(PlanTypeEntity planTypeEntity) {
        List<Integer> parentsList = new ArrayList<Integer>();
        while (planTypeEntity != null && planTypeEntity.getId() != null) {
            parentsList.add(0, planTypeEntity.getId());
            planTypeEntity = planTypeEntity.getPlanTypeByParentId();
        }
        return parentsList;
    }

    /**
     * 顺序限制
     */

    public Integer addOrderLimit(OrderLimitEntity orderLimitEntity) {
        return orderLimitRepository.save(orderLimitEntity).getId();
    }


    public List<getOrderLimitDTO> getAllOrderLimit() {
        List<OrderLimitEntity> ol = orderLimitRepository.findAll();
        List<getOrderLimitDTO> getOrderLimitDTOs = new ArrayList<getOrderLimitDTO>();
        for (OrderLimitEntity orderLimitEntity : ol) {
            PlanTypeEntity banned = orderLimitEntity.getPlanTypeByBannedPlanTypeId();
            PlanTypeEntity based = orderLimitEntity.getPlanTypeByBaseedPlanTypeId();
            List<Integer> bannedList = getAllParents(banned);
            List<Integer> basedList = getAllParents(based);
            getOrderLimitDTOs.add(new getOrderLimitDTO(orderLimitEntity.getId(), orderLimitEntity.getOrderType(),
                    bannedList, basedList));
        }
        return getOrderLimitDTOs;
    }

    public void deleteOrderLimitById(OrderLimitEntity orderLimitEntity) {
        orderLimitRepository.delete(orderLimitEntity);
    }

    /**
     * 时间顺序
     */

    public Integer addTimeLimit(TimeLimitEntity timeLimitEntity) {
        return timeLimitRepository.save(timeLimitEntity).getId();
    }

    public List<getTimeLimitDTO> getAllTimeLimit() {
        List<TimeLimitEntity> timeLimitEntities = timeLimitRepository.findAll();
        List<getTimeLimitDTO> timeLimitDTOs = new ArrayList<>();
        for (TimeLimitEntity timeLimitEntity : timeLimitEntities) {
            timeLimitDTOs.add(new getTimeLimitDTO(timeLimitEntity.getId(), timeLimitEntity.getStartTime(),
                    timeLimitEntity.getEndTime(), getAllParents(timeLimitEntity.getPlanTypeByPlanTypeId())));
        }
        return timeLimitDTOs;
    }

    public void deleteTimeLimitById(TimeLimitEntity timeLimitEntity) {
        timeLimitRepository.delete(timeLimitEntity);
    }

    /**
     * 次数限制
     */

     public Integer addFrequencyLimit(FrequencyLimitEntity frequencyLimitEntity) {
         return frequencyLimitRepository.save(frequencyLimitEntity).getId();
     }

     public List<getFrequencyLimitDTO> getAllFrequencyLimit() {
        List<FrequencyLimitEntity> frequencyLimitEntities = frequencyLimitRepository.findAll();
        List<getFrequencyLimitDTO> frequencyLimitDTOs = new ArrayList<>();
        for (FrequencyLimitEntity it : frequencyLimitEntities) {
            frequencyLimitDTOs.add(new getFrequencyLimitDTO(it.getId(), getAllParents(it.getPlanTypeByPlanTypeId()), it.getMin(), it.getMax(), it.getTime()));
        }
        return frequencyLimitDTOs;
     }

     public void deleteFrequencyLimitById(FrequencyLimitEntity frequencyLimitEntity) {
         frequencyLimitRepository.delete(frequencyLimitEntity);
     }

}