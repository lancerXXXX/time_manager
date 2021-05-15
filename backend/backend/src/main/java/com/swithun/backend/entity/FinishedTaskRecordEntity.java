/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-28 16:05:48
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-11 15:48:26
 */
package com.swithun.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FinishedTaskRecord", schema = "time_manger", catalog = "")
public class FinishedTaskRecordEntity {
    private Integer id;
    private String startTime;
    private String endTime;
    private PlanEntity planByPlanId;

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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FinishedTaskRecordEntity that = (FinishedTaskRecordEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(startTime, that.startTime)
                && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime);
    }

    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id", nullable = false)
    public PlanEntity getPlanByPlanId() {
        return planByPlanId;
    }

    public void setPlanByPlanId(PlanEntity planByPlanId) {
        this.planByPlanId = planByPlanId;
    }
}
