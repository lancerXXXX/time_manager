/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-20 14:09:15
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-26 10:12:27
 */
package com.swithun.backend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "planType", schema = "time_manger", catalog = "")
public class PlanTypeEntity {
    public PlanTypeEntity() {
    }

    public PlanTypeEntity(Integer id) {
        this.id = id;
    }

    private Integer id;
    private String name;
    @JsonIgnore
    private PlanTypeEntity planTypeByParentId;
    private Collection<PlanTypeEntity> planTypesById;
    @JsonIgnore
    private Collection<OrderLimitEntity> orderLimitsById;
    @JsonIgnore
    private Collection<OrderLimitEntity> orderLimitsById_0;
    @JsonIgnore
    private Collection<PlanEntity> plansById;
    @JsonIgnore
    private Collection<TimeLimitEntity> timeLimitsById;
    @JsonIgnore
    private Collection<FrequencyLimitEntity> frequencyLimitsById;

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
    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PlanTypeEntity that = (PlanTypeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "planTypeByBannedPlanTypeId")
    public Collection<OrderLimitEntity> getOrderLimitsById() {
        return orderLimitsById;
    }

    public void setOrderLimitsById(Collection<OrderLimitEntity> orderLimitsById) {
        this.orderLimitsById = orderLimitsById;
    }

    @OneToMany(mappedBy = "planTypeByBaseedPlanTypeId")
    public Collection<OrderLimitEntity> getOrderLimitsById_0() {
        return orderLimitsById_0;
    }

    public void setOrderLimitsById_0(Collection<OrderLimitEntity> orderLimitsById_0) {
        this.orderLimitsById_0 = orderLimitsById_0;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public PlanTypeEntity getPlanTypeByParentId() {
        return planTypeByParentId;
    }

    public void setPlanTypeByParentId(PlanTypeEntity planTypeByParentId) {
        this.planTypeByParentId = planTypeByParentId;
    }

    @OneToMany(mappedBy = "planTypeByParentId", fetch = FetchType.LAZY)
    @JsonProperty("children")
    public Collection<PlanTypeEntity> getPlanTypesById() {
        return planTypesById;
    }

    public void setPlanTypesById(Collection<PlanTypeEntity> planTypesById) {
        this.planTypesById = planTypesById;
    }

    @OneToMany(mappedBy = "planTypeByPlanType")
    public Collection<PlanEntity> getPlansById() {
        return plansById;
    }

    public void setPlansById(Collection<PlanEntity> plansById) {
        this.plansById = plansById;
    }

    @OneToMany(mappedBy = "planTypeByPlanTypeId")
    public Collection<TimeLimitEntity> getTimeLimitsById() {
        return timeLimitsById;
    }

    public void setTimeLimitsById(Collection<TimeLimitEntity> timeLimitsById) {
        this.timeLimitsById = timeLimitsById;
    }

    @OneToMany(mappedBy = "planTypeByPlanTypeId")
    public Collection<FrequencyLimitEntity> getFrequencyLimitsById() {
        return frequencyLimitsById;
    }

    public void setFrequencyLimitsById(Collection<FrequencyLimitEntity> frequencyLimitsById) {
        this.frequencyLimitsById = frequencyLimitsById;
    }
}
