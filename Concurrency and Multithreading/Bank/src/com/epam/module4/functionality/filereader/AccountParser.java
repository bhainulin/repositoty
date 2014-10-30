package com.epam.module4.functionality.filereader;

import com.epam.module4.model.Account;
import com.epam.module4.model.BankObject;
import com.epam.module4.model.CurrencyShortName;

public class AccountParser extends AbstractParser {
	
	//Account@1@1@1@100@USD

	@Override
	public BankObject parse(String[] values) {
		if (values.length != 6) {
			throw new IllegalArgumentException("Incorrect Account row");
		}

		for (int i = 1; i < 6; i++) {
			if (!validateNotEmptyValue(values[i])) {
				throw new IllegalArgumentException("Requied field is empty.");
			}
		}

		Integer id = parseInteger(values[1]);
		Integer personId = parseInteger(values[2]);
		Integer bankId = parseInteger(values[3]);

		Double amount = parseDouble(values[4]);
		CurrencyShortName currency = parseCurrencyName(values[5]);

		Account account = new Account(id, personId, bankId, currency, amount);

		System.out.println(account.toString());

		return account;
	}

}
