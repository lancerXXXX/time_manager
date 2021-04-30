package com.swithun.backend.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "statisticTemplate", schema = "time_manger", catalog = "")
public class StatisticTemplateEntity {
    public StatisticTemplateEntity(Integer id) {
        this.id = id;
    }

    public StatisticTemplateEntity() {
    }

    public StatisticTemplateEntity(String templeteName) {
        this.templeteName = templeteName;
    }

    private Integer id;
    private String templeteName;
    private Collection<StDevotionEntity> stDevotionsById;
    private Collection<StNameEntity> stNamesById;
    private Collection<StSatisfactionEntity> stSatisfactionsById;
    private Collection<StTimeEntity> stTimesById;
    private Collection<StTypeEntity> stTypesById;

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
    @Column(name = "templete_name")
    public String getTempleteName() {
        return templeteName;
    }

    public void setTempleteName(String templeteName) {
        this.templeteName = templeteName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticTemplateEntity that = (StatisticTemplateEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(templeteName, that.templeteName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, templeteName);
    }

    @OneToMany(mappedBy = "statisticTemplateByStId", cascade = CascadeType.ALL)
    public Collection<StDevotionEntity> getStDevotionsById() {
        return stDevotionsById;
    }

    public void setStDevotionsById(Collection<StDevotionEntity> stDevotionsById) {
        this.stDevotionsById = stDevotionsById;
    }

    @OneToMany(mappedBy = "statisticTemplateByStId", cascade = CascadeType.ALL)
    public Collection<StNameEntity> getStNamesById() {
        return stNamesById;
    }
/*  */
    public void setStNamesById(Collection<StNameEntity> stNamesById) {
        this.stNamesById = stNamesById;
    }

    @OneToMany(mappedBy = "statisticTemplateByStId", cascade = CascadeType.ALL)
    public Collection<StSatisfactionEntity> getStSatisfactionsById() {
        return stSatisfactionsById;
    }

    public void setStSatisfactionsById(Collection<StSatisfactionEntity> stSatisfactionsById) {
        this.stSatisfactionsById = stSatisfactionsById;
    }

    @OneToMany(mappedBy = "statisticTemplateByStId", cascade = CascadeType.ALL)
    public Collection<StTimeEntity> getStTimesById() {
        return stTimesById;
    }

    public void setStTimesById(Collection<StTimeEntity> stTimesById) {
        this.stTimesById = stTimesById;
    }

    @OneToMany(mappedBy = "statisticTemplateByStId", cascade = CascadeType.ALL)
    public Collection<StTypeEntity> getStTypesById() {
        return stTypesById;
    }

    public void setStTypesById(Collection<StTypeEntity> stTypesById) {
        this.stTypesById = stTypesById;
    }
}
