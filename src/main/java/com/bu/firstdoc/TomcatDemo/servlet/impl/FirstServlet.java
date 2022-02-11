package com.bu.firstdoc.TomcatDemo.servlet.impl;

import com.bu.firstdoc.TomcatDemo.request.Request;
import com.bu.firstdoc.TomcatDemo.response.Response;
import com.bu.firstdoc.TomcatDemo.servlet.Servlet;

import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 5:10 下午 2022/2/7
 * @mark FirstServlet
 */
public class FirstServlet implements Servlet {


    @Override
    public void doGet(Request request, Response response) throws IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(Request request, Response response) throws IOException {
        response.write("this is first");
    }
}
