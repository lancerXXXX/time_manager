package com.swithun.backend.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "tomato", schema = "time_management", catalog = "")
public class TomatoEntity {
    private int id;
    private Time workTime;
    private Time relaxTime;
    private String round;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "relax_time")
    public Time getRelaxTime() {
        return relaxTime;
    }

    public void setRelaxTime(Time relaxTime) {
        this.relaxTime = relaxTime;
    }

    @Basic
    @Column(name = "round")
    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TomatoEntity that = (TomatoEntity) o;

        if (id != that.id) return false;
        if (workTime != null ? !workTime.equals(that.workTime) : that.workTime != null) return false;
        if (relaxTime != null ? !relaxTime.equals(that.relaxTime) : that.relaxTime != null) return false;
        if (round != null ? !round.equals(that.round) : that.round != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (workTime != null ? workTime.hashCode() : 0);
        result = 31 * result + (relaxTime != null ? relaxTime.hashCode() : 0);
        result = 31 * result + (round != null ? round.hashCode() : 0);
        return result;
    }
}
