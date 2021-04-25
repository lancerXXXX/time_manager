package com.swithun.backend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@Entity
@Table(name = "orderLimit", schema = "time_manger", catalog = "")
public class OrderLimitEntity {
    private Integer id;
    @JsonProperty("order")
    private Integer order_type;
    @JsonProperty("bannedTaskType")
    private PlanTypeEntity planTypeByBannedPlanTypeId;
    @JsonProperty("basedTaskType")
    private PlanTypeEntity planTypeByBaseedPlanTypeId;

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
    @Column(name = "order_type")
    public Integer getOrderType() {
        return order_type;
    }

    public void setOrderType(Integer order) {
        this.order_type = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLimitEntity that = (OrderLimitEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(order_type, that.order_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order_type);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banned_plan_type_id", referencedColumnName = "id")
    public PlanTypeEntity getPlanTypeByBannedPlanTypeId() {
        return planTypeByBannedPlanTypeId;
    }

    public void setPlanTypeByBannedPlanTypeId(PlanTypeEntity planTypeByBannedPlanTypeId) {
        this.planTypeByBannedPlanTypeId = planTypeByBannedPlanTypeId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "baseed_plan_type_id", referencedColumnName = "id")
    public PlanTypeEntity getPlanTypeByBaseedPlanTypeId() {
        return planTypeByBaseedPlanTypeId;
    }

    public void setPlanTypeByBaseedPlanTypeId(PlanTypeEntity planTypeByBaseedPlanTypeId) {
        this.planTypeByBaseedPlanTypeId = planTypeByBaseedPlanTypeId;
    }
}
