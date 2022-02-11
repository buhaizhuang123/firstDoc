package com.bu.firstdoc.rpc.provider.registry;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author haizhuangbu
 * @date 9:29 下午 2022/2/9
 * @mark RegistryHandler
 */
public class RegistryHandler extends ChannelInboundHandlerAdapter {

//    保存所有可用的服务
        public static ConcurrentHashMap<String,Object> registryMap = new ConcurrentHashMap<>();

        // 保存所有相关的服务类
    private List<String> classNames = new ArrayList<>();

    public RegistryHandler(){
        scannerClass("com.bu.firstdoc.rpc.provider");
        doRegister();


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    /**
     * 完成注册
     * */
    private void doRegister() {

        if (classNames.size() == 0) {
            return;
        }
        for (String className : classNames) {
                try {
                    Class<?> clazz = Class.forName(className);
                    Class<?> i = clazz.getInterfaces()[0];
                    registryMap.put(i.getName(),clazz.newInstance());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }


    /*
    * 包扫描
    * */
    private void scannerClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));

        File dir = new File(url.getFile());

        for (File file : dir.listFiles()) {

            if (file.isDirectory()) {
                scannerClass(packageName + "." + file.getName());
            }else {
                classNames.add(packageName + "." +file.getName().replace(".class","").trim());
            }

        }


    }


}
