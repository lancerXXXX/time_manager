/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-23 10:55:53
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-23 10:58:25
 */
package com.swithun.backend.DTO;

import java.util.List;

public class getOrderLimitDTO {
    private Integer id;
    private Integer order;
    List<Integer> bannedTaskType;
    List<Integer> basedTaskType;

    public getOrderLimitDTO(Integer id, Integer order, List<Integer> bannedTaskType, List<Integer> basedTaskType) {
        this.id = id;
        this.order = order;
        this.bannedTaskType = bannedTaskType;
        this.basedTaskType = basedTaskType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<Integer> getBannedTaskType() {
        return bannedTaskType;
    }

    public void setBannedTaskType(List<Integer> bannedTaskType) {
        this.bannedTaskType = bannedTaskType;
    }

    public List<Integer> getBasedTaskType() {
        return basedTaskType;
    }

    public void setBasedTaskType(List<Integer> basedTaskType) {
        this.basedTaskType = basedTaskType;
    }
}