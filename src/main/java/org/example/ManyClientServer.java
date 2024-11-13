package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ManyClientServer {
    public static final int SERVER_PORT = 50001;
//    public static LinkedList<ClientHandler> serverList = new LinkedList<>();

    public static void main (String[] args){
        try (ServerSocket server = new ServerSocket(SERVER_PORT);){
            System.out.println("start server");
            while (true) {
                // wait client connection
                //System.out.println("accept");
                System.out.println("wait client connection");

                Socket clientSocket = server.accept();
                ClientHandler handler = new ClientHandler(clientSocket);
//                serverList.add(handler);


                new Thread(handler).start();
            }
            //server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
