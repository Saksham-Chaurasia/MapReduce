package com.aw;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AWDriver {
		public static void main (String[] args) throws Exception{
			Configuration conf = new Configuration();
			Job job  = new Job(conf,"Average Word");
			
			job.setJarByClass(AWDriver.class);
			
			FileInputFormat.setInputPaths(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job,new Path(args[1]));
			
			job.setMapperClass(AWMapper.class);
			job.setReducerClass(AWReducer.class);
			
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			
			
			boolean success = job.waitForCompletion(true);
			System.exit(success ?0:1);
			
		}
}
