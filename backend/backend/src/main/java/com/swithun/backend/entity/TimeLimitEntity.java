/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-25 19:20:28
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-25 19:53:48
 */
package com.swithun.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "timeLimit", schema = "time_manger", catalog = "")
public class TimeLimitEntity {
    public TimeLimitEntity(Integer id, String startTime, String endTime, PlanTypeEntity planTypeByPlanTypeId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.planTypeByPlanTypeId = planTypeByPlanTypeId;
    }

    public TimeLimitEntity() {
    }

    private Integer id;
    private String startTime;
    private String endTime;
    private PlanTypeEntity planTypeByPlanTypeId;

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
    @Column(name = "start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeLimitEntity that = (TimeLimitEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime);
    }

    @ManyToOne
    @JoinColumn(name = "plan_type_id", referencedColumnName = "id")
    public PlanTypeEntity getPlanTypeByPlanTypeId() {
        return planTypeByPlanTypeId;
    }

    public void setPlanTypeByPlanTypeId(PlanTypeEntity planTypeByPlanTypeId) {
        this.planTypeByPlanTypeId = planTypeByPlanTypeId;
    }
}
