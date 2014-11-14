package com.epam.unittesting.utils;

import java.util.Comparator;

import com.epam.unittesting.model.Bank;

public class BankComparator implements Comparator<Bank>{
	
	public static final int ASCENDING = 1;
	public static final int DESCENDING = -1;
	
	public BankComparator(String propety, int order) {
		this.propety = propety;
		this.order = order;
	}

	private String propety;
	private int order;
	
	public String getPropety() {
		return propety;
	}

	public void setPropety(String propety) {
		this.propety = propety;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int compare(Bank bank1, Bank bank2) {
		if(!Bank.hasProperty(propety)){
			throw new IllegalArgumentException("Bank entity does not have such property.");
		}
		if(order !=1 && order != -1){
			throw new IllegalArgumentException("Incorrect order.");
		}
		
		Object bankProperty1 = bank1.getPropertyValue(propety);
		Object bankProperty2 = bank2.getPropertyValue(propety);
		
		if(bankProperty1 == bankProperty2){
			return 0;
		}
		
		if(bankProperty1 == null){
			return order*(-1);
		}
		
		if(bankProperty2 == null){
			return order;
		}
		
		if(bankProperty1 instanceof Comparable && bankProperty2 instanceof Comparable){
			Comparable comp1 = (Comparable)bankProperty1;
			Comparable comp2 = (Comparable)bankProperty2;
			
			return comp1.compareTo(comp2);
		}
		
		return 0;
	}

}
