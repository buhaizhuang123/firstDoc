package com.bu.firstdoc.afLoan;

import com.bu.firstdoc.afLoan.impl.OneHandler;
import com.bu.firstdoc.afLoan.impl.TwoHandler;
import lombok.Data;

/**
 * @author haizhuangbu
 * @date 9:53 上午 2022/1/25
 * @mark AfHandler
 */
@Data
public abstract class AfHandler {


    private AfHandler afHandler;

    /**
     * @param req
     */
    public abstract void handleRequest(String req);


    public static void main(String[] args) {
        OneHandler oneHandler = new OneHandler();
        TwoHandler twoHandler = new TwoHandler();
        oneHandler.setAfHandler(twoHandler);
        oneHandler.handleRequest("one");
    }

}
