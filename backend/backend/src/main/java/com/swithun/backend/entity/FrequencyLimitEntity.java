/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-26 10:09:19
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-11 16:57:21
 */
package com.swithun.backend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "frequencyLimit", schema = "time_manger", catalog = "")
public class FrequencyLimitEntity {
    public FrequencyLimitEntity(Integer min, Integer max, Integer time, PlanTypeEntity planTypeByPlanTypeId) {
        this.min = min;
        this.max = max;
        this.time = time;
        this.planTypeByPlanTypeId = planTypeByPlanTypeId;
    }

    public FrequencyLimitEntity(Integer id, Integer min, Integer max, Integer time,
            PlanTypeEntity planTypeByPlanTypeId) {
        this.id = id;
        this.min = min;
        this.max = max;
        this.time = time;
        this.planTypeByPlanTypeId = planTypeByPlanTypeId;
    }

    public FrequencyLimitEntity() {
    }

    private Integer id;
    private Integer min;
    private Integer max;
    private Integer time;
    private PlanTypeEntity planTypeByPlanTypeId;
    @JsonIgnore
    private Collection<RewardRecordEntity> rewardRecordsById;

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
    @Column(name = "min")
    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    @Basic
    @Column(name = "max")
    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrequencyLimitEntity that = (FrequencyLimitEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(max, that.max) && Objects.equals(min, that.min) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, max, min, time);
    }

    @ManyToOne
    @JoinColumn(name = "plan_type_id", referencedColumnName = "id")
    public PlanTypeEntity getPlanTypeByPlanTypeId() {
        return planTypeByPlanTypeId;
    }

    public void setPlanTypeByPlanTypeId(PlanTypeEntity planTypeByPlanTypeId) {
        this.planTypeByPlanTypeId = planTypeByPlanTypeId;
    }

    @OneToMany(mappedBy = "frequencyLimitByFLimitId")
    public Collection<RewardRecordEntity> getRewardRecordsById() {
        return rewardRecordsById;
    }

    public void setRewardRecordsById(Collection<RewardRecordEntity> rewardRecordsById) {
        this.rewardRecordsById = rewardRecordsById;
    }
}
