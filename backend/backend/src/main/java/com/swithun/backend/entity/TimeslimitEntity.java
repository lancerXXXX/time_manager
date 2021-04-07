package com.swithun.backend.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "timeslimit", schema = "time_management", catalog = "")
public class TimeslimitEntity {
    private int id;
    private int taskType;
    private String taskName;
    private String operator;
    private String frequency;
    private Collection<RpstoreEntity> rpstoresById;
    private PlanitemtypeEntity planitemtypeByTaskType;
    private Collection<TimeslimittimeEntity> timeslimittimesById;

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
    @Column(name = "operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "frequency")
    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeslimitEntity that = (TimeslimitEntity) o;

        if (id != that.id) return false;
        if (taskType != that.taskType) return false;
        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;
        if (frequency != null ? !frequency.equals(that.frequency) : that.frequency != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + taskType;
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (frequency != null ? frequency.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "timeslimitByTask")
    public Collection<RpstoreEntity> getRpstoresById() {
        return rpstoresById;
    }

    public void setRpstoresById(Collection<RpstoreEntity> rpstoresById) {
        this.rpstoresById = rpstoresById;
    }

    @ManyToOne
    @JoinColumn(name = "task_type", referencedColumnName = "id", nullable = false)
    public PlanitemtypeEntity getPlanitemtypeByTaskType() {
        return planitemtypeByTaskType;
    }

    public void setPlanitemtypeByTaskType(PlanitemtypeEntity planitemtypeByTaskType) {
        this.planitemtypeByTaskType = planitemtypeByTaskType;
    }

    @OneToMany(mappedBy = "timeslimitByRoot")
    public Collection<TimeslimittimeEntity> getTimeslimittimesById() {
        return timeslimittimesById;
    }

    public void setTimeslimittimesById(Collection<TimeslimittimeEntity> timeslimittimesById) {
        this.timeslimittimesById = timeslimittimesById;
    }
}
