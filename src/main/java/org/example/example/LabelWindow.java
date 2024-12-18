package org.example.example;

import javax.swing.*;
import java.awt.*;

public class LabelWindow  extends JFrame{

    private JLabel email;
    private JLabel password;

    public LabelWindow() {
        super("Label");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 600);

         email= new JLabel("Ведите почту");
        password = new JLabel("Ведите пароль");

        this.add(email, BorderLayout.NORTH);
        this.add(password, BorderLayout.CENTER);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LabelWindow();
            }
        });
    }
}
