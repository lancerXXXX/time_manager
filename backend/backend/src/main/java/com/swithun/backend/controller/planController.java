/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-20 14:58:49
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-06 09:51:05
 */

package com.swithun.backend.controller;

import java.util.List;

import com.swithun.backend.DTO.AddPlanTypeDTO;
import com.swithun.backend.entity.PlanEntity;
import com.swithun.backend.entity.PlanTypeEntity;
import com.swithun.backend.service.PlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class planController {

    @Autowired
    PlanService planS;

    @PostMapping(value = "/plantype/addplantype")
    public Integer addPlanType(@RequestBody AddPlanTypeDTO addPlanTypeDTO) {
        PlanTypeEntity planTypeEntity = addPlanTypeDTO.getPlanTypeEntity();
        Integer parent_id = addPlanTypeDTO.getParent_id();
        PlanTypeEntity planTypeByParentId = new PlanTypeEntity();
        if (parent_id != -1) {
            planTypeByParentId.setId(addPlanTypeDTO.getParent_id());
            planTypeEntity.setPlanTypeByParentId(planTypeByParentId);
        }
        planS.addPlanType(addPlanTypeDTO.getPlanTypeEntity());
        return planS.getPlanTypeEntityByName(addPlanTypeDTO.getPlanTypeEntity().getName()).getId();
    }

    @GetMapping(value = "/plantype/getallplantype")
    public List<PlanTypeEntity> getAllPlanType() {
        return planS.getAllPlanType();
    }

    @PostMapping(value = "/plantype/updateplantypename")
    public String updatePlanType(@RequestBody PlanTypeEntity planTypeEntity) {
        planS.updatePlanTypeName(planTypeEntity);
        return "修改成功";
    }

    @PostMapping(value = "/plantype/deleteplantype")
    public String deletePlanType(@RequestBody PlanTypeEntity planTypeEntity) {
        planS.deletePlanType(planTypeEntity);
        return "删除成功";
    }

    /**
     * @description: 添加一个任务
     * @param {*}
     * @return {*}
     */
    @PostMapping(value = "/plan/add")
    public String addPlan(@RequestBody PlanEntity plan) {
        planS.addPlan(plan);
        return "添加成功";
    }

}
