package com.swithun.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reward_point", schema = "time_manger", catalog = "")
public class RewardPointEntity {
    private Integer id;
    private Integer point;
    private Integer userId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RewardPointEntity that = (RewardPointEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(point, that.point) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, point, userId);
    }
}
