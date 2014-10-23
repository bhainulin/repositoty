package com.sap.memory.task3;

import java.util.ArrayList;
import java.util.List;

import com.sap.memory.task3.classloader.CustomClassloader;
public class OutOfMemoryApp {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			initiateOutOfHeapMemory();
		} catch (OutOfMemoryError error) {
			System.out.println(error);
		}
		try{
		initiateOutOfPermMemory();
		}catch(OutOfMemoryError error){
			System.out.println(error);
		}
	}

	private static void initiateOutOfHeapMemory() {
		List<Object> objectHolder = new ArrayList<>();
		while (true) {
			byte[] massive = new byte[1048576];
			objectHolder.add(massive);
		}
	}
	
	private static void initiateOutOfPermMemory() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		List<Class<?>> objectHolder = new ArrayList<>();
		while (true) {
			ClassLoader myClassLoader = new CustomClassloader("D:\\jar1.jar");	
			Class<?> clazz = myClassLoader.loadClass("Functionality");
			objectHolder.add(clazz);
		}
	}
}

