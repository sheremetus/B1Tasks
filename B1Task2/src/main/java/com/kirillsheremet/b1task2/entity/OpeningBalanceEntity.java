package com.kirillsheremet.b1task2.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "opening_balance", schema = "b2_second_task_schema", catalog = "")
@IdClass(OpeningBalanceEntityPK.class)
public class OpeningBalanceEntity {
    private int id;
    private BigDecimal assets;
    private BigDecimal liability;
    private int classesIdclasses;
    private int accountsIdaccounts;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "assets", nullable = false, precision = 10)
    public BigDecimal getAssets() {
        return assets;
    }

    public void setAssets(BigDecimal assets) {
        this.assets = assets;
    }

    @Basic
    @Column(name = "liability", nullable = false, precision = 10)
    public BigDecimal getLiability() {
        return liability;
    }

    public void setLiability(BigDecimal liability) {
        this.liability = liability;
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
        OpeningBalanceEntity that = (OpeningBalanceEntity) o;
        return id == that.id && classesIdclasses == that.classesIdclasses && accountsIdaccounts == that.accountsIdaccounts && Objects.equals(assets, that.assets) && Objects.equals(liability, that.liability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assets, liability, classesIdclasses, accountsIdaccounts);
    }
}
