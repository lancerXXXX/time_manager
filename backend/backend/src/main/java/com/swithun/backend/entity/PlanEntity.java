package com.swithun.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "plan", schema = "time_manger", catalog = "")
public class PlanEntity {
    private Integer id;
    private Integer everyWeek;
    private Date expectedStartDate;
    private Date expectedEndDate;
    private Timestamp expectedStartTime;
    private Timestamp expectedEndTime;
    private Timestamp practiceStartDateTime;
    private Byte ended;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "every_week")
    public Integer getEveryWeek() {
        return everyWeek;
    }

    public void setEveryWeek(Integer everyWeek) {
        this.everyWeek = everyWeek;
    }

    @Basic
    @Column(name = "expected_start_date")
    public Date getExpectedStartDate() {
        return expectedStartDate;
    }

    public void setExpectedStartDate(Date expectedStartDate) {
        this.expectedStartDate = expectedStartDate;
    }

    @Basic
    @Column(name = "expected_end_date")
    public Date getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(Date expectedEndDate) {
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
    @Column(name = "ended")
    public Byte getEnded() {
        return ended;
    }

    public void setEnded(Byte ended) {
        this.ended = ended;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanEntity that = (PlanEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(everyWeek, that.everyWeek) && Objects.equals(expectedStartDate, that.expectedStartDate) && Objects.equals(expectedEndDate, that.expectedEndDate) && Objects.equals(expectedStartTime, that.expectedStartTime) && Objects.equals(expectedEndTime, that.expectedEndTime) && Objects.equals(practiceStartDateTime, that.practiceStartDateTime) && Objects.equals(ended, that.ended);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, everyWeek, expectedStartDate, expectedEndDate, expectedStartTime, expectedEndTime, practiceStartDateTime, ended);
    }
}
