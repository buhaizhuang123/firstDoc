package com.bu.firstdoc.TomcatDemo.tomcat;

import com.bu.firstdoc.TomcatDemo.request.Request;
import com.bu.firstdoc.TomcatDemo.response.Response;
import com.bu.firstdoc.TomcatDemo.servlet.Servlet;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author haizhuangbu
 * @date 5:16 下午 2022/2/7
 * @mark Tomcat
 */
@Slf4j
public class Tomcat {

    private int port = 8080;

    private ServerSocket serverSocket;

    private Map<String, Servlet> servletMap = new HashMap<>();


    private Properties webXml = new Properties();


    private void init() {

        try {
            // 当前根路径
            String WEB_INFO = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INFO + "web.properties");

            webXml.load(fis);

            for (Object o : webXml.keySet()) {
                String key = o.toString();

                if (key.endsWith(".url")) {

                    String servletName = key.replaceAll("\\.url$", "");

                    String url = webXml.getProperty(key);
                    String className = webXml.getProperty(servletName + ".className");

                    Servlet servlet = (Servlet) Class.forName(className).newInstance();

                    servletMap.put(url, servlet);

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    public void start() {

        // 初始化
        init();
        log.info("初始化完成");
        try {

            serverSocket = new ServerSocket(this.port);

            log.info("Tomcat 已经启动 , 监听端口为: {}", this.port);

            while (true) {
                Socket socket = serverSocket.accept();

                proccess(socket);

            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void proccess(Socket socket) throws IOException {

        InputStream inputStream = socket.getInputStream();

        OutputStream outputStream = socket.getOutputStream();

        Request request = new Request(inputStream);

        Response response = new Response(outputStream);

        String url = request.getUrl();

        if (servletMap.containsKey(url)) {
            Servlet servlet = servletMap.get(url);
            servlet.service(request, response);
        } else {
            response.write("404 - Not Found");
        }

        outputStream.flush();
        outputStream.close();

        inputStream.close();

        socket.close();


    }


}
