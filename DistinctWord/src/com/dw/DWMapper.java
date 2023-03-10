package com.dw;

import java.io.*;
import java.util.*;

import org.apache.hadoop.io.LongWritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DWMapper extends Mapper<LongWritable,Text,Text,Text> {
	
	public void map(LongWritable key , Iterator<Text> value, Context context)throws IOException, InterruptedException {
		
		String line = " ";
		while(value.hasNext()){
		 line =value.next().toString();
		}
		String [] data = line.split(" ");
		
		String str = " ";
		
		Set<String> distWord = new HashSet<String>();
		
		for(int i=0; i<data.length; i++){
			if(!distWord.contains(data[i])){
				distWord.add(data[i]);
			}
		}
		
		for(String st : distWord){
			str +=st + " ";
			
		}
		
		context.write(new Text("Distinct Words: "), new Text(str));
		
		
		}
		
}
	


