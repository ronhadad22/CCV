package wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapD extends Mapper<LongWritable, Text, LongWritable, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString();
        //parse events
        String result = line.replace("\t", "");
        String word[] = result.split(" ");
        //parse count
        Long count=Long.valueOf(word[1]).longValue();
        //int count = Integer.parseInt(word[1]);
        // use negative key for descending order sorting
        context.write(new LongWritable(count), new Text(word[0]));
    }
}
