package wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapC extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString();
        //split group event

        //check if pi is 1
        String result = line.replace("\t", "");
        String[] words = result.split(", ");
        int pi_pos = words[2].length() - 2;
        char charAt_= words[2].charAt(pi_pos);
        if(words[2].charAt(pi_pos) == '1') {
            context.write(new Text(words[0] + "," + words[1]+ " "), new IntWritable(1));
        }
    }
}