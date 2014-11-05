package com.epam.task.server.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.epam.task.server.util.ServerUtils;

public class MyThread implements Runnable {

	private String name;

	public MyThread(String name, String command) {
		super();
		this.name = name;
		this.command = command;
	}

	private String command;
	private String result;

	private static ReentrantLock LOCK = new ReentrantLock();

	public static final String INFO_FILE = "D:\\Info.txt";
	public static final String RESULT_FILE = "D:\\Results.txt";
	public static final String RESULT_FILE_LOCK = "D:\\Results.lock";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " runs.");
		
			if (LOCK.tryLock()) {
				System.out.println(Thread.currentThread().getName() + " is not locked.");
				System.out.println(Thread.currentThread().getName() + " Lock.");
				try {
					System.out.println(Thread.currentThread().getName() + " getInfoProperties start");
					Properties properties = getInfoProperties();
					System.out.println(Thread.currentThread().getName() + " getInfoProperties end");
					String capacityStr = properties.getProperty(name);
					if (capacityStr == null) {
						result = "No such account";
					} else {
						System.out.println(Thread.currentThread().getName() + " countResult start");
						countResult(capacityStr);
						System.out.println(Thread.currentThread().getName() + " countResult end");
						properties.put(name, result);
						System.out.println(Thread.currentThread().getName() + " writeInfoProperties start");
						writeInfoProperties(properties);
						System.out.println(Thread.currentThread().getName() + " writeInfoProperties end");
					}
					System.out.println(Thread.currentThread().getName() + " writeResult start");
					writeResult(name, result);
					System.out.println(Thread.currentThread().getName() + " writeResult end");
				} catch (Exception e) {
					System.out.println(e);
				} finally {
					System.out.println(Thread.currentThread().getName() + " is ended.");
					LOCK.unlock();
					System.out.println(Thread.currentThread().getName() + " lock is free.");
				}
			}
		System.out.println(Thread.currentThread().getName() + " ends.");
	}

	private void countResult(String capacityStr) {
		Integer capacity = Integer.parseInt(capacityStr);
		Integer commandInt = Integer.parseInt(command);
		Integer resultInt = capacity - commandInt;
		result = resultInt.toString();
	}

	private void writeInfoProperties(Properties properties) throws IOException {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(INFO_FILE);
			properties.store(fileOutputStream, "");
		} finally {
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
	}

	private Properties getInfoProperties() throws IOException {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(INFO_FILE);
			Properties properties = new Properties();
			properties.load(fileInputStream);
			return properties;
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}
	}

	public void writeResult(String name, String value)
			throws InterruptedException, IOException {
		while (ServerUtils.isLocked(RESULT_FILE_LOCK)) {
			Thread.sleep(100);
		}
		File lockFile = null;
		try {
			lockFile = ServerUtils.createLockFile(RESULT_FILE_LOCK);

			Properties properties = ServerUtils.readFromFile(RESULT_FILE);
			properties.put(name, value);
			ServerUtils.writeToFile(RESULT_FILE, properties);

		} finally {
			ServerUtils.deleteLockFile(lockFile);
		}
	}

}
