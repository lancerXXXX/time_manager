package com.swithun.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "rpstore", schema = "time_management", catalog = "")
public class RpstoreEntity {
    private int id;
    private int task;
    private int cost;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "cost")
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RpstoreEntity that = (RpstoreEntity) o;

        if (id != that.id) return false;
        if (task != that.task) return false;
        if (cost != that.cost) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + task;
        result = 31 * result + cost;
        return result;
    }
}
