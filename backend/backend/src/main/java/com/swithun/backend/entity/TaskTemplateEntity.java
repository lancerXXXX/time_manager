/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-15 18:37:22
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-16 09:57:13
 */
package com.swithun.backend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@Entity
@Table(name = "task_template", schema = "time_manger", catalog = "")
public class TaskTemplateEntity {
    private Integer id;
    private String icon;
    private String name;
    @JsonProperty("plan")
    private PlanEntity planByPlanId;
    // 0 custom
    // 1 common
    private Integer type;

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
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TaskTemplateEntity that = (TaskTemplateEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(icon, that.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, icon);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    public PlanEntity getPlanByPlanId() {
        return planByPlanId;
    }

    public void setPlanByPlanId(PlanEntity planByPlanId) {
        this.planByPlanId = planByPlanId;
    }
}
/*
 * @Descripttion:
 * 
 * @version:
 * 
 * @@Company: None
 * 
 * @Author: Swithun Liu
 * 
 * @Date: 2021-05-15 18:37:22
 * 
 * @LastEditors: Swithun Liu
 * 
 * @LastEditTime: 2021-05-15 18:37:24
 */
