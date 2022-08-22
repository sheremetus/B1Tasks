package com.kirillsheremet.b1task2.dao;
/**
 * Интерфейс определяющий метод сохранения для репозитория
 */
public interface ExcelDao {
    <T> void save(T parameter);
}
