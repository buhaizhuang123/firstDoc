package com.bu.firstdoc.ftp;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author haizhuangbu
 * @date 3:16 下午 2022/1/18
 * @mark FtpClient
 */
public class FtpClient {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8081);

        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        bufferedReader.lines().forEach(System.out::println);

        IOUtils.copy(inputStream, socket.getOutputStream());

        inputStream.close();


        bufferedReader.close();


    }
}
