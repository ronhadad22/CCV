package wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.*;
import java.io.IOException;

public class Reduce extends Reducer<Text, Text, Text, Text> {
    @Override
    public void reduce(Text word, Iterable<Text> values, Context context)
                throws IOException, InterruptedException {

        /*extract events*/
        ArrayList<ArrayList<String>> eventList = new ArrayList<ArrayList<String>>();
        for (Text event : values) {
            
            String line = event.toString();
            String[] words = line.split(",");
            eventList.add(new ArrayList<String>(Arrays.asList(words)));
        }

        /*sort events by timestamp*/
        Collections.sort(eventList, new Comparator<ArrayList<String>>() {
                @Override
                public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                    return o1.get(0).compareTo(o2.get(0));
                }               
        });
        /*concatenate strings*/
        StringBuilder result = new StringBuilder();
        for (ArrayList<String> event : eventList) {
            result.append(event.get(0) + ",");
            result.append(event.get(1) + ",");
            result.append(event.get(2) + " ");
        }

        context.write(word, new Text(result.toString()));
    }
}
