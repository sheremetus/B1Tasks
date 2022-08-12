package com.kirillsheremet.b1task2.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "money_turnover", schema = "b2_second_task_schema", catalog = "")
@IdClass(MoneyTurnoverEntityPK.class)
public class MoneyTurnoverEntity {
    private BigDecimal debit;
    private BigDecimal credit;
    private int id;
    private int classesIdclasses;
    private int accountsIdaccounts;

    @Basic
    @Column(name = "debit", nullable = false, precision = 2)
    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    @Basic
    @Column(name = "credit", nullable = false, precision = 2)
    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "classes_idclasses", nullable = false)
    public int getClassesIdclasses() {
        return classesIdclasses;
    }

    public void setClassesIdclasses(int classesIdclasses) {
        this.classesIdclasses = classesIdclasses;
    }

    @Id
    @Column(name = "accounts_idaccounts", nullable = false)
    public int getAccountsIdaccounts() {
        return accountsIdaccounts;
    }

    public void setAccountsIdaccounts(int accountsIdaccounts) {
        this.accountsIdaccounts = accountsIdaccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyTurnoverEntity that = (MoneyTurnoverEntity) o;
        return id == that.id && classesIdclasses == that.classesIdclasses && accountsIdaccounts == that.accountsIdaccounts && Objects.equals(debit, that.debit) && Objects.equals(credit, that.credit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(debit, credit, id, classesIdclasses, accountsIdaccounts);
    }
}
