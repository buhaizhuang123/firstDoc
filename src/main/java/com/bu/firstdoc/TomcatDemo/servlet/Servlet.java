package com.bu.firstdoc.TomcatDemo.servlet;

import com.bu.firstdoc.TomcatDemo.request.Request;
import com.bu.firstdoc.TomcatDemo.response.Response;

import java.io.IOException;

public interface Servlet {

    String METHOD = "GET";

    default void service(Request request, Response response) throws IOException {
        if (METHOD.equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    void doGet(Request request, Response response) throws IOException;

    void doPost(Request request, Response response) throws IOException;

}
