package wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.ArrayList;
import java.io.IOException;
import java.util.StringTokenizer;

public class MapB extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {


        String line = value.toString();
        //Extract events
        String SID    = line.split("\t")[0];
        String[] events =  line.split("\t")[1].split("  ");

        //string for final result
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < events.length; i++){
            String dd[]=events[i].split(",");
            if(events.length==i+1&&(i+1)%3!=0) {
                for(int j=0;j <=3- (i+1)%3; j++){
                    if(j ==3- (i+1)%3){

                        result.append("("+dd[1]+",0)");
                        context.write(new Text(SID), new Text(result.toString()));
                        result = new StringBuilder();
                        break;
                    }
                    result.append("("+dd[1]+","+dd[2]+"), ");

                }
            }
            else{
                result.append("("+dd[1]+","+dd[2]+")");
                if((i+1)%3==0){
                    context.write(new Text(SID), new Text(result.toString()));
                    result = new StringBuilder();

                }else{
                    result.append(", ");
                }
            }

        }



    }
}