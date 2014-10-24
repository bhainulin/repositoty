package com.sap.com.task6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JavaApp {
	public static void main(String[] args) throws IOException {
		long start = 0;
		long end;
		try {
			start = System.currentTimeMillis();
			initiateOutOfHeapMemory();
		} catch (OutOfMemoryError error) {
			end = System.currentTimeMillis();
			System.out.println(error + " takes: " + (end - start) + "ms");
		}
		
		try {
			start = System.currentTimeMillis();
			initiateStakOverflow();
		} catch (StackOverflowError error) {
			end = System.currentTimeMillis();
			System.out.println(error + " takes: " + (end - start) + "ms");
		}
	}

	//Данный метод инициирует OutOfMemoryError из-за переполнения Хипа.
	//В данном методе происходит построчное чтение файла, размер которого больше,
	//чем размер Heap.
	private static void initiateOutOfHeapMemory() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(
				"D:\\Task6.txt"));
		String line = null;
		String text = null;
		while ((line = reader.readLine()) != null) {
			text = text + line;
		}
	}
	
	//В данном методе инициируется StackOverflowError. Это происходит из-за того,
	//создается объект класса A, который содержит в себе свойство, типа A, которое
	//пытается создаться во время создания объекта.
	private static void initiateStakOverflow() throws IOException {
		A a = new A();
		a.toString();
	}
}

class A
{
    A a = new A();
}
