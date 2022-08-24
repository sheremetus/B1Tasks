package com.kirillsheremet.b1task2.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий определяющий общий параметризированный метод для сохранения объектов
 */
@Repository
public class ExcelDaoImpl implements ExcelDao {
    // Внедряем интерфейс фабрики из которого будем получать сессию
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public <T> void save(T parameter) {
        Session session = sessionFactory.getCurrentSession();
        session.save(parameter);

    }
}
