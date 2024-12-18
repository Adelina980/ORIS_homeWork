package org.example;

import org.example.example.button.ButtonActionListener;
import org.example.model.User;
import org.example.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextFieldWindow extends JFrame {

    private UserService service = new UserService();

    private JTextField emailText;
    private JTextField passwordText;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton helpButton;

    public TextFieldWindow() {
        super("TextField");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 600);

        ImageIcon image = new ImageIcon("favico.png");

        JPanel panel = new JPanel();
        panel.setLayout(null);


        emailLabel = new JLabel("Почта:");
        emailLabel.setBounds(20, 20, 70, 30);
        emailText = new JTextField();
        emailText.setBounds(100, 20, 200, 30);

        passwordLabel = new JLabel("Пароль:");
        passwordLabel.setBounds(20, 60, 70, 30);
        passwordText = new JTextField();
        passwordText.setBounds(100, 60, 200, 30);


        saveButton = new JButton("Сохранить");
        saveButton.setBounds(20, 100, 100, 30);
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon image = new ImageIcon("favico.png");

                if (emailText.getText() != "" && !emailText.getText().isEmpty()){
                    User user = new User(emailText.getText(), passwordText.getText());
                    service.save(user);
                    JOptionPane.showMessageDialog(
                            null,
                            "Сохранение пользователя прошло успешно", "Сохраниение",
                            JOptionPane.INFORMATION_MESSAGE, image);

                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Неправильно введены почта или пароль", "Ошибка",
                            JOptionPane.INFORMATION_MESSAGE, image);
                }
            }
        });

        cancelButton = new JButton("Отменить");
        cancelButton.setBounds(130, 100, 100, 30);
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                emailText.setText("");
                passwordText.setText("");

            }
        });

        helpButton = new JButton("Справка");
        helpButton.setBounds(240, 100, 100, 30);
        helpButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Форма ввод данных почты и пароля", "Справочная",
                        JOptionPane.INFORMATION_MESSAGE, image);
            }

        });


        panel.add(emailLabel);
        panel.add(emailText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(saveButton);
        panel.add(cancelButton);
        panel.add(helpButton);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextFieldWindow();
            }
        });
    }
}

