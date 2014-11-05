package com.epam.task.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ServerUtils {
	public static Properties readFromFile(String fileName) throws IOException {
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

	public static void writeToFile(String fileName, Properties properties)
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
	
	public static File createLockFile(String lockFileName) throws IOException {
		File lockFile = new File(lockFileName);
		lockFile.createNewFile();
		return lockFile;
	}

	public static void deleteLockFile(File file) throws IOException {
		file.delete();
	}

	public static boolean isLocked(String lockFileName) {
		File file = new File(lockFileName);
		return file.exists();
	}
}
