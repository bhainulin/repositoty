package com.epam.module4.functionality.filereader;

import com.epam.module4.model.BankObject;
import com.epam.module4.model.CurrencyShortName;

public abstract class AbstractParser {
	public boolean validateNotEmptyValue(String value) {
		if (value == null || value.isEmpty()) {
			return false;
		}
		return true;
	}

	public Integer parseInteger(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Incorrect integer value.");
		}
	}
	
	public Double parseDouble(String value) {
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Incorrect double value.");
		}
	}
	
	public CurrencyShortName parseCurrencyName(String value) {
		try {
			return CurrencyShortName.valueOf(value);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Incorrect currency short name.");
		}
	}
	
	public abstract BankObject parse(String[] values);
}
