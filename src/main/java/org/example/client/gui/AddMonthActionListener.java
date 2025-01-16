package org.example.client.gui;

import org.example.client.service.MonthService;
import org.example.model.Month;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddMonthActionListener implements ActionListener{
    private DefaultListModel<Month> monthListModel;
    private MonthService service;
    private JTextField inputName;
    private JTextField inputSeason;


    public AddMonthActionListener(DefaultListModel<Month> monthListModel, MonthService service, JTextField inputName, JTextField inputSeason) {
        this.monthListModel = monthListModel;
        this.service = service;
        this.inputName = inputName;
        this.inputSeason = inputSeason;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = inputName.getText().trim();
        String season = inputSeason.getText().trim();

        if (!name.isEmpty() && !season.isEmpty()) {
            try {
                Long id =monthListModel.get(monthListModel.size() - 1).getId() +1L;
                Month newMonth = new Month(id, name, season);
                Month savedMonth = service.save(newMonth); // Сохраняем через сервис
                monthListModel.addElement(savedMonth); // Обновляем модель данных
                inputName.setText("");
                inputSeason.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ошибка добавления месяца: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Введите название и сезон!");
        }
    }

}
