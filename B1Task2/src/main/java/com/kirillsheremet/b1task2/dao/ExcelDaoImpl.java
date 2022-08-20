package com.kirillsheremet.b1task2.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExcelDaoImpl implements ExcelDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public <T> void save(T parameter) {
        Session session = sessionFactory.getCurrentSession();
        session.save(parameter);
    }
}
