package com.swithun.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "templatechildplanitem", schema = "time_management", catalog = "")
public class TemplatechildplanitemEntity {
    private int id;
    private int root;
    private String name;
    private Integer tomato;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "root")
    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
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
    @Column(name = "tomato")
    public Integer getTomato() {
        return tomato;
    }

    public void setTomato(Integer tomato) {
        this.tomato = tomato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TemplatechildplanitemEntity that = (TemplatechildplanitemEntity) o;

        if (id != that.id) return false;
        if (root != that.root) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (tomato != null ? !tomato.equals(that.tomato) : that.tomato != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + root;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tomato != null ? tomato.hashCode() : 0);
        return result;
    }
}
