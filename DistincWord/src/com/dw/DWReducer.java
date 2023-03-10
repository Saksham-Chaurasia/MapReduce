package com.dw;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Reducer;

public class DWReducer extends Reducer<Text, Text, Text,Text>{

	
	public void reduce(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException{
		// print distinct word in separated line
		//one way
//		int sum =0;
//		for(IntWritable values: value){
//			sum+=values.get();
//			
//			if(sum==1)
//				context.write(key,NullWritable.get());
//		}
		// second way
		
		//context.write(key,NullWritable.get());
		
		// print distinct word in same line
		
		
		String l = " ";
		Set<String> distWord = new HashSet<String>();
		
		for(Text values: value){
			distWord.add(values.toString());
		}
		
		for (String st : distWord){
			l +=st + " ";
		}
		
		
		
		context.write(new Text("Distinct Words: "), new Text(l));
		
		
		
		
	}
}
