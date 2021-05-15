/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-11 16:03:10
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-11 21:18:28
 */
package com.swithun.backend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "reward_setting", schema = "time_manger", catalog = "")
public class RewardSettingEntity {
    private Integer id;
    @JsonProperty("taskType")
    private PlanTypeEntity planTypeByTaskType;
    private String planName;
    private Integer point;
    @JsonProperty("isOn")
    private boolean isOn;
    @JsonIgnore
    private Collection<RewardRecordEntity> rewardRecordsById;
    private ArrayList<Integer> typeList;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Transient
    public List<Integer> getTypeList() {
        return typeList;
    }

    public void setTypeList(ArrayList<Integer> typeList) {
        this.typeList = typeList;
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
    @Column(name = "is_on")
    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

    @Basic
    @Column(name = "point")
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RewardSettingEntity that = (RewardSettingEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(planName, that.planName)
                && Objects.equals(point, that.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, planName, point);
    }

    @OneToMany(mappedBy = "rewardSettingBySettingId", cascade = CascadeType.DETACH)
    public Collection<RewardRecordEntity> getRewardRecordsById() {
        return rewardRecordsById;
    }

    public void setRewardRecordsById(Collection<RewardRecordEntity> rewardRecordsById) {
        this.rewardRecordsById = rewardRecordsById;
    }

    @ManyToOne
    @JoinColumn(name = "task_type", referencedColumnName = "id")
    public PlanTypeEntity getPlanTypeByTaskType() {
        return planTypeByTaskType;
    }

    public void setPlanTypeByTaskType(PlanTypeEntity planTypeByTaskType) {
        this.planTypeByTaskType = planTypeByTaskType;
    }
}
