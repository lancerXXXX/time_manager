/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-20 15:35:36
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-20 15:43:57
 */
package com.swithun.backend.DTO;

import com.swithun.backend.entity.PlanTypeEntity;

import org.springframework.stereotype.Component;

@Component
public class AddPlanTypeDTO {
    private PlanTypeEntity planTypeEntity;
    private Integer parent_id;


    public PlanTypeEntity getPlanTypeEntity() {
        return planTypeEntity;
    }
    public void setPlanTypeEntity(PlanTypeEntity planTypeEntity) {
        this.planTypeEntity = planTypeEntity;
    }
    public Integer getParent_id() {
        return parent_id;
    }
    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }
}
