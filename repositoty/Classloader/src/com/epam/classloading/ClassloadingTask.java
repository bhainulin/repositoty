package com.epam.classloading;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.classloading.classloader.CustomClassloader;
import com.epam.classloading.functionality.IFunctionality;

public class ClassloadingTask {
	public static final Logger LOGGER = Logger.getLogger(ClassloadingTask.class);

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String menu = "";
		Scanner sc = new Scanner(System.in);
		CustomClassloader classloader;
		Class<?> clazz;
		IFunctionality functionality = null;
		while(!"exit".equals(menu)){
			LOGGER.info(String.format("Starts command: '%s'.", menu));
			menu = sc.nextLine();
			switch(menu){
			case "jar1":
				classloader = new CustomClassloader("D:\\jar1.jar"); 
				clazz = classloader.loadClass("Functionality");
				functionality = (IFunctionality) clazz.newInstance();
			break;
			case "jar2":	
				classloader = new CustomClassloader("D:\\jar2.jar"); 
				clazz = classloader.loadClass("Functionality");
				functionality = (IFunctionality) clazz.newInstance();
			break;
			case "exit":		
			break;
			default:
			{
				try{
					int num = Integer.parseInt(menu);
					if(functionality !=null){
						System.out.println("Result is: " + functionality.run(num));
						System.out.println("Number of runs " + functionality.getStaticCounter());
					}
				}catch(NumberFormatException e){
					LOGGER.error("Incorrect number:" + menu);
				}
			}
			}
			
		}
		sc.close();
	}

}
