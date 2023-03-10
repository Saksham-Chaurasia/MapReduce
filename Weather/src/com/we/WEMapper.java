package com.we;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;


public class WEMapper extends Mapper<LongWritable,Text,Text,Text> {
	float max_temp = Float.MIN_VALUE;
	float min_temp = Float.MAX_VALUE;
	float maximum =0;
	float minimum =0;
	
	@Override
	public void map(LongWritable key, Text value, Context context)throws IOException,InterruptedException{
		
		String line = value.toString();
		
		if(!(line.length()==0)){
			//taking date
			String date = line.substring(6,14);
			//taking all the temperature values;
			String temp = line.substring(15);
			//now tokenizing all the temperature values;
			StringTokenizer linew = new StringTokenizer(temp," ");
			
			//only considering the maximum and minimum temperature value from that particular date;
			while(linew.hasMoreTokens()){
				float f = Float.parseFloat(linew.nextToken());
				maximum = f;
				minimum = f;
				
				if(maximum>max_temp)
					max_temp = maximum;
				if(minimum<min_temp)
					min_temp = minimum;
				
			}
			
			String maxT = Float.toString(max_temp);
			String minT = Float.toString(min_temp);
			String tT = "maximum Temp " + maxT + " minimum Temp " + minT;
			
			
			context.write(new Text(date + " "), new Text(tT));
			
		}
	}
}
