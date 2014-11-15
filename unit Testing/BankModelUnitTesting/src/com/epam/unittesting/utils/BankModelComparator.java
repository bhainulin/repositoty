package com.epam.unittesting.utils;

import java.util.Comparator;

import com.epam.unittesting.model.BankObject;

public class BankModelComparator<T extends BankObject> implements Comparator<T> {

	public static final int ASCENDING = 1;
	public static final int DESCENDING = -1;

	public BankModelComparator(String propety, int order) {
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
	public int compare(T obj1, T obj2) {
		if (!PropertyValueChecker.hasProperty(propety, obj1.getClass())) {
			throw new IllegalArgumentException(
					"Entity does not have such property.");
		}
		if (order != 1 && order != -1) {
			throw new IllegalArgumentException("Incorrect order.");
		}

		Object objProperty1 = obj1.getPropertyValue(propety);
		Object objProperty2 = obj2.getPropertyValue(propety);

		if (objProperty1 == objProperty2) {
			return 0;
		}

		if (objProperty1 == null) {
			return order;
		}

		if (objProperty2 == null) {
			return order * (-1);
		}

		if (objProperty1 instanceof Comparable
				&& objProperty2 instanceof Comparable) {
			Comparable comp1 = (Comparable) objProperty1;
			Comparable comp2 = (Comparable) objProperty2;

			return (order) * comp1.compareTo(comp2);
		}

		return 0;
	}

}
