package org.example;

import java.io.*;
import java.net.Socket;


public class ClientHandler implements Runnable {

    private Socket clientSocket;
    public static final String ROOT_DIRECTORY = "C:/Users/pro/IdeaProjects/serverWithHeaders/";
    private static final String DEFAULT_HTML = "<html><body><h1>Hello, World!</h1></body></html>";

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = clientSocket.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String headerLine = bufferedReader.readLine();

            String[] firstLine = headerLine.split("\\s+");

            String method = firstLine[0];
            String uri = firstLine[1].substring(1);
            String httpVers = firstLine[2];

            System.out.println(method + " " + uri + " " + httpVers);

            StringBuilder requestHeaders = new StringBuilder();
            while ((headerLine= bufferedReader.readLine()) != null && !headerLine.equals("") && !headerLine.isEmpty()) {
                requestHeaders.append(headerLine).append("\r\n");
                System.out.println(headerLine);
            }

            if (!httpVers.equals("HTTP/1.1")) {
                String[] response = {"HTTP/1.1 505 HTTP Version Not Supported\r\n","Server: Adelina\r\n","\r\n"};

                for (String responseHeaderLine : response) {
                    clientSocket.getOutputStream().write(responseHeaderLine.getBytes());
                    clientSocket.getOutputStream().flush();
                }
                clientSocket.close();
                return;
            } if (method.equals("GET")){
                File file = new File(ROOT_DIRECTORY + uri);
                if(uri.isEmpty()){
                    byte[] body = DEFAULT_HTML.getBytes();
                    String headers = "Content-Length: " + body.length + "\r\n" +
                            "Content-Type: text/html; charset=utf-8\r\n" + "\r\n";
                    clientSocket.getOutputStream().write(("HTTP/1.1 200 OK\r\n" + "Server: Adelina\r\n" + headers).getBytes());
                    clientSocket.getOutputStream().write(body);
                    clientSocket.getOutputStream().flush();
                }else if (!file.exists()) {
                    String[] response = {"HTTP/1.1 404 Not Found\r\n", "Server: Adelina\r\n", "\r\n"};

                    for (String responseHeaderLine : response) {
                        clientSocket.getOutputStream().write(responseHeaderLine.getBytes());
                        clientSocket.getOutputStream().flush();
                    }
                    clientSocket.close();
                } else {
                    try (FileInputStream fis = new FileInputStream(file)) {

                        byte[] body = fis.readAllBytes();
                        String[] response = {
                                "HTTP/1.1 200 OK\r\n",
                                "Server: Adelina\r\n",
                                "Content-Type: text/html; charset=utf-8\r\n",
                                "Content-Length: " + body.length + "\r\n",
                                "\r\n"};

                        for (String responseHeaderLine : response) {
                            clientSocket.getOutputStream().write(responseHeaderLine.getBytes());
                            clientSocket.getOutputStream().flush();
                        }

                        clientSocket.getOutputStream().write(body);
                        clientSocket.getOutputStream().flush();

                        clientSocket.close();
                    } catch (IOException e) {
                        String[] response = {"HTTP/1.1 500 Internal Server Error\r\n", "Server: Adelina\r\n", "\r\n"};

                        for (String responseHeaderLine : response) {
                            clientSocket.getOutputStream().write(responseHeaderLine.getBytes());
                            clientSocket.getOutputStream().flush();
                        }
                        clientSocket.close();
                    }
                }
            }else if (method.equals("POST")){
                int contentLength = getContentLength(requestHeaders.toString());
                char[] body = new char[contentLength];
                bufferedReader.read(body, 0, contentLength);
                String requestBody = new String(body);

                System.out.println("Тело POST-запроса:" + requestBody);
                String[] response = {
                        "HTTP/1.1 200 OK\r\n",
                        "Server: Adelina\r\n",
//                        "Content-Type: text/html; charset=utf-8\r\n",
//                        "Content-Length: " + requestBody.length() + "\r\n",
                        "\r\n"};
                for (String responseHeaderLine : response) {
                    clientSocket.getOutputStream().write(responseHeaderLine.getBytes());
                    clientSocket.getOutputStream().flush();
                }
                clientSocket.close();
            }else {
                String[] response = {"HTTP/1.1 405 Method Not Allowed\r\n", "Server: Adelina\r\n", "\r\n"};

                for (String responseHeaderLine : response) {
                    clientSocket.getOutputStream().write(responseHeaderLine.getBytes());
                    clientSocket.getOutputStream().flush();
                }
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static int getContentLength(String headers) {
        for (String header : headers.split("\r\n")) {
            if (header.startsWith("Content-Length:")) {
                return Integer.parseInt(header.split(":")[1].trim());
            }
        }
        return 0;
    }
}
