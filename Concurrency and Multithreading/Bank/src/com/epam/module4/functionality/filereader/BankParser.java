package com.epam.module4.functionality.filereader;

import com.epam.module4.model.Bank;
import com.epam.module4.model.BankObject;

public class BankParser extends AbstractParser {

	// Bank@1@Priorbank@Minsk@Lobanka@13@14@+375293958542

	@Override
	public BankObject parse(String[] values) {
		if (values.length != 8) {
			throw new IllegalArgumentException("Incorrect Bank row");
		}

		for (int i = 1; i < 6; i++) {
			if (!validateNotEmptyValue(values[i])) {
				throw new IllegalArgumentException("Requied field is empty.");
			}
		}

		Integer id = parseInteger(values[1]);
		Integer building = parseInteger(values[5]);

		Bank bank = new Bank(id, values[2], values[3], values[4], building);
		String officeS = values[6];
		if (officeS != null && !officeS.isEmpty()) {
			Integer office = Integer.parseInt(officeS);
			bank.setOffice(office);
		}

		String phone = values[7];
		if (phone != null && !phone.isEmpty()) {
			bank.setPhone(phone);
		}
		System.out.println(bank.toString());
		return bank;
	}
}
