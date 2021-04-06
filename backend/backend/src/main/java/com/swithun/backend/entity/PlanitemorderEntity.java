package com.swithun.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "planitemorder", schema = "time_management", catalog = "")
public class PlanitemorderEntity {
    private int id;
    private int planItemId;
    private Integer prePlanItemId;
    private Integer nextPlanItemId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "planItem_id")
    public int getPlanItemId() {
        return planItemId;
    }

    public void setPlanItemId(int planItemId) {
        this.planItemId = planItemId;
    }

    @Basic
    @Column(name = "pre_planItem_id")
    public Integer getPrePlanItemId() {
        return prePlanItemId;
    }

    public void setPrePlanItemId(Integer prePlanItemId) {
        this.prePlanItemId = prePlanItemId;
    }

    @Basic
    @Column(name = "next_planItem_id")
    public Integer getNextPlanItemId() {
        return nextPlanItemId;
    }

    public void setNextPlanItemId(Integer nextPlanItemId) {
        this.nextPlanItemId = nextPlanItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanitemorderEntity that = (PlanitemorderEntity) o;

        if (id != that.id) return false;
        if (planItemId != that.planItemId) return false;
        if (prePlanItemId != null ? !prePlanItemId.equals(that.prePlanItemId) : that.prePlanItemId != null)
            return false;
        if (nextPlanItemId != null ? !nextPlanItemId.equals(that.nextPlanItemId) : that.nextPlanItemId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + planItemId;
        result = 31 * result + (prePlanItemId != null ? prePlanItemId.hashCode() : 0);
        result = 31 * result + (nextPlanItemId != null ? nextPlanItemId.hashCode() : 0);
        return result;
    }
}
