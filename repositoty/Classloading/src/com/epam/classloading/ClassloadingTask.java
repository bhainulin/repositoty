package com.epam.classloading;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.classloading.classloader.CustomClassloader;

public class ClassloadingTask {
	public static final Logger LOGGER = Logger
			.getLogger(ClassloadingTask.class);

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException {
		String menu = "";
		Scanner sc = new Scanner(System.in);
		menu = sc.nextLine();
		while (!"exit".equals(menu)) {
			LOGGER.info(String.format("Starts command: '%s'.", menu));
			switch (menu) {
			case "jar1":
				menu = menuJar(sc, "D:\\jar1.jar");
				break;
			case "jar2":
				menu = menuJar(sc, "D:\\jar2.jar");
				break;
			case "exit":
				break;
			}
		}
		sc.close();
	}

	private static String menuJar(Scanner sc, String jarName)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException {
		CustomClassloader classloader = new CustomClassloader(jarName);
		Class<?> clazz = classloader.loadClass("Functionality");
		Object functionality = clazz.newInstance();
		String menu = "";

		boolean bExitFromJarMenu = false;

		while (!bExitFromJarMenu) {
			menu = sc.nextLine();
			bExitFromJarMenu = isExitFromJarMeny(menu);
			if (!bExitFromJarMenu) {
				runFunctionality(menu, functionality);
			}
		}
		return menu;
	}

	private static void runFunctionality(String numStr, Object functionality)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		try {
			int num = Integer.parseInt(numStr);
			if (functionality != null) {

				int runResult = executeRun(functionality, num);
				int staticCounterResult = executeGetStaticCounter(functionality);

				System.out.println("Result is: " + runResult);
				System.out.println("Number of runs " + staticCounterResult);
			}
		} catch (NumberFormatException e) {
			LOGGER.error("Incorrect number:" + numStr);
		}
	}

	private static Integer executeRun(Object obj, int value)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Class<?>[] paramTypes = new Class[] { int.class };
		Method method = obj.getClass().getMethod("run", paramTypes);
		return (Integer) method.invoke(obj, value);
	}

	private static Integer executeGetStaticCounter(Object obj)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Method method = obj.getClass().getMethod("getStaticCounter",
				new Class[] {});
		return (Integer) method.invoke(obj);
	}

	private static boolean isExitFromJarMeny(String menu) {
		return "jar1".equals(menu) || "jar2".equals(menu)
				|| "exit".equals(menu);
	}

}
