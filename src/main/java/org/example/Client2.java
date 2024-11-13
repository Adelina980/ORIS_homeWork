package org.example;

import java.io.*;
import java.net.Socket;

public class Client2 {
    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try {
                clientSocket = new Socket("127.0.0.1", 50001);
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                while (true) {
                    System.out.println("Задайте свой вопрос (или введите 'Exit' для выхода):");
                    String question = reader.readLine();
                    if (question.equals("Exit")) {
                        out.write("Exit\n");
                        out.flush();
                        break;
                    }
                    out.write(question + "\n");
                    out.flush();
                    String answer = in.readLine();
                    System.out.println("Ответ: "+answer);
                }
            } finally {
                System.out.println("Клиент был закрыт");
                clientSocket.close();
                in.close();
                out.close();
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
