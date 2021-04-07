package com.swithun.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rpscore", schema = "time_management", catalog = "")
public class RpscoreEntity {
    private int point;

    @Id
    @Column(name = "point")
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RpscoreEntity that = (RpscoreEntity) o;

        if (point != that.point) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return point;
    }
}
