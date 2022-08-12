package com.kirillsheremet.b1task2.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ClosingBalanceEntityPK implements Serializable {
    private int id;
    private int accountsIdaccounts;
    private int classesIdclasses;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "accounts_idaccounts", nullable = false)
    @Id
    public int getAccountsIdaccounts() {
        return accountsIdaccounts;
    }

    public void setAccountsIdaccounts(int accountsIdaccounts) {
        this.accountsIdaccounts = accountsIdaccounts;
    }

    @Column(name = "classes_idclasses", nullable = false)
    @Id
    public int getClassesIdclasses() {
        return classesIdclasses;
    }

    public void setClassesIdclasses(int classesIdclasses) {
        this.classesIdclasses = classesIdclasses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClosingBalanceEntityPK that = (ClosingBalanceEntityPK) o;
        return id == that.id && accountsIdaccounts == that.accountsIdaccounts && classesIdclasses == that.classesIdclasses;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountsIdaccounts, classesIdclasses);
    }
}
