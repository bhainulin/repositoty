package com.epam.task.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

import com.epam.task.server.thread.Command;
import com.epam.task.server.thread.MyThread;
import com.epam.task.server.util.ServerUtils;

public class Server {
	public static final String COMMAND_FILE = "D:\\Commands.txt";
	public static final String RESULT_FILE = "D:\\Results.txt";
	public static final String COMMAND_FILE_LOCK = "D:\\Commands.lock";
	public static final String RESULT_FILE_LOCK = "D:\\Results.lock";

	public static final String INFO_FILE = "D:\\Info.txt";
	
	public static final long COMMAND_DELAY = 6000;
	public static final long READ_DELAY = 3000;

	public static void main(String[] args) throws IOException,
			InterruptedException {
		Server server = new Server();
		BlockingQueue<Command> commands = new DelayQueue<Command>();
		//waiting for few consoles
		Thread.sleep(10000);
		while (true) {
			//gets command from a file
			Command command = server.getCommand();
			if (command != null) {
				//puts command to an queue
				commands.offer(command);
				System.out.println("Put command.");
			}
			
			//tries to read command from a file
			Command readyToExecute = commands.poll(READ_DELAY, TimeUnit.MILLISECONDS);

			//if command was read start new thread for an executing
			if (readyToExecute != null) {
				System.out.println("Gets command.");
				Thread thread = new Thread(new MyThread(readyToExecute));
				thread.start();
				System.out.println(thread.getName() + " is started.");
				System.out.println(Thread.getAllStackTraces().size()
						+ " is running.");
			}
		}
	}

	/**
	 * Reads command from Commands.txt. If file was locked sleep for 100ms. If file was not locked - lock it and remove command from file.
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public Command getCommand() throws InterruptedException, IOException {
		String result = null;
		Object key = null;
		if (!ServerUtils.isLocked(COMMAND_FILE_LOCK)) {
			Thread.sleep(100);
			File lockFile = null;
			try {
				lockFile = ServerUtils.createLockFile(COMMAND_FILE_LOCK);

				Properties properties = readFromFile(COMMAND_FILE);
				if (properties.size() > 0) {
					key = getElementKey(properties);
					result = (String) properties.remove(key);
					writeToFile(COMMAND_FILE, properties);
				}
			} finally {
				ServerUtils.deleteLockFile(lockFile);
			}
		}
		if(result == null || key == null){
			return null;
		}
		return new Command(key.toString(), result, COMMAND_DELAY);
	}

	/**
	 * gets first element from property.
	 */
	private Object getElementKey(Properties properties) {
		Set<Object> keys = properties.keySet();
		for (Object key : keys) {
			return key;
		}
		return null;
	}

	/**
	 * Reads properties from file.
	 * @param fileName - name of a file.
	 * @return properties
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private Properties readFromFile(String fileName) throws IOException,
			InterruptedException {
		FileInputStream in = null;
		try {
			Properties properties = new Properties();
			in = new FileInputStream(fileName);
			properties.load(in);
			return properties;
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	/**
	 * Writes properties to file
	 * @param fileName - name of a file
	 * @param properties - properties to write
	 * @throws IOException
	 */
	private void writeToFile(String fileName, Properties properties)
			throws IOException {
		FileOutputStream out = null;

		try {
			out = new FileOutputStream(fileName);
			properties.store(out, "");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
