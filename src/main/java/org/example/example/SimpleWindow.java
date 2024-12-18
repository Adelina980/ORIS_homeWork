package org.example.example;

import javax.swing.*;

public class SimpleWindow extends JFrame {

    public SimpleWindow() {
        super();
        // Поведение программы при закрытии окна: прекращает работу при закрытии окна
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Задаем размер окна
        this.setSize(600, 600);
        // Заголовок окна
        this.setTitle("Simple Window");
        // Показываем окно
        this.setVisible(true);
    }

    public static void main(String[] args) {
        // Формируем окно и его компоненты в потоке рассылки сообщений (с момощью invokeLater)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() { new SimpleWindow(); } });
    }
}
