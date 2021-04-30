/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 16:39:27
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 16:41:20
 */
package com.swithun.backend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

@Entity
@Table(name = "stType", schema = "time_manger", catalog = "")
public class StTypeEntity {
    private Integer id;
    private Boolean notType;
    private Integer planType;
    @JsonIgnore
    private StatisticTemplateEntity statisticTemplateByStId;

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
    @Column(name = "not_type")
    public Boolean getNotType() {
        return notType;
    }

    public void setNotType(Boolean notType) {
        this.notType = notType;
    }

    @Basic
    @Column(name = "plan_type")
    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StTypeEntity that = (StTypeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(notType, that.notType) && Objects.equals(planType, that.planType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notType, planType);
    }

    @ManyToOne
    @JoinColumn(name = "st_id", referencedColumnName = "id")
    public StatisticTemplateEntity getStatisticTemplateByStId() {
        return statisticTemplateByStId;
    }

    public void setStatisticTemplateByStId(StatisticTemplateEntity statisticTemplateByStId) {
        this.statisticTemplateByStId = statisticTemplateByStId;
    }
}
