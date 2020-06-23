package wordcount;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReduceB extends Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(Text word, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {


        for (Text value : values) {
            String line = value.toString();
            //split groups of 3 event

                //write to file group
                context.write(new Text(line), new Text(""));

        }

    }
}
