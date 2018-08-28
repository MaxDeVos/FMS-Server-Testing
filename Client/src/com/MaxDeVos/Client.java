package com.MaxDeVos;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    Scanner scanner;
    Socket socket;

    public Client() throws IOException {

        socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 90);
        scanner = new Scanner(System.in);
    }

    public void start() throws IOException {

        String input;

        while(true){
            input = scanner.nextLine();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(input);
            out.flush();
        }

    }

    public static void main(String[] args) throws IOException {
        Client socket = new Client();
        socket.start();
    }
}
