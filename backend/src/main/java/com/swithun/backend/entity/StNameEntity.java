/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-05 21:15:43
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-10 10:03:04
 */
package com.swithun.backend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

@Entity
@Table(name = "stName", schema = "time_manger", catalog = "")
public class StNameEntity {
    private Integer id;
    private String name;
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
    @Column(name = "name")
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
        StNameEntity that = (StNameEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
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
