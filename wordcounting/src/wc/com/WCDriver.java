package wc.com;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WCDriver {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("Word count is not correct ");
			System.exit(-1);
		}
		Configuration conf = new Configuration();
		Job job = new Job(conf);

		job.setJobName("Word Count");

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setJarByClass(WCDriver.class);

		job.setMapperClass(WCMapper.class);
		job.setReducerClass(WCReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		boolean success = job.waitForCompletion(true);
		System.exit(success ? 0 : 1);
	}
}
