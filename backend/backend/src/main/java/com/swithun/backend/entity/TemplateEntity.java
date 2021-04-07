package com.swithun.backend.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "template", schema = "time_management", catalog = "")
public class TemplateEntity {
    private int id;
    private byte type;
    private String icon;
    private String name;
    private int taskType;
    private byte hasSubTasks;
    private PlanitemtypeEntity planitemtypeByTaskType;
    private Collection<TemplatechildplanitemEntity> templatechildplanitemsById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
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
    @Column(name = "task_type")
    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    @Basic
    @Column(name = "has_sub_tasks")
    public byte getHasSubTasks() {
        return hasSubTasks;
    }

    public void setHasSubTasks(byte hasSubTasks) {
        this.hasSubTasks = hasSubTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TemplateEntity that = (TemplateEntity) o;

        if (id != that.id) return false;
        if (type != that.type) return false;
        if (taskType != that.taskType) return false;
        if (hasSubTasks != that.hasSubTasks) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) type;
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + taskType;
        result = 31 * result + (int) hasSubTasks;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "task_type", referencedColumnName = "id", nullable = false)
    public PlanitemtypeEntity getPlanitemtypeByTaskType() {
        return planitemtypeByTaskType;
    }

    public void setPlanitemtypeByTaskType(PlanitemtypeEntity planitemtypeByTaskType) {
        this.planitemtypeByTaskType = planitemtypeByTaskType;
    }

    @OneToMany(mappedBy = "templateByRoot")
    public Collection<TemplatechildplanitemEntity> getTemplatechildplanitemsById() {
        return templatechildplanitemsById;
    }

    public void setTemplatechildplanitemsById(Collection<TemplatechildplanitemEntity> templatechildplanitemsById) {
        this.templatechildplanitemsById = templatechildplanitemsById;
    }
}
