package com.bu.firstdoc.dp.ChainPatterns;

import com.bu.firstdoc.zs.zr.inter.model.Member;

/**
 * @author haizhuangbu
 * @date 3:49 下午 2022/2/14
 * @mark Handler
 */
public abstract class Handler {

    public Handler handler;

    public void nextHandler(Handler handler){
        this.handler = handler;
    }

    public abstract void doSome(Member member);

    public static class Builder{

        private Handler head;

        private Handler tail;

        public Builder addHandler(Handler handler){
            if (this.head == null){
                this.head  = this.tail =  handler;
                return this;
            }
            this.tail.nextHandler(handler);
            this.tail = handler;
            return this;
        }

        public Handler build(){
            return this.head;
        }


    }


}
