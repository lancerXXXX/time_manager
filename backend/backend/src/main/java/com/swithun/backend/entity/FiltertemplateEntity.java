package com.swithun.backend.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "filtertemplate", schema = "time_management", catalog = "")
public class FiltertemplateEntity {
    private int id;
    private String name;
    private String taskName;
    private Collection<FiltertemplateplanitemtypeEntity> filtertemplateplanitemtypesById;
    private Collection<FiltertemplaterateEntity> filtertemplateratesById;
    private Collection<FiltertemplatetimeEntity> filtertemplatetimesById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Basic
    @Column(name = "task_name")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiltertemplateEntity that = (FiltertemplateEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "filtertemplateByRoot")
    public Collection<FiltertemplateplanitemtypeEntity> getFiltertemplateplanitemtypesById() {
        return filtertemplateplanitemtypesById;
    }

    public void setFiltertemplateplanitemtypesById(Collection<FiltertemplateplanitemtypeEntity> filtertemplateplanitemtypesById) {
        this.filtertemplateplanitemtypesById = filtertemplateplanitemtypesById;
    }

    @OneToMany(mappedBy = "filtertemplateByRoot")
    public Collection<FiltertemplaterateEntity> getFiltertemplateratesById() {
        return filtertemplateratesById;
    }

    public void setFiltertemplateratesById(Collection<FiltertemplaterateEntity> filtertemplateratesById) {
        this.filtertemplateratesById = filtertemplateratesById;
    }

    @OneToMany(mappedBy = "filtertemplateByFiltertTemplateId")
    public Collection<FiltertemplatetimeEntity> getFiltertemplatetimesById() {
        return filtertemplatetimesById;
    }

    public void setFiltertemplatetimesById(Collection<FiltertemplatetimeEntity> filtertemplatetimesById) {
        this.filtertemplatetimesById = filtertemplatetimesById;
    }
}
