package com.swithun.backend.controller;

import java.util.List;
import java.util.Map;

import com.swithun.backend.service.PlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PlanRelationController {
    @Autowired
    PlanService planS;

    @PostMapping(value = "/plan/addRelation")
    public void addRelation(@RequestBody Map<String, Object> mp) {
        List<Integer> relation = (List<Integer>) mp.get("relation");
        planS.addRelation(relation.get(1), relation.get(0));
    }

    @PostMapping(value = "/plan/relation/updateRelation")
    public void updateRelation(@RequestBody Map<String, Object> mp) {
        planS.updateRelation(mp);
    }

    @PostMapping(value = "/plan/relation/delete")
    public void deleteRelation(@RequestBody Map<String, Object> mp) {
        planS.deleteRelation(mp);
    }
}
/*
 * @Descripttion:
 * 
 * @version:
 * 
 * @@Company: None
 * 
 * @Author: Swithun Liu
 * 
 * @Date: 2021-05-13 10:58:32
 * 
 * @LastEditors: Swithun Liu
 * 
 * @LastEditTime: 2021-05-13 10:58:32
 */
