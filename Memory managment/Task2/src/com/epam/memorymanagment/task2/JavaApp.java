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
    
    
    private static void methodA(Integer obj1){
    	obj1 = new Integer(30);
    }
    
    private static void methodB(int[] obj2){
    	obj2[0] = 150;
    }
    
    private static Integer methodC(Integer obj3){
    	obj3 = new Integer(80);
    	return obj3;
    }
    
    private static Integer methodD(int[] obj4){
    	obj4[0] = 500;
    	return 600;
    }
}
