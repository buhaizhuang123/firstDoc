package com.bu.firstdoc.ftp;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.Socket;

/**
 * @author haizhuangbu
 * @date 3:03 下午 2022/1/18
 * @mark FtpUtil
 */
public class FtpUtil {

    public static void main(String[] args) throws IOException {

        // 读取文件
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/Applications/tools/java/firstDoc/src/main/resources/ons.txt")));

        Socket serverSocket = new Socket("localhost", 8081);

        OutputStream outputStream = serverSocket.getOutputStream();

        PrintWriter printWriter = new PrintWriter(outputStream, true);

        IOUtils.copy(bufferedReader, printWriter);

        serverSocket.close();

    }


}
