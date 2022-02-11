package com.bu.firstdoc.rpc;

import lombok.Data;

/**
 * @author haizhuangbu
 * @date 9:12 下午 2022/2/9
 * @mark InvokerProtocol
 */
@Data
public class InvokerProtocol {

//    类名
    private String className;

    // 函数名称
    private String methodName;

//    参数类型
    private Class<?>[] params;

//    参数列表
    private Object[] values;

}
