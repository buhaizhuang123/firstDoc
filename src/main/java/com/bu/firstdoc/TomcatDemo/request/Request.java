package com.bu.firstdoc.TomcatDemo.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * @author haizhuangbu
 * @date 4:58 下午 2022/2/7
 * @mark Request
 */
@Data
@Slf4j
public class Request {

    private String method;

    private String url;

    public Request(InputStream inputStream) {
        try {
            // 获取http 内容
            String content = "";

            byte[] buffer = new byte[1024];

            int len = 0;
            while ((len = inputStream.read(buffer)) > 0) {
                content = new String(buffer, 0, len);
            }
            // 获取第一行数据 请求头信息
            String line = content.split("\\n")[0];

            String[] arr = line.split("\\s");


            // 请求方法
            this.method = arr[0];
            // 请求路径
            this.url = arr[1].split("\\?")[0];

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
