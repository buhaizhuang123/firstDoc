package com.bu.firstdoc.hpMain.service.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/3/29 1:53 下午
 * @mark BookMapper
 */
public class BookMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final IntWritable l = new IntWritable(1);

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String val = value.toString();
        String[] names = val.split(" ");

        for (String name : names) {
            context.write(new Text(name), l);
        }

    }
}
