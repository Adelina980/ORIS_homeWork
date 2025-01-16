package org.example.client.gui;

import org.example.client.service.MonthService;
import org.example.model.Month;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindMonthActionListener implements ActionListener {
    private JTextField inputId;
    private MonthService service;
    private JLabel resultLabel;

    public FindMonthActionListener(JTextField inputId, MonthService service, JLabel resultLabel) {
        this.inputId = inputId;
        this.service = service;
        this.resultLabel = resultLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Long id = Long.parseLong(inputId.getText());
            Month month = service.findById(id);
            if (month != null) {
                resultLabel.setText("ID: " + month.getId() +
                        ", Название: " + month.getName() +
                        ", Сезон: " + month.getSeason());
            } else {
                resultLabel.setText("Месяц с ID " + id + " не найден.");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Введите корректный числовой ID.");
        } catch (Exception ex) {
            resultLabel.setText("Введите корректный числовой ID.");
            ex.printStackTrace();
        }
    }
}

