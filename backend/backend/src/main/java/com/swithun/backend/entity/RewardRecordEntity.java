/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-11 16:03:09
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-11 16:26:24
 */
package com.swithun.backend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@Entity
@Table(name = "reward_record", schema = "time_manger", catalog = "")
public class RewardRecordEntity {
    private Integer id;
    private Integer point;
    private String time;
    @JsonProperty("ruleId")
    private FrequencyLimitEntity frequencyLimitByFLimitId;
    @JsonProperty("settingId")
    private RewardSettingEntity rewardSettingBySettingId;

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
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RewardRecordEntity that = (RewardRecordEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(point, that.point) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, point, time);
    }

    @ManyToOne
    @JoinColumn(name = "f_limit_id", referencedColumnName = "id")
    public FrequencyLimitEntity getFrequencyLimitByFLimitId() {
        return frequencyLimitByFLimitId;
    }

    public void setFrequencyLimitByFLimitId(FrequencyLimitEntity frequencyLimitByFLimitId) {
        this.frequencyLimitByFLimitId = frequencyLimitByFLimitId;
    }

    @ManyToOne
    @JoinColumn(name = "setting_id", referencedColumnName = "id")
    public RewardSettingEntity getRewardSettingBySettingId() {
        return rewardSettingBySettingId;
    }

    public void setRewardSettingBySettingId(RewardSettingEntity rewardSettingBySettingId) {
        this.rewardSettingBySettingId = rewardSettingBySettingId;
    }
}
