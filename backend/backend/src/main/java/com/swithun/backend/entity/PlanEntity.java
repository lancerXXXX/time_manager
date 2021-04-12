package com.swithun.backend.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "plan", schema = "time_manger", catalog = "")
public class PlanEntity {
    private Integer id;
    private Integer repeatType; // 重复类型 0(1次) 1(每周) 2(每月) 3(每年)
    private String expectedStartDate; // 预计开始时间
    // 重复类型 0 2020-01-01
    //         1 1~7
    //         2 1~31
    //         3 01-01
    private String expectedEndDate; // 预计结束时间
    private Timestamp expectedStartTime; // 预计几点开始
    private Timestamp expectedEndTime; // 预计几点结束
    private Timestamp practiceStartDateTime; // 实际结束时间(整个可重复任务开始)
    private Timestamp practiceEndDateTime; // 实际结束时间(整个可重复任务结束)
    private Byte ended;

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
        return Objects.equals(id, that.id) && Objects.equals(expectedStartDate, that.expectedStartDate) && Objects.equals(expectedEndDate, that.expectedEndDate) && Objects.equals(expectedStartTime, that.expectedStartTime) && Objects.equals(expectedEndTime, that.expectedEndTime) && Objects.equals(practiceStartDateTime, that.practiceStartDateTime) && Objects.equals(ended, that.ended);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,expectedStartDate, expectedEndDate, expectedStartTime, expectedEndTime, practiceStartDateTime, ended);
    }


}