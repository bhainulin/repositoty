package com.sap.memory.task7;

import java.util.ArrayList;
import java.util.List;

public class JavaApp {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
        List<Object> list = new ArrayList<Object>();
        while(true) {
             list.add(new Object());
             //code change for increasing number of gc calls
             System.gc();
        }
    }

}
