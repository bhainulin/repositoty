package com.epam.task.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Client {

	/**
	 * @param args
	 * @throws IOException
	 */

	public static final String COMMAND_FILE = "D:\\Commands.txt";
	public static final String RESULT_FILE = "D:\\Results.txt";

	public static final String COMMAND_FILE_LOCK = "D:\\Commands.lock";
	public static final String RESULT_FILE_LOCK = "D:\\Results.lock";

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {

		Client client = new Client();
		Integer i = -1;

		Scanner scanner = new Scanner(System.in);
		//reads console name
		System.out.println("Input name:");
		String name = scanner.next();
		client.setName(name);
		System.out.println("Input numbers:");

		while (i != 0) {
			//reads command number
			i = scanner.nextInt();
			//writes command to Commands.txt
			client.writeCommand(i);
			long startWait = System.currentTimeMillis();
			
			//gets the result of execution
			String result = client.getResult();
			
			long endWait = System.currentTimeMillis();
			System.out.println("Result:" + result);
			System.out.println("Waited for Result: " + (endWait - startWait) + "ms.");

		}
	}

	/**
	 * Reads result by name from Results.txt file. If file was locked waits 1000ms. Then remove result from file.
	 * @return result.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String getResult() throws InterruptedException, IOException {
		String result = null;
		while (result == null) {
			if (!isLocked(RESULT_FILE_LOCK)) {
				Thread.sleep(100);
				File lockFile = null;
				try {
					lockFile = createLockFile(RESULT_FILE_LOCK);

					Properties properties = readFromFile(RESULT_FILE);
					result = (String) properties.remove(name);
					if (result != null) {
						writeToFile(RESULT_FILE, properties);
					}
				} finally {
					deleteLockFile(lockFile);
				}
			}
		}
		return result;
	}

	/**
	 * Writes command to Commands.txt file. If file was locked sleep for 1000 ms and then tries again.
	 * @param value - command value.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void writeCommand(Integer value) throws InterruptedException,
			IOException {
		while (isLocked(COMMAND_FILE_LOCK)) {
			Thread.sleep(100);
		}
		File lockFile = null;
		try {
			lockFile = createLockFile(COMMAND_FILE_LOCK);

			Properties properties = readFromFile(COMMAND_FILE);
			properties.put(name, value.toString());
			writeToFile(COMMAND_FILE, properties);

		} finally {
			deleteLockFile(lockFile);
		}
	}

	/**
	 * Reads properties from property file.
	 * @param fileName - name of property file.
	 * @return properties
	 * @throws IOException
	 */
	private Properties readFromFile(String fileName) throws IOException {
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
	 * Writes properties to property file.
	 * @param fileName - file name.
	 * @param properties - properties to write.
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

	/**
	 * Creates lock file.
	 * @param lockFileName -name of lock file.
	 * @return
	 * @throws IOException
	 */
	public File createLockFile(String lockFileName) throws IOException {
		File lockFile = new File(lockFileName);
		lockFile.createNewFile();
		return lockFile;
	}

	/**
	 * Deletes lock file.
	 * @param file - instance of lock file.
	 * @throws IOException
	 */
	public void deleteLockFile(File file) throws IOException {
		file.delete();
	}

	/**
	 * Checks if file was locked.
	 * @param lockFileName - name of lock file
	 * @return
	 */
	public boolean isLocked(String lockFileName) {
		File file = new File(lockFileName);
		return file.exists();
	}
}
