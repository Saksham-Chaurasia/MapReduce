package com.dw;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DWDriver  {
	
	public static void main(String[] args) throws Exception{
		
		Configuration conf = new Configuration();
		
		Job job = new Job(conf);
		job.setJarByClass(DWDriver.class);
		
		job.setJobName("Distinct Word");
		job.setInputFormatClass(FileInputFormat.class);
		
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
	    
	    
	    job.setMapperClass(DWMapper.class);
	   
	    
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(Text.class);
	    
	    boolean success = job.waitForCompletion(true);
	    System.exit(success ? 0 : 1);

	    
		
	}
	
	
}