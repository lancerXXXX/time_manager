/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-06 10:10:53
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-06 10:27:54
 */
package com.swithun.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "relation", schema = "time_manger", catalog = "")
public class RelationEntity {
    private Integer id;
    private PlanEntity planByPlanId;
    private PlanEntity planByPrePlanId;

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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RelationEntity that = (RelationEntity) o;
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

    @ManyToOne
    @JoinColumn(name = "pre_plan_id", referencedColumnName = "id")
    public PlanEntity getPlanByPrePlanId() {
        return planByPrePlanId;
    }

    public void setPlanByPrePlanId(PlanEntity planByPrePlanId) {
        this.planByPrePlanId = planByPrePlanId;
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
 * @Date: 2021-05-06 10:10:53
 * 
 * @LastEditors: Swithun Liu
 * 
 * @LastEditTime: 2021-05-06 10:11:29
 */
