package com.bu.firstdoc.hpMain.ts;

import com.bu.firstdoc.hpMain.config.HpUtil;
import com.bu.firstdoc.hpMain.service.mapper.BookMapper;
import com.bu.firstdoc.hpMain.service.redurce.BookRedurce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/3/26 9:59 下午
 * @mark HadoopTs
 */
public class HadoopTs {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        //  上传文件
//        FileSystem fileSystem = HpUtil.getFileSystem();
//        fileSystem.copyFromLocalFile(new Path("/Applications/tools/java/firstDoc/src/main/resources/1.txt"), new Path("/ts/"));
//        fileSystem.close();

        //  删除文件
//        FileSystem fileSystem = HpUtil.getFileSystem();
//        fileSystem.delete(new Path("/ts/1.txt"), true);
//        fileSystem.close();


        Configuration conf = HpUtil.getConf();

        Job job = Job.getInstance(conf, "world Count");
        job.setJarByClass(HadoopTs.class);
        job.setMapperClass(BookMapper.class);
        job.setCombinerClass(BookRedurce.class);
        job.setReducerClass(BookRedurce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path("/ts/language.txt"));
        FileOutputFormat.setOutputPath(job, new Path("/ts/language/out"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
