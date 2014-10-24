package com.epam.memorymanagment.task2;

public class JavaApp {

	public static void main(String[] args) {
		Integer obj1 = 50;

		methodA(obj1);
		System.out.println("Obj1: " + obj1);

		int[] obj2 = new int[1];
		obj2[0] = 800;

		methodB(obj2);
		System.out.println("Obj2: " + obj2[0]);

		Integer obj3 = 20;

		System.out.println("method C returns: " + methodC(obj3));
		System.out.println("Obj3: " + obj3);

		int[] obj4 = new int[1];
		obj2[0] = 900;

		System.out.println("method D returns: " + methodD(obj4));
		System.out.println("Obj3: " + obj4[0]);
	}

	// Передача параметра по значению.
	// В метод передается ссылка на объект obj1. Затем этой ссылке присваивается
	// значение вновь созданного объекта.
	// После выполнения данного метода значение obj1 в главном методе не
	// поменяется.
	private static void methodA(Integer obj1) {
		obj1 = new Integer(30);
	}

	// Передача параметра по ссылке.
	// В метод передается ссылка на obj2. Затем в методе меняется значение
	// одного из элементов массива.
	// После выполнения данного метода obj2[0] будет иметь значение, присвоенное
	// в методе, так как ссылка не поменялась, а поменялись
	// только данные которые доступны в данном адресном пространсве.
	private static void methodB(int[] obj2) {
		obj2[0] = 150;
	}

	// Данный метод аналогичен методу "methodA()" за исключением того, что в
	// результате выполнения вернёт ссылку на вновь созданный объект.
	private static Integer methodC(Integer obj3) {
		obj3 = new Integer(80);
		return obj3;
	}

	//Данный метод аналогичен методу "methodB()" за исключением того, что в
	// результате выполнения вернёт вновь созданный объект.
	private static Integer methodD(int[] obj4) {
		obj4[0] = 500;
		return 600;
	}
}
