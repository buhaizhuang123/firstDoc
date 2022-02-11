package com.bu.firstdoc.zs;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2:54 下午 2022/2/11
 * @mark MeIn
 */
@Data
@AllArgsConstructor
public class MeIn<T> {

    private T data;

    public T exr(T data){
        this.data = data;
        return data;
    }

}
