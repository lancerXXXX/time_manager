package com.swithun.backend.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "FinishedTaskRecord", schema = "time_manger", catalog = "")
public class FinishedTaskRecordEntity {
    private Integer id;
    private Timestamp startTime;
    private Timestamp endTime;
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
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
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
