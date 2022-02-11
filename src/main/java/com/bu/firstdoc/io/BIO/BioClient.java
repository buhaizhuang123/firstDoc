package com.bu.firstdoc.io.BIO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author haizhuangbu
 * @date 3:35 下午 2022/1/20
 * @mark BioClient
 */
public class BioClient {

    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket("localhost", 8081);
            outputStream = socket.getOutputStream();
            Scanner sc = new Scanner(System.in);

            System.out.println("===== 请输入内容 =========");
            String data = sc.nextLine();
            outputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
