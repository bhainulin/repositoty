package com.epam.module4.functionality.filereader;

import com.epam.module4.model.Currency;
import com.epam.module4.model.CurrencyShortName;

public class CurrencyParser extends AbstractParser {
	
	//Currency@1@1@USD@EUR@1,13
	
	@Override
	public Currency parse(String[] values) {
		if (values.length != 6) {
			throw new IllegalArgumentException("Incorrect Currency row");
		}

		for (int i = 1; i < 6; i++) {
			if (!validateNotEmptyValue(values[i])) {
				throw new IllegalArgumentException("Requied field is empty.");
			}
		}

		Integer id = parseInteger(values[1]);
		Integer bankId = parseInteger(values[2]);		
		CurrencyShortName from = parseCurrencyName(values[3]);
		CurrencyShortName to = parseCurrencyName(values[4]);		
		Double cost = parseDouble(values[5]);
		
		Currency currency = new Currency(id, bankId, from, to, cost);

		System.out.println(currency.toString());

		return currency;
	}
}
