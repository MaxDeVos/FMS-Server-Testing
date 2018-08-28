package com.MaxDeVos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

class Client {

    private String ip;
    private String name;
    private String UUID;
    private Socket socket_;
    private boolean hasDisconnected;

    Client(Socket socket){
        socket_ = socket;
        ip = socket.getInetAddress().getHostAddress();
        Long time = System.currentTimeMillis();
        UUID = socket_.getInetAddress().getHostName().replaceAll("[^A-Za-z0-9]", "")+ time;
        hasDisconnected = false;
        Thread n = new Thread(() -> {
            try {
                talk();
            }
            catch(SocketException f) {
                disconnect();
                System.out.println(UUID + " has Disconnected!");
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        });
        n.start();

    }

    String getIP(){
        return ip;
    }

    String getUUID(){
        return UUID;
    }

    boolean isConnected() throws IOException {
        return socket_.getInetAddress().isReachable(10);
    }

    boolean isHasDisconnected(){
        return hasDisconnected;
    }

    void disconnect(){
        hasDisconnected = true;
    }

    private void talk() throws IOException {
        System.out.println("Talk Thread Spawned!");
        String data;
        BufferedReader in = new BufferedReader(new InputStreamReader(socket_.getInputStream()));

        while (true) {
            if ((data = in.readLine()) != null) {
                System.out.println(UUID + ": " + data);
            }
        }
    }

}
