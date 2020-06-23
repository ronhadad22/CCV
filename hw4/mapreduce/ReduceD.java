package wordcount;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReduceD extends Reducer<LongWritable, Text,LongWritable, Text> {

    @Override
    public void reduce(LongWritable key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        //Append values to one big array

        for (Text value : values) {
            context.write(key,new Text(value));
        }
        //Write to final file

    }
}
