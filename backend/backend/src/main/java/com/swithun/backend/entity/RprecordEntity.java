package com.swithun.backend.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rprecord", schema = "time_management", catalog = "")
public class RprecordEntity {
    private int id;
    private Timestamp time;
    private int task;
    private Integer point;
    private Integer cost;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "task")
    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    @Basic
    @Column(name = "point")
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Basic
    @Column(name = "cost")
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RprecordEntity that = (RprecordEntity) o;

        if (id != that.id) return false;
        if (task != that.task) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (point != null ? !point.equals(that.point) : that.point != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + task;
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }
}
