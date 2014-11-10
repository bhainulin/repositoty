package com.epam.task.server.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

import com.epam.task.server.util.ServerUtils;

public class MyThread implements Runnable {

	private Command command;

	public MyThread(Command command) {
		super();
		this.command = command;
	}

	private static ReentrantLock LOCK = new ReentrantLock();

	public static final String INFO_FILE = "D:\\Info.txt";
	public static final String RESULT_FILE = "D:\\Results.txt";
	public static final String RESULT_FILE_LOCK = "D:\\Results.lock";

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " runs.");
		
		//waits for lock
		if (LOCK.tryLock()) {
			System.out.println(Thread.currentThread().getName()
					+ " is not locked.");
			System.out.println(Thread.currentThread().getName() + " Lock.");
			try {
				System.out.println(Thread.currentThread().getName()
						+ " getInfoProperties start");
				Properties properties = getInfoProperties();
				System.out.println(Thread.currentThread().getName()
						+ " getInfoProperties end");
				
				//reads amount available on account
				String capacityStr = properties.getProperty(command.getName());
				if (capacityStr == null) {
					//there is no such account in Info.txt
					command.setResult("No such account");
				} else {
					System.out.println(Thread.currentThread().getName()
							+ " countResult start");
					//count result
					countResult(capacityStr);
					System.out.println(Thread.currentThread().getName()
							+ " countResult end");
					//put result to properties
					properties.put(command.getName(), command.getResult());
					System.out.println(Thread.currentThread().getName()
							+ " writeInfoProperties start");
					//write new amount in property
					writeInfoProperties(properties, command.getResult());
					System.out.println(Thread.currentThread().getName()
							+ " writeInfoProperties end");
				}
				System.out.println(Thread.currentThread().getName()
						+ " writeResult start");
				//write new result
				writeResult(command.getName(), command.getResult());
				System.out.println(Thread.currentThread().getName()
						+ " writeResult end");
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				System.out.println(Thread.currentThread().getName()
						+ " is ended.");
				//free lock
				LOCK.unlock();
				System.out.println(Thread.currentThread().getName()
						+ " lock is free.");
			}
		}
		System.out.println(Thread.currentThread().getName() + " ends.");
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	private void countResult(String capacityStr) {
		Integer capacity = Integer.parseInt(capacityStr);
		Integer commandInt = Integer.parseInt(command.getCommand());
		Integer resultInt = capacity - commandInt;
		if (resultInt < 0) {
			//if the result is negative wtite a message
			command.setResult("No more money!");
		} else {
			//sets new sum 
			command.setResult(resultInt.toString());
		}
	}

	/**
	 * Writes new data in Info.txt file.
	 * @param properties - new property
	 * @param result - result of execution
	 * @throws IOException
	 */
	private void writeInfoProperties(Properties properties, String result) throws IOException {
		if("No more money!".equals(result)){
			return;
		}
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

	/**
	 * Reads info properties.
	 * @return
	 * @throws IOException
	 */
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
