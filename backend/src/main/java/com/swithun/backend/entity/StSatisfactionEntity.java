package com.swithun.backend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

@Entity
@Table(name = "stSatisfaction", schema = "time_manger", catalog = "")
public class StSatisfactionEntity {
    private Integer id;
    private Integer operator;
    private Integer level;
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
    @Column(name = "operator")
    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StSatisfactionEntity that = (StSatisfactionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(operator, that.operator) && Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operator, level);
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
