package com.bu.firstdoc.dp.DesignPatterns.DecorativePattern.impl;

import com.bu.firstdoc.dp.DesignPatterns.DecorativePattern.DecoExe;

/**
 * @author haizhuangbu
 * @date 3:22 下午 2022/2/14
 * @mark BaseDecoExe
 */
public class BaseDecoExe implements DecoExe {

   private DecoExe decoExe;

   public BaseDecoExe(DecoExe decoExe){
       this.decoExe = decoExe;
   }

    @Override
    public String getMsg(){
        return this.decoExe.getMsg();
    }

}
