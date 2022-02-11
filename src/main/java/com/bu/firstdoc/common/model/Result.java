package com.bu.firstdoc.common.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author haizhuangbu
 * @date 1:54 下午 2022/1/14
 * @mark Result
 */
@Data
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{" +
                "code :" + code +
                ", msg:'" + msg + '\'' +
                ", data:" + data +
                '}';
    }

    public static Result success() {
        return new Result(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }


    public static Result success(Object data) {
        return new Result(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }


    public static Result error() {
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }


    public static Result error(String msg) {
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

}
