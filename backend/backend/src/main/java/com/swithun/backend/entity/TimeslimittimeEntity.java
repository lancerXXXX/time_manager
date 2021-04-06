package com.swithun.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "timeslimittime", schema = "time_management", catalog = "")
public class TimeslimittimeEntity {
    private int id;
    private int root;
    private int type;
    private int time;

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
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "time")
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeslimittimeEntity that = (TimeslimittimeEntity) o;

        if (id != that.id) return false;
        if (root != that.root) return false;
        if (type != that.type) return false;
        if (time != that.time) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + root;
        result = 31 * result + type;
        result = 31 * result + time;
        return result;
    }
}
