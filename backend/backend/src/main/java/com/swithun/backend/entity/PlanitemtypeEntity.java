package com.swithun.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "planitemtype", schema = "time_management", catalog = "")
public class PlanitemtypeEntity {
    private int id;
    private byte isRoot;
    private String name;
    private Integer pre;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "is_root")
    public byte getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(byte isRoot) {
        this.isRoot = isRoot;
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
    @Column(name = "pre")
    public Integer getPre() {
        return pre;
    }

    public void setPre(Integer pre) {
        this.pre = pre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanitemtypeEntity that = (PlanitemtypeEntity) o;

        if (id != that.id) return false;
        if (isRoot != that.isRoot) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pre != null ? !pre.equals(that.pre) : that.pre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) isRoot;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pre != null ? pre.hashCode() : 0);
        return result;
    }
}
