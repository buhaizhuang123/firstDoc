package com.bu.firstdoc.TomcatDemo.response;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author haizhuangbu
 * @date 4:58 下午 2022/2/7
 * @mark Response
 */
public class Response {

    private OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(content);

        outputStream.write(stringBuilder.toString().getBytes());

    }


}
