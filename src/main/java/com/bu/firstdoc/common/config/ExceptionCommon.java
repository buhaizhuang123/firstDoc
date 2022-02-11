package com.bu.firstdoc.common.config;

import com.alibaba.fastjson.JSON;
import com.bu.firstdoc.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author haizhuangbu
 * @date 1:46 下午 2022/1/14
 * @mark ExceptionCommon 全局异常处理类
 */
@RestControllerAdvice
@Slf4j
public class ExceptionCommon {

    @ExceptionHandler(value = Exception.class)
    public void common(Exception e, HttpServletResponse response) {
        try {
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            log.error(e.getMessage());
            Result error = Result.error(e.getMessage());
            System.out.println(error);
            writer.write(error.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
