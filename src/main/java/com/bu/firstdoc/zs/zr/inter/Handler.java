package com.bu.firstdoc.zs.zr.inter;

import com.bu.firstdoc.zs.zr.inter.model.Member;

/**
 * @author haizhuangbu
 * @date 4:43 下午 2022/2/10
 * @mark Handler 责任链模式
 */
public abstract class Handler {

    public Handler handler;

   public void nextHandler(Handler handler){
        this.handler = handler;
       System.out.println(this.handler);
    }

    public abstract void doHandler(Member member);

   public static class Builder{

        private Handler header;

        private Handler tail;

        public Builder addHandler(Handler handler){
            if (this.header == null){
                this.header = this.tail = handler;
                return this;
            }
            this.tail.nextHandler(handler);

            this.tail = handler;

            return this;
        }

        public Handler build(){
            return this.header;
        }

    }

}
