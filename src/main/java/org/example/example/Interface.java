package org.example.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interface();
            }
        });
    }

    Interface() {
        //ОСНОВНОЕ ОКНО////////////////////////////////////////////////////
        JFrame frame = new JFrame("Основное окно");
        frame.setSize(240, 500);
        frame.setResizable(false);
        frame.setLocation(0/*(int) screenSize().getHeight() * 1/ 100*/, (int) screenSize().getWidth() * 25 / 100);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //ПАНЕЛЬ
        JPanel jpn = new JPanel();

        JLabel lbl = new JLabel("Первая прога");

        jpn.add(lbl);

        //ПАНЕЛЬ 2
        JPanel jpn2 = new JPanel();

        JLabel lbl2 = new JLabel("Текущий бонус: ");
        JButton btn = new JButton("0");

        jpn2.add(lbl2);
        jpn2.add(btn);

        //ДОБАВЛЕНИЕ ПАНЕЛЕЙ
        frame.add(jpn, BorderLayout.NORTH);
        frame.add(jpn2, BorderLayout.CENTER);

        //ОКНО БОНУСОВ//////////////////////////////////////////////////////
        JFrame secondFrame = new JFrame("Окно бонусов");
        secondFrame.setSize(250, 300);
        secondFrame.setResizable(false);
        secondFrame.setLocationRelativeTo(null);
        secondFrame.setLayout(new BorderLayout());
        secondFrame.setUndecorated(false);

        //ПАНЕЛЬ 1
        JPanel jppF1 = new JPanel(new GridLayout(3, 2, 3, 5));

        //ПАНЕЛЬ 2
        JPanel jppF2 = new JPanel(new GridLayout(5, 2));

        JLabel lblpF3 = new JLabel("Тип объекта");
        jppF2.add(lblpF3);

        String[] getUnitNames = {"Тип1","Тип2","Тип3","Тип4","Тип5"};
        JComboBox jcbpF = new JComboBox<>(getUnitNames);
        jppF2.add(jcbpF);

        JLabel lblpF4 = new JLabel("Количество");
        jppF2.add(lblpF4);

        JTextField tfpF = new JTextField(15);
        jppF2.add(tfpF);
        jppF2.add(new JLabel());

        JButton btnpF = new JButton("Добавить");
        jppF2.add(btnpF);

        //ДОБАВЛЕНИЕ ПАНЕЛЕЙ
        secondFrame.add(jppF1, BorderLayout.NORTH);
        secondFrame.add(jppF2, BorderLayout.SOUTH);
        ///////////////К Н О П К И ////////////////////////////////////////////
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!secondFrame.isVisible()) {
                    secondFrame.setVisible(true);
                }
            }
        });
        btnpF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel lblpF1 = new JLabel("");
                if (lblpF1.getText().equals(jcbpF.getSelectedItem())) {
                    lblpF1.setText("asdasdas");
                } else {
                    jppF1.add(lblpF1);
                    lblpF1.setText("" + jcbpF.getSelectedItem() + " (" + tfpF.getText() + ")");
                    new GroupLayout(jppF1);
                    jppF1.setLayout(new GridLayout(3, 2, 3, 5));
                }
            }
        });

    }

    private static Dimension screenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize;
    }


}
