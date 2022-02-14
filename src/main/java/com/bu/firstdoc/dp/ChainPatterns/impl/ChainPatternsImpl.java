package com.bu.firstdoc.dp.ChainPatterns.impl;

import com.bu.firstdoc.dp.ChainPatterns.ChainPatternsInter;

import java.util.Objects;

/**
 * @author haizhuangbu
 * @date 3:40 下午 2022/2/14
 * @mark ChainPatternsImpl
 */
public class ChainPatternsImpl implements ChainPatternsInter {

    private ChainPatternsInter chainPatternsInter;

    public void addChain(ChainPatternsInter chainPatternsInter){
        this.chainPatternsInter = chainPatternsInter;
    }


    @Override
    public void doSomeThing() {
        System.out.println("基本方法执行");
        if (Objects.nonNull(chainPatternsInter)){
            chainPatternsInter.doSomeThing();
        }
    }
}
