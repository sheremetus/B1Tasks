package com.kirillsheremet.b1task2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "classes", schema = "b2_second_task_schema", catalog = "")
public class ClassesEntity {
    private int idclasses;

    @Id
    @Column(name = "idclasses", nullable = false)
    public int getIdclasses() {
        return idclasses;
    }

    public void setIdclasses(int idclasses) {
        this.idclasses = idclasses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassesEntity that = (ClassesEntity) o;
        return idclasses == that.idclasses;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idclasses);
    }
}
