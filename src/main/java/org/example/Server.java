package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static final int SERVER_ROOT = 50001;
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {

        try {
            try {
                server = new ServerSocket(SERVER_ROOT);
                System.out.println("Сервер запущен");
                clientSocket = server.accept();
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                    String headerLine = "-";
//                    while (headerLine != null && !headerLine.equals("")) {
//                        headerLine = in.readLine();
//                        System.out.println(headerLine);
//                    }
//                    String[] response = {"HTTP/1.1 200 OK\r\n", "Server: Adelina …\\r\n", "\r\n"};
//                    for (String h : response) {
//                        clientSocket.getOutputStream().write(h.getBytes());
//                    }
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    String question = in.readLine();
                    System.out.println(question);
                    out.write("Отвечая на этот вопрос, я думаю, вы правы\n");
                    out.flush();

            } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт");
                server.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
