package com.aw;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.IntWritable;

public class AWMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
	
	public void map(LongWritable key, Text value , Context context) throws IOException, InterruptedException{
		
		String line = value.toString();
		
		for(String word : line.split("\\W+")){
			if(word.length()>0){
				String letter = word.substring(0,1).toLowerCase();
				
				context.write(new Text(letter), new IntWritable(word.length()));
			}
		}
		
		
	}

}
