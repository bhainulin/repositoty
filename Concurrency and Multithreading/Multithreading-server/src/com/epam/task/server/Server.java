package com.epam.task.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import com.epam.task.server.thread.MyThread;

public class Server {
	public static final String COMMAND_FILE = "D:\\Commands.txt";
	public static final String RESULT_FILE = "D:\\Results.txt";
	public static final String COMMAND_FILE_LOCK = "D:\\Commands.lock";
	public static final String RESULT_FILE_LOCK = "D:\\Results.lock";

	public static final String INFO_FILE = "D:\\Info.txt";

	public static void main(String[] args) throws IOException,
			InterruptedException {
		Server server = new Server();
		while (true) {
			String[] command = server.getCommand();
			Thread thread = new Thread(new MyThread(command[0], command[1]));
			thread.start();
			System.out.println(thread.getName() + " is started.");
			System.out.println(Thread.getAllStackTraces().size() + " is running.");
		}
	}

	public String[] getCommand() throws InterruptedException, IOException {
		String result = null;
		Object key = null;
		while (result == null) {
			if (!isLocked(COMMAND_FILE_LOCK)) {
				Thread.sleep(100);
				File lockFile = null;
				try {
					lockFile = createLockFile(COMMAND_FILE_LOCK);

					Properties properties = readFromFile(COMMAND_FILE);
					if (properties.size() > 0) {
						key = getElementKey(properties);
						result = (String) properties.remove(key);
						writeToFile(COMMAND_FILE, properties);
					}
				} finally {
					deleteLockFile(lockFile);
				}
			}
		}
		return new String[] { key.toString(), result };
	}

	private Object getElementKey(Properties properties) {
		Set<Object> keys = properties.keySet();
		for (Object key : keys) {
			return key;
		}
		return null;
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
