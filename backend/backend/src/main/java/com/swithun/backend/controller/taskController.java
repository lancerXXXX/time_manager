/*
 * @Descripttion: 任务模块Controller
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-08 10:14:30
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-08 15:06:05
 */
package com.swithun.backend.controller;

import java.util.List;

import com.swithun.backend.dao.PlanitemRepository;
import com.swithun.backend.entity.PlanEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class taskController {
    @Autowired
    private PlanitemRepository planitemRepository;

    @GetMapping(value = "/gettask")
    public List<PlanEntity> tasktest() {
        java.util.Date java_date = new java.util.Date();
        java.sql.Date sql_date = new java.sql.Date(java_date.getTime());
        return planitemRepository.findByExpectedStartDate(sql_date);
    }

    @GetMapping(value = "/addtask")
    public void addTask() {
        PlanEntity pe = new PlanEntity();
        pe.setId(1);
        java.util.Date java_date = new java.util.Date();
        java.sql.Date sql_date = new java.sql.Date(java_date.getTime());
        pe.setExpectedStartDate(sql_date);
        planitemRepository.save(pe);
    }

    @GetMapping(value = "/test")
    public String test() {
        return "Hello";
    }

}