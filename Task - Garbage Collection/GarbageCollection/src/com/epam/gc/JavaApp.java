package com.epam.gc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class JavaApp {
	public static final Logger LOGGER = Logger.getLogger(JavaApp.class);

	public static void main(String[] args) {
		long startApp = System.currentTimeMillis();
		for(int i = 0; i < 10; i++){
			List<BigDecimal> bigObject = new ArrayList<BigDecimal>();
			long startCycle = System.currentTimeMillis();
			for(int j = 0; j < 240000; j++){
				BigDecimal decimal = new BigDecimal(j);
				decimal = decimal.pow(2);
				bigObject.add(decimal);
			}
			long endCycle = System.currentTimeMillis();
			LOGGER.info(startCycle);
			LOGGER.info(endCycle);
		}
		long endApp = System.currentTimeMillis();
		LOGGER.info(startApp);
		LOGGER.info(endApp);
		System.out.println("Execution is over.");
	}

}
