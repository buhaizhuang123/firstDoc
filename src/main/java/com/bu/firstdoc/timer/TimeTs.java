package com.bu.firstdoc.timer;

import org.apache.commons.lang3.time.DateParser;
import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author haizhuangbu
 * @date 10:21 上午 2022/2/2
 * @mark TimeTs
 */
public class TimeTs {


    class DemoTimerTask extends TimerTask {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }
}
