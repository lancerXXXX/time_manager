package com.swithun.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "filtertemplateplanitemtype", schema = "time_management", catalog = "")
public class FiltertemplateplanitemtypeEntity {
    private int id;
    private int root;
    private byte not;
    private int taskType;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "root")
    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    @Basic
    @Column(name = "not")
    public byte getNot() {
        return not;
    }

    public void setNot(byte not) {
        this.not = not;
    }

    @Basic
    @Column(name = "task_type")
    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiltertemplateplanitemtypeEntity that = (FiltertemplateplanitemtypeEntity) o;

        if (id != that.id) return false;
        if (root != that.root) return false;
        if (not != that.not) return false;
        if (taskType != that.taskType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + root;
        result = 31 * result + (int) not;
        result = 31 * result + taskType;
        return result;
    }
}
