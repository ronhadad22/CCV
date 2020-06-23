
package wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import java.util.Random;

public class Main extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {

        Path tempDir = new Path("data/tempstep1-" + Integer.toString(new Random().nextInt(Integer.MAX_VALUE)));
        Path tempDir2 = new Path("data/tempstep2-" + Integer.toString(new Random().nextInt(Integer.MAX_VALUE)));
        Path tempDir3 = new Path("data/tempstep3-" + Integer.toString(new Random().nextInt(Integer.MAX_VALUE)));
        Configuration conf = getConf();
        //FileSystem.get(conf).delete(new Path("data/output"), true);


        try {
// ----------- 1 JOB -----------

            System.out.println("-------1 JOB-------");
            Job job1 = Job.getInstance(getConf());
            job1.setJobName("step1");
            job1.setJarByClass(Main.class);

            job1.setMapOutputValueClass(Text.class);
            job1.setOutputKeyClass(Text.class);
            job1.setOutputValueClass(Text.class);


            job1.setMapperClass(MapA.class);
            job1.setReducerClass(Reduce.class);

            //Path inputFilePath = new Path(args[0]);
            Path outputFilePath = tempDir;
            //FileInputFormat.addInputPath(job1,new Path("data/input/"));
            FileInputFormat.addInputPath(job1,new Path(args[0]) );
            FileOutputFormat.setOutputPath(job1, outputFilePath);

            if (!job1.waitForCompletion(true)) {
                return 1;
            }



            // ----------- 2 JOB -----------

            System.out.println("-------2 JOB-------");
            conf =  new Configuration();
            Job job2 = Job.getInstance(conf);
            job2.setJobName("Step2");
            job2.setJarByClass(Main.class);

            FileInputFormat.setInputPaths(job2, tempDir);
            FileOutputFormat.setOutputPath(job2, tempDir2);


            job2.setMapOutputValueClass(Text.class);
            job2.setOutputKeyClass(Text.class);
            job2.setOutputValueClass(Text.class);

            job2.setMapperClass(MapB.class);
            job2.setReducerClass(ReduceB.class);

            if (!job2.waitForCompletion(true)) {
                return 1;
            }

            // ----------- 3 JOB -----------

            System.out.println("-------3 JOB-------");
            conf =  new Configuration();
            Job job3 = Job.getInstance(conf);
            job3.setJobName("Step3");
            job3.setJarByClass(Main.class);

            FileInputFormat.setInputPaths(job3, tempDir2);
            FileOutputFormat.setOutputPath(job3, tempDir3);

            job3.setMapOutputValueClass(IntWritable.class);
            job3.setOutputKeyClass(Text.class);
            job3.setOutputValueClass(LongWritable.class);

            job3.setMapperClass(MapC.class);
            job3.setReducerClass(ReduceC.class);

            if (!job3.waitForCompletion(true)) {
                return 1;
            }

            // ----------- 4 JOB -----------
            System.out.println("-------4 JOB-------");
            conf =  new Configuration();
            Job job4 = Job.getInstance(conf);
            job4.setJobName("Step4");
            job4.setJarByClass(Main.class);


            FileInputFormat.setInputPaths(job4, tempDir3);
           // FileOutputFormat.setOutputPath(job4, new Path("data/tempstep3-" + Integer.toString(new Random().nextInt(Integer.MAX_VALUE))));
		    FileOutputFormat.setOutputPath(job4,new Path(args[1]))
		   

            job4.setMapOutputValueClass(Text.class);
            job4.setOutputKeyClass(LongWritable.class);
            job4.setOutputValueClass(Text.class);

            job4.setMapperClass(MapD.class);
            job4.setReducerClass(ReduceD.class);

            return job4.waitForCompletion(true) ? 0 : 1;

        }finally {
            FileSystem.get(conf).delete(tempDir, true);
            FileSystem.get(conf).delete(tempDir2, true);
            FileSystem.get(conf).delete(tempDir3, true);
        }
    }



    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Main(), args);
        System.exit(exitCode);
    }
}
