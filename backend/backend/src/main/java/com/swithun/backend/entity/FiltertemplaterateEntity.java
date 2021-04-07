package com.swithun.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "filtertemplaterate", schema = "time_management", catalog = "")
public class FiltertemplaterateEntity {
    private int id;
    private int root;
    private String type;
    private String operator;
    private String rate;
    private FiltertemplateEntity filtertemplateByRoot;

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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "rate")
    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiltertemplaterateEntity that = (FiltertemplaterateEntity) o;

        if (id != that.id) return false;
        if (root != that.root) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;
        if (rate != null ? !rate.equals(that.rate) : that.rate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + root;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "root", referencedColumnName = "id", nullable = false)
    public FiltertemplateEntity getFiltertemplateByRoot() {
        return filtertemplateByRoot;
    }

    public void setFiltertemplateByRoot(FiltertemplateEntity filtertemplateByRoot) {
        this.filtertemplateByRoot = filtertemplateByRoot;
    }
}
