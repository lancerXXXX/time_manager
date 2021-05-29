/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-28 20:49:34
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-28 10:33:30
 */
package com.swithun.backend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

@Entity
@Table(name = "track", schema = "time_manger", catalog = "")
public class TrackEntity {
    private Integer id;
    private String name;
    private String startTime;
    private String endTime;
    @JsonIgnore
    private PlanEntity planByParentPlan;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        TrackEntity that = (TrackEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startTime, endTime);
    }

    @ManyToOne
    @JoinColumn(name = "parent_plan", referencedColumnName = "id")
    public PlanEntity getPlanByParentPlan() {
        return planByParentPlan;
    }

    public void setPlanByParentPlan(PlanEntity planByParentPlan) {
        this.planByParentPlan = planByParentPlan;
    }
}
