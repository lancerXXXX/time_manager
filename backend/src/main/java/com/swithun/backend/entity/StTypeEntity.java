/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 16:39:27
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-27 16:41:10
 */
package com.swithun.backend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swithun.backend.utils.ClassConvert;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stType", schema = "time_manger", catalog = "")
public class StTypeEntity {

    @Autowired
    ClassConvert convert;

    private Integer id;
    private Boolean notType;
    private Integer planType;
    @JsonIgnore
    private StatisticTemplateEntity statisticTemplateByStId;
    private List<Integer> typeList;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Transient
    public List<Integer> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Integer> typeList) {
        this.typeList = typeList;
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        StTypeEntity that = (StTypeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(notType, that.notType)
                && Objects.equals(planType, that.planType);
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

    public void type2TypeList() {
        setTypeList(convert.getTypeListByPlanTypeId(planType));
    }
}
