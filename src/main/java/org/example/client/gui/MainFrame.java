package org.example.client.gui;

import org.example.client.service.MonthService;
import org.example.model.Month;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private MonthService service = new MonthService();
    private DefaultListModel<Month> monthListModel;


    public MainFrame() {
        super("12 месяцев");

        setLayout(new FlowLayout());
        setSize(400, 400);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Получаем список всех месяцев через сервис, и создаем модель данных для списка
        monthListModel = new DefaultListModel<>();

        try {
            for (Month month : service.findAll()) {
                monthListModel.addElement(month);
            }
        } catch (Exception e) {
            monthListModel.addElement(new Month(0L, "Ошибка", "Не удалось загрузить данные"));
        }

        JList<Month> listMonth = new JList<>(monthListModel);
        listMonth.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(listMonth), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());
        JPanel controlPanel1 = new JPanel(new FlowLayout());
        JTextField inputName = new JTextField(10);
        JTextField inputSeason = new JTextField(10);
        JButton addButton = new JButton("Добавить");
        JButton deleteButton = new JButton("Удалить");

        JTextField inputId = new JTextField(5);
        JButton findButton = new JButton("Найти по ID");
        JLabel resultLabel = new JLabel("");


        controlPanel.add(new JLabel("Название:"));
        controlPanel.add(inputName);
        controlPanel.add(new JLabel("Сезон:"));
        controlPanel.add(inputSeason);
        controlPanel.add(addButton);
        controlPanel.add(deleteButton);

        controlPanel1.add(new JLabel("ID:"));
        controlPanel1.add(inputId);
        controlPanel1.add(findButton);
        controlPanel1.add(resultLabel);
        add(controlPanel, BorderLayout.SOUTH);
        add(controlPanel1, BorderLayout.SOUTH);

        addButton.addActionListener(new AddMonthActionListener(monthListModel, service, inputName, inputSeason));
        deleteButton.addActionListener(new DeleteMonthActionListener(service, monthListModel, listMonth));
        findButton.addActionListener(new FindMonthActionListener(inputId, service, resultLabel));

        setVisible(true);
    }

}
