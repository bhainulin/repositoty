package com.epam.classloading.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

public class CustomClassloader extends ClassLoader {
	
	public static final Logger LOGGER = Logger.getLogger(CustomClassloader.class);
	
	private Map<String, Class<?>> cache = new HashMap<String, Class<?>>();
	
	private String jarFileName;
	
	public CustomClassloader(String jarFileName) {
		this.jarFileName = jarFileName;
		LOGGER.info("Class loader was created for " + jarFileName + " file.");
	}
	
	public CustomClassloader() {
		LOGGER.info("Empty Class Loader is creating.");
	}
	
	public void setJarFileName(String jarFileName){
		this.jarFileName = jarFileName;
		LOGGER.info("New Jar file name " + jarFileName + " file.");
	}

	@Override
	public Class<?> findClass(String className) throws ClassNotFoundException {
		Class<?> clazz = null;
		if(cache.containsKey(jarFileName + className)){
			LOGGER.info("Loader cache contains " + jarFileName + className + " class.");
			return cache.get(jarFileName + className);
		}
		try {
			JarFile jarFile = new JarFile(jarFileName);
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements() && clazz == null) {
				JarEntry jarEntry = (JarEntry) entries.nextElement();
				if (match(jarEntry.getName())) {
					if (className.equals(stripClassName((jarEntry.getName())))) {
						byte[] classData = loadClassData(jarFile, jarEntry);
						clazz = defineClass(
								stripClassName((jarEntry.getName())),
								classData, 0, classData.length);		
					}
				}
			}
		}catch(IOException e){
			LOGGER.error(e.getMessage());
		}
		if(clazz == null){
			LOGGER.error("Class was not found.");
			throw new ClassNotFoundException(String.format("Class '%s' was not found in '%s'.", className, jarFileName));
		}
		cache.put(jarFileName + className, clazz);
		LOGGER.info("Added to classloader cache " + jarFileName + className + " class.");
		return clazz;
	}

	private String stripClassName(String className) {
		return className.substring(0, className.length() - 6);
	}

	private boolean match(String className) {
		return className.endsWith(".class");
	}

	private byte[] loadClassData(JarFile jarFile, JarEntry jarEntry)
			throws IOException {
		LOGGER.info("Start loading of class data.");
		long size = jarEntry.getSize();
		if (size == -1 || size == 0)
			return null;
		byte[] data = new byte[(int) size];
		InputStream in = jarFile.getInputStream(jarEntry);
		in.read(data);
		LOGGER.info("End loading of class data.");
		return data;
	

    }
}
