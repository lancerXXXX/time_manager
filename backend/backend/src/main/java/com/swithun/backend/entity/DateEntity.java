package com.swithun.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "date", schema = "time_management", catalog = "")
public class DateEntity {
    private int id;
    private byte not;
    private String repeatType;
    private Date date;
    private Date date2;
    private Collection<FiltertemplatetimeEntity> filtertemplatetimesById;
    private Collection<PlanitemtimerangeEntity> planitemtimerangesById;
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
    @Column(name = "not")
    public byte getNot() {
        return not;
    }

    public void setNot(byte not) {
        this.not = not;
    }

    @Basic
    @Column(name = "repeatType")
    public String getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(String repeatType) {
        this.repeatType = repeatType;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "date2")
    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateEntity that = (DateEntity) o;

        if (id != that.id) return false;
        if (not != that.not) return false;
        if (repeatType != null ? !repeatType.equals(that.repeatType) : that.repeatType != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (date2 != null ? !date2.equals(that.date2) : that.date2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) not;
        result = 31 * result + (repeatType != null ? repeatType.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (date2 != null ? date2.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "dateByTime")
    public Collection<FiltertemplatetimeEntity> getFiltertemplatetimesById() {
        return filtertemplatetimesById;
    }

    public void setFiltertemplatetimesById(Collection<FiltertemplatetimeEntity> filtertemplatetimesById) {
        this.filtertemplatetimesById = filtertemplatetimesById;
    }

    @OneToMany(mappedBy = "dateByTimeId")
    public Collection<PlanitemtimerangeEntity> getPlanitemtimerangesById() {
        return planitemtimerangesById;
    }

    public void setPlanitemtimerangesById(Collection<PlanitemtimerangeEntity> planitemtimerangesById) {
        this.planitemtimerangesById = planitemtimerangesById;
    }

    @OneToMany(mappedBy = "dateByTime")
    public Collection<TimeslimittimeEntity> getTimeslimittimesById() {
        return timeslimittimesById;
    }

    public void setTimeslimittimesById(Collection<TimeslimittimeEntity> timeslimittimesById) {
        this.timeslimittimesById = timeslimittimesById;
    }
}
