package org.example;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable {

    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
            String question;
            while ((question = bufferedReader.readLine()) != null) {
                System.out.println(question);
                switch (question) {
                    case ("Как погода?"):
                        bufferedWriter.write("Снежно \n");
                        bufferedWriter.flush();
                        break;
                    case ("Как настроение?"):
                        bufferedWriter.write("Лучше некуда \n");
                        bufferedWriter.flush();
                        break;
                    case ("Какой сегодня день недели?"):
                        bufferedWriter.write("Суббота \n");
                        bufferedWriter.flush();
                        break;
                    case ("В каком году произошло крещение Руси?"):
                        bufferedWriter.write("в 988\n");
                        bufferedWriter.flush();
                        break;
                    case ("Как придумать последний вопрос?"):
                        bufferedWriter.write("Приложив все свои силы\n");
                        bufferedWriter.flush();
                        break;
                    default:
                        bufferedWriter.write("Задайте вопрос из перечня\n");
                        bufferedWriter.flush();
                        break;
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}