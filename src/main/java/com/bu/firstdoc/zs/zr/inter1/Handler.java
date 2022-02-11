package com.bu.firstdoc.zs.zr.inter1;

import com.bu.firstdoc.zs.zr.inter.model.Member;

/**
 * @author haizhuangbu
 * @date 9:00 下午 2022/2/10
 * @mark Handler
 */
public abstract class Handler {

    public Handler handler;

    public abstract void doHandler(Member member);

    public void addHandler(Handler handler){
        this.handler = handler;
    }

    public static class Builder{

        private Handler head;

        private Handler tail;

        public Builder addHandler(Handler handler){
            if (head == null){
                this.head = this.tail = handler;
                return this;
            }
            this.tail.addHandler(handler);
            this.tail = handler;
            return this;
        }

        public Handler build(){
            return this.head;
        }


    }

}
