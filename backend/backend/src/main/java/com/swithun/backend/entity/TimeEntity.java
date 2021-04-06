package com.swithun.backend.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "time", schema = "time_management", catalog = "")
public class TimeEntity {
    private int id;
    private byte not;
    private Time start;
    private Time end;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "start")
    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    @Basic
    @Column(name = "end")
    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeEntity that = (TimeEntity) o;

        if (id != that.id) return false;
        if (not != that.not) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) not;
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
