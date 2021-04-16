/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-13 16:58:03
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-16 14:55:33
 */
package com.swithun.backend.entity;

import javax.persistence.*;


import java.util.Objects;

@Entity
@Table(name = "finishedPlan", schema = "time_manger", catalog = "")
public class FinishedPlanEntity {
    private Integer id;
    // @JsonManagedReference(value = "test3")
    private PlanEntity planByPlanId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinishedPlanEntity that = (FinishedPlanEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    public PlanEntity getPlanByPlanId() {
        return planByPlanId;
    }

    public void setPlanByPlanId(PlanEntity planByPlanId) {
        this.planByPlanId = planByPlanId;
    }
}
