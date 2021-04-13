/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-13 14:47:18
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-13 15:12:18
 */

package com.swithun.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "planTemplate", schema = "time_manger", catalog = "")
public class PlanTemplateEntity{
    private Integer id;
    private String planName;
    private Integer minutes;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "plan_name")
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @Basic
    @Column(name = "minutes")
    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

}