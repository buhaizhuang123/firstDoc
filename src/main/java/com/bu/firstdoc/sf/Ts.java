package com.bu.firstdoc.sf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author haizhuangbu
 * @date 2022/3/8 10:38 上午
 * @mark Ts
 */
public class Ts {


    public static void main(String[] args) {
        int[] integers = new int[]{5,9,2,3};
        for (int i = 0; i < integers.length -1; i++) {
            for (int j = i + 1; j < integers.length; j++) {
                if (integers[i] >= integers[j]){
                   int flag =  integers[i];
                   integers[i] = integers[j];
                   integers[j] = flag;
                }
            }
        }

        Arrays.stream(integers).forEach(System.out::print);

        // 冒泡排序
        for (int i = 0; i < integers.length -1; i++) {
            for (int j = 0; j < integers.length - 1 -i; j++) {
                if (integers[j+1] >= integers[j]) {
                    int flag = integers[j];
                    integers[j] = integers[j + 1];
                    integers[j + 1] = flag;
                }
            }
        }
        System.out.println();
        Arrays.stream(integers).forEach(System.out::print);


        System.out.println();
    }

}
