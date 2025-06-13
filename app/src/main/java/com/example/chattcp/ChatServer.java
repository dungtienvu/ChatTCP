package com.example.chattcp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            System.out.println("Server đang chạy, chờ client kết nối...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client đã kết nối: " + clientSocket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Client: " + message);
                // Gửi phản hồi lại cho client
                out.println("Server đã nhận: " + message);
            }

            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

