package org.example.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonthRepository {

    private static MonthRepository _instance;

    public synchronized static MonthRepository getInstance() throws SQLException {
        if (_instance == null) {
            synchronized (MonthRepository.class) {
                if (_instance == null) {
                    _instance = new MonthRepository();
                }
            }
        }
        return _instance;
    }
    private final List<Month> monthList;

    private MonthRepository() {
        monthList = new ArrayList<>(Arrays.asList(
                new Month(1L, "Январь", "Зима"),
                new Month(2L, "Февраль", "Зима"),
                new Month(3L, "Март", "Весна"),
                new Month(4L, "Апрель", "Весна"),
                new Month(5L, "Май", "Весна"),
                new Month(6L, "Июнь", "Лето"),
                new Month(7L, "Июль", "Лето"),
                new Month(8L, "Август", "Лето"),
                new Month(9L, "Сентябрь", "Осень"),
                new Month(10L, "Октябрь", "Осень"),
                new Month(11L, "Ноябрь", "Осень"),
                new Month(12L, "Декабрь", "Зима")
        ));
    }


    public List<Month> getMonthList() {
        return monthList;
    }

    public synchronized void addMonth(Month month) {
        monthList.add(month);
    }
    public synchronized boolean deleteMonthById(Long id) {
        Month monthToRemove = null;

        // Найти месяц с указанным ID
        for (Month month : monthList) {
            if (month.getId().equals(id)) {
                monthToRemove = month;
                break;
            }
        }

        // Удалить найденный месяц, если он существует
        if (monthToRemove != null) {
            return monthList.remove(monthToRemove);
        }

        return false; // Если месяц с таким ID не найден
    }
}
