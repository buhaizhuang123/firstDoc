package com.bu.firstdoc.io.channel.copy;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author haizhuangbu
 * @date 11:49 上午 2022/1/19
 * @mark FileCopy
 */
public class FileCopy {


    public static void main(String[] args) {

    }

    public static void nioCopySourceFile() {
        // source file
        String sourceFile = "/Applications/tools/java/firstDoc/src/main/resources/ons.txt";
        // target file
        String targetFile = "/Applications/tools/java/firstDoc/src/main/resources/ons1.txt";

        copyFile(sourceFile, targetFile);
    }

    public static void copyFile(String sourceFile, String targetFile) {
        File sourceFileOnes = new File(sourceFile);

        File targetFileOnes = new File(targetFile);
        // 如果目标文件不存在则创建
        if (!targetFileOnes.exists()) {
            try {
                targetFileOnes.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long startedTime = System.currentTimeMillis();
        System.out.println("startedTime = " + startedTime);
        FileInputStream fileInputStream = null;

        FileOutputStream fileOutputStream = null;

        FileChannel fis = null;

        FileChannel fos = null;

        try {
            fileInputStream = new FileInputStream(sourceFileOnes);
            fileOutputStream = new FileOutputStream(targetFileOnes);

            fis = fileInputStream.getChannel();
            fos = fileOutputStream.getChannel();

            int length = -1;

            ByteBuffer buff = ByteBuffer.allocate(1024);

            // 从通道读取到buffer while () 中元素优先操作
            while ((length = fis.read(buff)) != -1) {
                // 转换buffer模式  写 转换为读
                buff.flip();

                int outLength = 0;
                while ((outLength = fos.write(buff)) != 0) {
                    System.out.println("写入的字节数: " + outLength);
                }
                buff.clear();
            }
            // 强制刷入到磁盘
            fos.force(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(fileOutputStream);
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(fileInputStream);
        }
        long endDate = System.currentTimeMillis();
        System.out.println("endDate = " + endDate);
    }
}
