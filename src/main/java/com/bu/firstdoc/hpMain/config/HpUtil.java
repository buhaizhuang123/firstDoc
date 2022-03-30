package com.bu.firstdoc.hpMain.config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/3/29 11:50 上午
 * @mark HpUtil
 */
public class HpUtil {


    private static Configuration conf;

    static {
        conf = new Configuration();
        System.setProperty("hadoop.home.dir", "/usr/local/Cellar/hadoop/3.3.2/");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.set("fs.default.name", "hdfs://localhost:9000");
    }

    /**
     * 获取操作对象
     */
    public static FileSystem getFileSystem() {
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileSystem;
    }

    /**
     * 校验文件是否存在
     */
    public boolean isExists(String fileName) {
        Boolean isExists = null;
        try {
            isExists = getFileSystem().exists(new Path(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isExists;
    }


    public static Configuration getConf() {
        return conf;
    }

}
