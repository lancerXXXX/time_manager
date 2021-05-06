/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-05 21:10:28
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-06 09:12:59
 */
package com.swithun.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subTask", schema = "time_manger", catalog = "")
public class SubTaskEntity {

    private Integer id;
    private String name;
    private Integer time;
    private Integer expectedTime;
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
    @Column(name = "expected_time")
    public Integer getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Integer expectedTime) {
        this.expectedTime = expectedTime;
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
    @Column(name = "time")
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SubTaskEntity that = (SubTaskEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, time);
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
