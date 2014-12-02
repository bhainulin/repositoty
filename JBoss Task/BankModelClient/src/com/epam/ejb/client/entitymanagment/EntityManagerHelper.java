package com.epam.ejb.client.entitymanagment;

import static com.epam.ejb.client.util.BankModelBeanCreator.getAccountManager;
import static com.epam.ejb.client.util.BankModelBeanCreator.getBankManager;
import static com.epam.ejb.client.util.BankModelBeanCreator.getCurrencyManager;
import static com.epam.ejb.client.util.BankModelBeanCreator.getPersonManager;

public class EntityManagerHelper {

	public static class BankManagerHolder {
		public static final BankManagerEntityHelper BANK_MANAGER_ENTITY_HELPER = new BankManagerEntityHelper(
				getBankManager());
	}

	public static class PersonManagerHolder {
		public static final PersonManagerEntityHelper PERSON_MANAGER_ENTITY_HELPER = new PersonManagerEntityHelper(
				getPersonManager());
	}

	public static class AccountManagerHolder {
		public static final AccountManagerEntityHelper ACCOUNT_MANAGER_ENTITY_HELPER = new AccountManagerEntityHelper(
				getAccountManager());
	}

	public static class CurrencyManagerHolder {
		public static final CurrencyManagerEntityHelper CURRENCY_MANAGER_ENTITY_HELPER = new CurrencyManagerEntityHelper(
				getCurrencyManager());
	}

	public static BankManagerEntityHelper getBankManagerEntityHelper() {
		return BankManagerHolder.BANK_MANAGER_ENTITY_HELPER;
	}

	public static CurrencyManagerEntityHelper getCurrencyManagerEntityHelper() {
		return CurrencyManagerHolder.CURRENCY_MANAGER_ENTITY_HELPER;
	}

	public static AccountManagerEntityHelper getAccountManagerEntityHelper() {
		return AccountManagerHolder.ACCOUNT_MANAGER_ENTITY_HELPER;
	}

	public static PersonManagerEntityHelper getPersonManagerEntityHelper() {
		return PersonManagerHolder.PERSON_MANAGER_ENTITY_HELPER;
	}
}
