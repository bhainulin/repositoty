package com.epam.memorymanagment.task1;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class MemoryEater {
	static final Logger logger = Logger.getLogger(MemoryEater.class);

	public static void main(String[] args) {
		long freeMemory = 0;
		long numOfElements = 0;
		
		Runtime rt = Runtime.getRuntime();
		logger.info("free memory at start: " + rt.freeMemory()/1048576);
		
		try {
			List<Object> v = new ArrayList<>();
			while(true){
				freeMemory = rt.freeMemory();
				if(freeMemory < 128*1048576){
					v.remove(0);
				}
				byte b[] = new byte[1048576];
				v.add(b);
				logger.info("free memory: " + freeMemory/1048576);
				numOfElements++;

			}
		} finally {
			logger.info("free memory at end: " + freeMemory/1048576);
			logger.info("Num Of elements: " + numOfElements);
		}
	}
}
