package com.swithun.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "rpsettings", schema = "time_management", catalog = "")
public class RpsettingsEntity {
    private int id;
    private int taskType;
    private String taskName;
    private String point;
    private PlanitemtypeEntity planitemtypeByTaskType;

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
    @Column(name = "point")
    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RpsettingsEntity that = (RpsettingsEntity) o;

        if (id != that.id) return false;
        if (taskType != that.taskType) return false;
        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;
        if (point != null ? !point.equals(that.point) : that.point != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + taskType;
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (point != null ? point.hashCode() : 0);
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
}
