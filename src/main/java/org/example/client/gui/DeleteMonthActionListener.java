package org.example.client.gui;

import org.example.client.service.MonthService;
import org.example.model.Month;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteMonthActionListener implements ActionListener {
    private MonthService service;
    private DefaultListModel<Month> monthListModel;
    private JList<Month> listMonth;

    public DeleteMonthActionListener(MonthService service, DefaultListModel<Month> monthListModel, JList<Month> listMonth) {
        this.service = service;
        this.monthListModel = monthListModel;
        this.listMonth = listMonth;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedIndex = listMonth.getSelectedIndex();
        if (selectedIndex >= 0) {
            Month selectedMonth = monthListModel.get(selectedIndex);
            try {
                service.delete(selectedMonth.getId()); // Удаляем через сервис
                monthListModel.remove(selectedIndex); // Удаляем из модели данных
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ошибка удаления месяца: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Выберите месяц для удаления!");
        }
    }
}

