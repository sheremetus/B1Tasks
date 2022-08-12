package com.kirillsheremet.b1task2.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "accounts", schema = "b2_second_task_schema", catalog = "")
public class AccountsEntity {
    private int idaccounts;

    @Id
    @Column(name = "idaccounts", nullable = false)
    public int getIdaccounts() {
        return idaccounts;
    }

    public void setIdaccounts(int idaccounts) {
        this.idaccounts = idaccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountsEntity accounts = (AccountsEntity) o;
        return idaccounts == accounts.idaccounts;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idaccounts);
    }
}
