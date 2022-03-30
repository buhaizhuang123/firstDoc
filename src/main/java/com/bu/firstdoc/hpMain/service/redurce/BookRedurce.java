package com.bu.firstdoc.hpMain.service.redurce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/3/29 1:58 下午
 * @mark BookRedurce
 */
public class BookRedurce extends Reducer<Text, IntWritable,Text,IntWritable> {

    private IntWritable result = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        result.set(sum);
        context.write(key, result);
    }
}
