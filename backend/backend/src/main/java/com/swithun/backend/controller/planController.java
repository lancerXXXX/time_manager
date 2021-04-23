/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-20 14:58:49
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-21 16:12:32
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
    public Integer addPlanType(@RequestBody AddPlanTypeDTO addPlanTypeDTO) {
        PlanTypeEntity planTypeEntity = addPlanTypeDTO.getPlanTypeEntity();
        Integer parent_id = addPlanTypeDTO.getParent_id();
        PlanTypeEntity planTypeByParentId = new PlanTypeEntity();
        if (parent_id != -1) {
            planTypeByParentId.setId(addPlanTypeDTO.getParent_id());
            planTypeEntity.setPlanTypeByParentId(planTypeByParentId);
        }
        planService.addPlanType(addPlanTypeDTO.getPlanTypeEntity());
        return planService.getPlanTypeEntityByName(addPlanTypeDTO.getPlanTypeEntity().getName()).getId();
    }

    @GetMapping(value = "/plantype/getallplantype")
    public List<PlanTypeEntity> getAllPlanType() {
        return planService.getAllPlanType();
    }

    @PostMapping(value="/plantype/updateplantypename")
    public String updatePlanType(@RequestBody PlanTypeEntity planTypeEntity) {
        planService.updatePlanTypeName(planTypeEntity);
        return "修改成功";
    }

    @PostMapping(value="/plantype/deleteplantype")
    public String deletePlanType(@RequestBody PlanTypeEntity planTypeEntity) {
        planService.deletePlanType(planTypeEntity);
        return "删除成功";
    }
    

}
