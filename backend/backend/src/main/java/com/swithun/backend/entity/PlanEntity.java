package com.swithun.backend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "plan", schema = "time_manger", catalog = "")
public class PlanEntity {
    private Integer id;
    private String planName;
    private Integer repeatType;
    private String expectedStartDate;
    private String expectedEndDate;
    private Timestamp expectedStartTime;
    private Timestamp expectedEndTime;
    private Timestamp practiceStartDateTime;
    private Timestamp practiceEndDateTime;
    @JsonIgnore
    private Collection<FinishedTaskRecordEntity> finishedTaskRecordsById;
    @JsonIgnore
    private Collection<UnfinishedPlanEntity> unfinishedPlansById;
    @JsonIgnore
    private Collection<FinishedPlanEntity> finishedPlansById;
    private PlanTypeEntity planTypeByPlanType;

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
    @Column(name = "plan_name")
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @Basic
    @Column(name = "repeat_type")
    public Integer getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(Integer repeatType) {
        this.repeatType = repeatType;
    }

    @Basic
    @Column(name = "expected_start_date")
    public String getExpectedStartDate() {
        return expectedStartDate;
    }

    public void setExpectedStartDate(String expectedStartDate) {
        this.expectedStartDate = expectedStartDate;
    }

    @Basic
    @Column(name = "expected_end_date")
    public String getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(String expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    @Basic
    @Column(name = "expected_start_time")
    public Timestamp getExpectedStartTime() {
        return expectedStartTime;
    }

    public void setExpectedStartTime(Timestamp expectedStartTime) {
        this.expectedStartTime = expectedStartTime;
    }

    @Basic
    @Column(name = "expected_end_time")
    public Timestamp getExpectedEndTime() {
        return expectedEndTime;
    }

    public void setExpectedEndTime(Timestamp expectedEndTime) {
        this.expectedEndTime = expectedEndTime;
    }

    @Basic
    @Column(name = "practice_start_date_time")
    public Timestamp getPracticeStartDateTime() {
        return practiceStartDateTime;
    }

    public void setPracticeStartDateTime(Timestamp practiceStartDateTime) {
        this.practiceStartDateTime = practiceStartDateTime;
    }

    @Basic
    @Column(name = "practice_end_date_time")
    public Timestamp getPracticeEndDateTime() {
        return practiceEndDateTime;
    }

    public void setPracticeEndDateTime(Timestamp practiceEndDateTime) {
        this.practiceEndDateTime = practiceEndDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PlanEntity that = (PlanEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(planName, that.planName)
                && Objects.equals(repeatType, that.repeatType)
                && Objects.equals(expectedStartDate, that.expectedStartDate)
                && Objects.equals(expectedEndDate, that.expectedEndDate)
                && Objects.equals(expectedStartTime, that.expectedStartTime)
                && Objects.equals(expectedEndTime, that.expectedEndTime)
                && Objects.equals(practiceStartDateTime, that.practiceStartDateTime)
                && Objects.equals(practiceEndDateTime, that.practiceEndDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, planName, repeatType, expectedStartDate, expectedEndDate, expectedStartTime,
                expectedEndTime, practiceStartDateTime, practiceEndDateTime);
    }

    @OneToMany(mappedBy = "planByPlanId")
    public Collection<FinishedTaskRecordEntity> getFinishedTaskRecordsById() {
        return finishedTaskRecordsById;
    }

    public void setFinishedTaskRecordsById(Collection<FinishedTaskRecordEntity> finishedTaskRecordsById) {
        this.finishedTaskRecordsById = finishedTaskRecordsById;
    }

    @OneToMany(mappedBy = "planByPlanId")
    public Collection<UnfinishedPlanEntity> getUnfinishedPlansById() {
        return unfinishedPlansById;
    }

    public void setUnfinishedPlansById(Collection<UnfinishedPlanEntity> unfinishedPlansById) {
        this.unfinishedPlansById = unfinishedPlansById;
    }

    @OneToMany(mappedBy = "planByPlanId")
    public Collection<FinishedPlanEntity> getFinishedPlansById() {
        return finishedPlansById;
    }

    public void setFinishedPlansById(Collection<FinishedPlanEntity> finishedPlansById) {
        this.finishedPlansById = finishedPlansById;
    }

    @ManyToOne
    @JoinColumn(name = "plan_type", referencedColumnName = "id")
    public PlanTypeEntity getPlanTypeByPlanType() {
        return planTypeByPlanType;
    }

    public void setPlanTypeByPlanType(PlanTypeEntity planTypeByPlanType) {
        this.planTypeByPlanType = planTypeByPlanType;
    }
}
