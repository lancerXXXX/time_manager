package com.swithun.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "planitemtimerange", schema = "time_management", catalog = "")
public class PlanitemtimerangeEntity {
    private int id;
    private int planItemId;
    private int dateId;
    private Integer timeId;
    private byte type;

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
    @Column(name = "date_id")
    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    @Basic
    @Column(name = "time_id")
    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    @Basic
    @Column(name = "type")
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanitemtimerangeEntity that = (PlanitemtimerangeEntity) o;

        if (id != that.id) return false;
        if (planItemId != that.planItemId) return false;
        if (dateId != that.dateId) return false;
        if (type != that.type) return false;
        if (timeId != null ? !timeId.equals(that.timeId) : that.timeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + planItemId;
        result = 31 * result + dateId;
        result = 31 * result + (timeId != null ? timeId.hashCode() : 0);
        result = 31 * result + (int) type;
        return result;
    }
}
