package com.aw;

import java.io.IOException;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;
public class AWReducer extends Reducer<Text, IntWritable , Text , DoubleWritable> {
	
	public void reduce(Text key, Iterable<IntWritable> value, Context context) throws IOException, InterruptedException {
		
		double sum =0; 
		double count =0;
		
		
		
		for(IntWritable values: value){
			sum +=values.get();
			count++;
		}
		
		if(count!=0d){
			double result = sum/count;
			context.write(key, new DoubleWritable(result));
		}
		
	}

}
