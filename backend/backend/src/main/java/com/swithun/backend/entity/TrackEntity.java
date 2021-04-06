package com.swithun.backend.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "track", schema = "time_management", catalog = "")
public class TrackEntity {
    private int id;
    private Integer subTask;
    private Time time;
    private Integer tomato;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sub_task")
    public Integer getSubTask() {
        return subTask;
    }

    public void setSubTask(Integer subTask) {
        this.subTask = subTask;
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
    @Column(name = "tomato")
    public Integer getTomato() {
        return tomato;
    }

    public void setTomato(Integer tomato) {
        this.tomato = tomato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrackEntity that = (TrackEntity) o;

        if (id != that.id) return false;
        if (subTask != null ? !subTask.equals(that.subTask) : that.subTask != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (tomato != null ? !tomato.equals(that.tomato) : that.tomato != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subTask != null ? subTask.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (tomato != null ? tomato.hashCode() : 0);
        return result;
    }
}
