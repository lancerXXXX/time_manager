/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-20 14:09:15
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-20 16:32:01
 */
package com.swithun.backend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "planType", schema = "time_manger", catalog = "")
public class PlanTypeEntity {
    private Integer id;
    private String name;
    @JsonIgnore
    private PlanTypeEntity planTypeByParentId;
    private Collection<PlanTypeEntity> planTypesById;

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
    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanTypeEntity that = (PlanTypeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public PlanTypeEntity getPlanTypeByParentId() {
        return planTypeByParentId;
    }

    public void setPlanTypeByParentId(PlanTypeEntity planTypeByParentId) {
        this.planTypeByParentId = planTypeByParentId;
    }

    @OneToMany(mappedBy = "planTypeByParentId", fetch = FetchType.LAZY)
    @JsonProperty("children")
    public Collection<PlanTypeEntity> getPlanTypesById() {
        return planTypesById;
    }

    public void setPlanTypesById(Collection<PlanTypeEntity> planTypesById) {
        this.planTypesById = planTypesById;
    }
}
