/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-20 14:58:49
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-20 16:33:31
 */

package com.swithun.backend.controller;

import java.util.List;

import com.swithun.backend.DTO.AddPlanTypeDTO;
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
    PlanService planService;


    @PostMapping(value = "/plantype/addplantype")
    public String addPlanType(@RequestBody AddPlanTypeDTO addPlanTypeDTO) {
        PlanTypeEntity planTypeEntity = addPlanTypeDTO.getPlanTypeEntity();
        PlanTypeEntity planTypeByParentId = new PlanTypeEntity();
        planTypeByParentId.setId(addPlanTypeDTO.getParent_id());
        planTypeEntity.setPlanTypeByParentId(planTypeByParentId);
        planService.addPlanType(addPlanTypeDTO.getPlanTypeEntity());
        return "添加成功";
    }

    @GetMapping(value = "/plantype/getallplantype")
    public List<PlanTypeEntity> getAllPlanType() {
        return planService.getAllPlanType();
    }

}
