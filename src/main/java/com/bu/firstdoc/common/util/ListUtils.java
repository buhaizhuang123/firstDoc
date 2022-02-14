package com.bu.firstdoc.common.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author haizhuangbu
 * @date 10:13 上午 2022/2/14
 * @mark ListUtils
 */
public class ListUtils<T> {


    public static void main(String[] args) {
        ListUtils.Get<String> get = new ListUtils.Get<>();
        List<String> list = get.setData("123").setData("234").getList();
        list.forEach(System.out::println);

    }

    static  class Get<T> {

        private T data;

        private List<T> objects;


        public Get() {

        }


        public List<T> getList(){
            if (Objects.isNull(objects)){
                objects = new ArrayList<>();
            }
            return objects;
        }

        public Get<T> setData(T data){
            if (Objects.nonNull(objects)){
                objects.add(data);
            }else {
                objects = new ArrayList<>();
                objects.add(data);
            }
            return this;
        }



    }

}
