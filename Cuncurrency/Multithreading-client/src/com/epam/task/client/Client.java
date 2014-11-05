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
		System.out.println("Input name:");
		String name = scanner.next();

		System.out.println("Input numbers:");

		while (i != 0) {
			i = scanner.nextInt();
			client.setName(name);
			client.writeCommand(i);
			String result = client.getResult();
			System.out.println("Result:" + result);

		}
	}

	
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

	public File createLockFile(String lockFileName) throws IOException {
		File lockFile = new File(lockFileName);
		lockFile.createNewFile();
		return lockFile;
	}

	public void deleteLockFile(File file) throws IOException {
		file.delete();
	}

	public boolean isLocked(String lockFileName) {
		File file = new File(lockFileName);
		return file.exists();
	}
}
