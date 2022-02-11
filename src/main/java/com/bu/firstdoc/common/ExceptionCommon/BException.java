package com.bu.firstdoc.common.ExceptionCommon;

/**
 * @author haizhuangbu
 * @date 2:14 下午 2022/1/14
 * @mark BException
 */
public class BException extends Exception {

    public BException(String msg, Object... data) {
        super(String.format(msg, data));
    }


}
