package com.epam.ejb.client;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.ejb.EJBException;
import javax.naming.NamingException;

import com.epam.bank.model.Account;
import com.epam.bank.model.Bank;
import com.epam.bank.model.Currency;
import com.epam.bank.model.CurrencyShortName;
import com.epam.bank.model.Person;
import com.epam.ejb.client.entitymanagment.AccountManagerEntityHelper;
import com.epam.ejb.client.entitymanagment.BankManagerEntityHelper;
import com.epam.ejb.client.entitymanagment.CurrencyManagerEntityHelper;
import com.epam.ejb.client.entitymanagment.EntityManagerHelper;
import com.epam.ejb.client.entitymanagment.PersonManagerEntityHelper;
import com.epam.ejb.client.functionality.BankExchangeFunctionality;
import com.epam.ejb.client.functionality.BankTransferFunctionality;
import com.epam.ejb.client.util.BankModelBeanCreator;

public class Client {

	public static void main(String[] args) throws NamingException, IOException {
		Scanner sc = new Scanner(System.in);

		try {
			menu(sc);
		} finally {
			sc.close();
		}
	}

	public static final void showMainMenu() {
		System.out.println("1 - Banks");
		System.out.println("2 - Persons");
		System.out.println("3 - Accounts");
		System.out.println("4 - Currencies");
		System.out.println("5 - Transfer");
		System.out.println("6 - Exchange");

		System.out.println("0 - Exit");
	}

	public static final void showEntityMenu() {
		System.out.println("1 - All");
		System.out.println("2 - By Id");
		System.out.println("3 - Add");

		System.out.println("0 - Back");
	}

	public static Bank addBank(Scanner sc, Bank bank) {
		System.out.println("Input name:");
		String name = sc.next();
		System.out.println("Input city:");
		String city = sc.next();
		System.out.println("Input street:");
		String street = sc.next();
		System.out.println("Input building:");
		int building = sc.nextInt();
		System.out.println("Input office:");
		int office = sc.nextInt();
		System.out.println("Input phone:");
		String phone = sc.next();

		bank.setBuildig(building);
		bank.setCity(city);
		bank.setPhone(phone);
		bank.setName(name);
		bank.setOffice(office);
		bank.setStreet(street);

		return bank;
	}

	public static Person addPerson(Scanner sc, Person person) {
		System.out.println("Input nickname:");
		String nickname = sc.next();
		System.out.println("Input first name:");
		String first = sc.next();
		System.out.println("Input last name:");
		String last = sc.next();
		System.out.println("Input city:");
		String city = sc.next();
		System.out.println("Input phone:");
		String phone = sc.next();

		person.setNickName(nickname);
		person.setFirstName(first);
		person.setLastName(last);
		person.setCity(city);
		person.setPhone(phone);

		return person;
	}

	public static Account addAccount(Scanner sc, Account account) {
		System.out.println("Input person id:");
		int personId = sc.nextInt();
		System.out.println("Input bank id:");
		int bankId = sc.nextInt();
		System.out.println("Input currency:");
		String curr = sc.next();
		System.out.println("Input amount:");
		double city = sc.nextDouble();

		account.setPersonId(personId);
		account.setBankId(bankId);
		try {
			account.setCurrency(CurrencyShortName.valueOf(curr));
		} catch (Exception e) {
			System.out.println("Cannot be added!");
			System.out.println(e.getMessage());
			return null;
		}
		account.setAmount(city);

		return account;
	}

	public static Currency addCurrency(Scanner sc, Currency currency) {
		System.out.println("Input bank id:");
		int bankId = sc.nextInt();
		System.out.println("Input currency from:");
		String from = sc.next();
		System.out.println("Input currency to:");
		String to = sc.next();
		System.out.println("Input cost:");
		double cost = sc.nextDouble();

		currency.setBankId(bankId);
		try {
			currency.setFrom(CurrencyShortName.valueOf(from));
			currency.setTo(CurrencyShortName.valueOf(to));
		} catch (Exception e) {
			System.out.println("Cannot be added.");
			System.out.println(e.getMessage());
			return null;
		}
		currency.setCost(cost);

		return currency;
	}

	public static void menu(Scanner sc) throws NamingException, IOException {
		int selection = 10;
		while (selection != 0) {
			showMainMenu();

			selection = sc.nextInt();

			switch (selection) {
			case 1:
				bankMenu(sc);
				break;
			case 2:
				personMenu(sc);
				break;
			case 3:
				accountMenu(sc);
				break;
			case 4:
				currencynMenu(sc);
				break;
			case 5:
				transfer(sc);
				break;
			case 6:
				exchange(sc);
				break;

			}
		}
	}

	public static void showEntities(List<?> objects) {
		System.out.println("==========Entities==========");
		for (Object object : objects) {
			System.out.println(object);
		}
		System.out.println("==========Entities==========");
	}

	public static void showEntity(Object object) {
		System.out.println("==========Entity==========");
		System.out.println(object);
		System.out.println("==========Entity==========");
	}

	public static void bankMenu(Scanner sc) {
		int option = 10;
		BankManagerEntityHelper helper = EntityManagerHelper
				.getBankManagerEntityHelper();
		while (option != 0) {
			showEntityMenu();

			option = sc.nextInt();

			switch (option) {
			case 1:
				showEntities(helper.getAll());
				break;
			case 2:
				System.out.println("Input Id:");
				int id = sc.nextInt();
				showEntity(helper.get(id));
				break;
			case 3:
				Bank bank = new Bank();
				bank = addBank(sc, bank);
				helper.add(bank);
			}
		}
	}

	public static void personMenu(Scanner sc) {
		int option = 10;
		PersonManagerEntityHelper helper = EntityManagerHelper
				.getPersonManagerEntityHelper();
		while (option != 0) {
			showEntityMenu();

			option = sc.nextInt();

			switch (option) {
			case 1:
				showEntities(helper.getList());
				break;
			case 2:
				System.out.println("Input Id:");
				int id = sc.nextInt();
				showEntity(helper.get(id));
				break;
			case 3:
				Person person = new Person();
				person = addPerson(sc, person);
				helper.add(person);
			}
		}
	}

	public static void currencynMenu(Scanner sc) {
		int option = 10;
		CurrencyManagerEntityHelper helper = EntityManagerHelper
				.getCurrencyManagerEntityHelper();
		while (option != 0) {
			showEntityMenu();

			option = sc.nextInt();

			switch (option) {
			case 1:
				showEntities(helper.getAll());
				break;
			case 2:
				System.out.println("Input Id:");
				int id = sc.nextInt();
				showEntity(helper.get(id));
				break;
			case 3:
				Currency currency = new Currency();
				currency = addCurrency(sc, currency);
				if (currency != null) {
					try {
						helper.add(currency);
					} catch (Exception e) {
						System.out.println("Cannot be added!");
					}
				}
			}
		}
	}

	public static void accountMenu(Scanner sc) {
		int option = 10;
		AccountManagerEntityHelper helper = EntityManagerHelper
				.getAccountManagerEntityHelper();
		while (option != 0) {
			showEntityMenu();

			option = sc.nextInt();

			switch (option) {
			case 1:
				showEntities(helper.getAll());
				break;
			case 2:
				System.out.println("Input Id:");
				int id = sc.nextInt();
				showEntity(helper.get(id));
				break;
			case 3:
				Account account = new Account();
				account = addAccount(sc, account);
				if (account != null) {
					try {
						helper.add(account);
					} catch (Exception e) {
						System.out.println("Cannot be added!");
					}
				}
			}
		}
	}

	public static void exchange(Scanner sc) throws NamingException, IOException {

		AccountManagerEntityHelper accountHelper = EntityManagerHelper
				.getAccountManagerEntityHelper();
		CurrencyManagerEntityHelper currencyHelper = EntityManagerHelper
				.getCurrencyManagerEntityHelper();

		System.out.println("All accounts:");
		showEntities(accountHelper.getAll());
		System.out.println("Select Account Id:");
		int accountId = sc.nextInt();

		System.out.println("All currencies:");
		showEntities(currencyHelper.getAll());
		System.out.println("Select Currency Id:");
		int currencyId = sc.nextInt();

		BankExchangeFunctionality func = new BankExchangeFunctionality(
				BankModelBeanCreator.getBankOperationExcange());

		try {
			Account account = func.exchange(accountHelper.get(accountId),
					currencyHelper.get(currencyId));

			accountHelper.update(account);

			System.out.println("Succesfully exchanged!");
		} catch (EJBException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void transfer(Scanner sc) throws NamingException, IOException {

		AccountManagerEntityHelper accountHelper = EntityManagerHelper
				.getAccountManagerEntityHelper();
		CurrencyManagerEntityHelper currencyHelper = EntityManagerHelper
				.getCurrencyManagerEntityHelper();

		System.out.println("All accounts:");
		showEntities(accountHelper.getAll());
		System.out.println("Select From Account Id:");
		int from = sc.nextInt();
		System.out.println("Select To Account Id:");
		int to = sc.nextInt();

		System.out.println("All currencies:");
		showEntities(currencyHelper.getAll());
		System.out.println("Select Currency Id:");
		int currencyId = sc.nextInt();

		BankTransferFunctionality func = new BankTransferFunctionality(
				BankModelBeanCreator.getBankOperationTransfer());

		try {
			List<Account> accounts = func.transfer(accountHelper.get(from),
					accountHelper.get(to), currencyHelper.get(currencyId));
			accountHelper.update(accounts.get(0));
			accountHelper.update(accounts.get(1));

			System.out.println("Succesfully transfered!");
		} catch (EJBException e) {
			System.out.println(e.getMessage());
		}
	}

}
