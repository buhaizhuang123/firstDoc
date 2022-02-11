package com.bu.firstdoc.io.BIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author haizhuangbu
 * @date 3:35 下午 2022/1/20
 * @mark BioServer
 */
public class BioServer {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        BufferedReader bufferedReader = null;
        try {
            serverSocket = new ServerSocket(8081);
            Socket socket = serverSocket.accept();

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (bufferedReader.read() != -1) {
                System.out.println("bufferedReader.readLine() = " + bufferedReader.readLine());
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
