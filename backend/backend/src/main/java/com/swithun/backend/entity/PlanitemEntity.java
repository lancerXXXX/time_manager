package com.swithun.backend.entity;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "planitem", schema = "time_management", catalog = "")
public class PlanitemEntity {
    private int id;
    private int taskType;
    private String taskName;
    private String priority;
    private Time time;
    private Timestamp startTime;
    private Timestamp endTime;
    private Time workTime;
    private String note;
    private String devotion;
    private String satisfaction;
    private String type;
    private Integer parent;
    private PlanitemtypeEntity planitemtypeByTaskType;
    private Collection<PlanitemorderEntity> planitemordersById;
    private Collection<PlanitemorderEntity> planitemordersById_0;
    private Collection<PlanitemtimerangeEntity> planitemtimerangesById;
    private Collection<RprecordEntity> rprecordsById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "task_type")
    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    @Basic
    @Column(name = "task_name")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @Column(name = "priority")
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "time")
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Basic
    @Column(name = "startTime")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endTime")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "work_time")
    public Time getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Time workTime) {
        this.workTime = workTime;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "devotion")
    public String getDevotion() {
        return devotion;
    }

    public void setDevotion(String devotion) {
        this.devotion = devotion;
    }

    @Basic
    @Column(name = "satisfaction")
    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "parent")
    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanitemEntity that = (PlanitemEntity) o;

        if (id != that.id) return false;
        if (taskType != that.taskType) return false;
        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;
        if (priority != null ? !priority.equals(that.priority) : that.priority != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (workTime != null ? !workTime.equals(that.workTime) : that.workTime != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (devotion != null ? !devotion.equals(that.devotion) : that.devotion != null) return false;
        if (satisfaction != null ? !satisfaction.equals(that.satisfaction) : that.satisfaction != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + taskType;
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (workTime != null ? workTime.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (devotion != null ? devotion.hashCode() : 0);
        result = 31 * result + (satisfaction != null ? satisfaction.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "task_type", referencedColumnName = "id", nullable = false)
    public PlanitemtypeEntity getPlanitemtypeByTaskType() {
        return planitemtypeByTaskType;
    }

    public void setPlanitemtypeByTaskType(PlanitemtypeEntity planitemtypeByTaskType) {
        this.planitemtypeByTaskType = planitemtypeByTaskType;
    }

    @OneToMany(mappedBy = "planitemByPrePlanItemId")
    public Collection<PlanitemorderEntity> getPlanitemordersById() {
        return planitemordersById;
    }

    public void setPlanitemordersById(Collection<PlanitemorderEntity> planitemordersById) {
        this.planitemordersById = planitemordersById;
    }

    @OneToMany(mappedBy = "planitemByNextPlanItemId")
    public Collection<PlanitemorderEntity> getPlanitemordersById_0() {
        return planitemordersById_0;
    }

    public void setPlanitemordersById_0(Collection<PlanitemorderEntity> planitemordersById_0) {
        this.planitemordersById_0 = planitemordersById_0;
    }

    @OneToMany(mappedBy = "planitemByPlanItemId")
    public Collection<PlanitemtimerangeEntity> getPlanitemtimerangesById() {
        return planitemtimerangesById;
    }

    public void setPlanitemtimerangesById(Collection<PlanitemtimerangeEntity> planitemtimerangesById) {
        this.planitemtimerangesById = planitemtimerangesById;
    }

    @OneToMany(mappedBy = "planitemByTask")
    public Collection<RprecordEntity> getRprecordsById() {
        return rprecordsById;
    }

    public void setRprecordsById(Collection<RprecordEntity> rprecordsById) {
        this.rprecordsById = rprecordsById;
    }
}
