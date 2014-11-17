package test.com.epam.unittesting.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.unittesting.dao.AccountDAO;
import com.epam.unittesting.dao.CurrencyDAO;
import com.epam.unittesting.model.Account;
import com.epam.unittesting.model.Currency;
import com.epam.unittesting.model.CurrencyShortName;
import com.epam.unittesting.service.BankOperations;

@RunWith(MockitoJUnitRunner.class)
public class BankOperationsTest {
	private CurrencyDAO currencyDAO = Mockito.mock(CurrencyDAO.class);
	private AccountDAO accountDAO = Mockito.mock(AccountDAO.class);

	private List<Account> accounts;
	private List<Currency> currencies;

	@Before
	public void init() throws SQLException {
		accounts = new ArrayList<Account>();
		accounts.add(new Account(1, 1, 1, CurrencyShortName.BYR, 1100000d));
		accounts.add(new Account(2, 1, 1, CurrencyShortName.USD, 200d));
		accounts.add(new Account(3, 1, 2, CurrencyShortName.EUR, 300d));
		accounts.add(new Account(4, 1, 1, CurrencyShortName.RUR, 3000d));
		accounts.add(new Account(5, 2, 1, CurrencyShortName.EUR, 30000d));

		currencies = new ArrayList<Currency>();
		currencies.add(new Currency(1, 1, CurrencyShortName.BYR,
				CurrencyShortName.USD, 11000d));

		Mockito.when(accountDAO.getAll()).thenReturn(accounts);
		Mockito.when(currencyDAO.getAll()).thenReturn(currencies);
		Mockito.when(accountDAO.get(15)).thenReturn(null);
		Mockito.when(accountDAO.get(1)).thenReturn(new Account(1, 1, 1, CurrencyShortName.BYR, 1100000d));
		Mockito.when(accountDAO.get(2)).thenReturn(new Account(2, 1, 1, CurrencyShortName.USD, 200d));
		Mockito.when(accountDAO.get(3)).thenReturn(new Account(3, 1, 2, CurrencyShortName.EUR, 300d));
		Mockito.when(accountDAO.get(4)).thenReturn(new Account(4, 1, 1, CurrencyShortName.RUR, 3000d));
		Mockito.when(accountDAO.get(5)).thenReturn(new Account(5, 2, 1, CurrencyShortName.EUR, 30000d));		
		Mockito.when(currencyDAO.get(15)).thenReturn(null);

	}

	@After
	public void destroy() {
		accounts = null;
		currencies = null;
	}

	@Test(timeout = 10000)
	public void findExchangeWithNoSuchCurrencyExchange() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		Currency currency = operation.findExchange(1, CurrencyShortName.USD,
				CurrencyShortName.RUR);
		assertNull(currency);
	}

	@Test(timeout = 10000)
	public void findExchangeWithNoSuchBankId() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		Currency currency = operation.findExchange(100, CurrencyShortName.USD,
				CurrencyShortName.BYR);
		assertNull(currency);
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public void findExchangeWithNullBankId() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		operation.findExchange(null, CurrencyShortName.USD,
				CurrencyShortName.BYR);
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public void findExchangeWithNullFrom() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		operation.findExchange(1, null, CurrencyShortName.BYR);
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public void findExchangeWithNullTo() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		operation.findExchange(1, CurrencyShortName.USD, null);
	}

	@Test(timeout = 10000)
	public void findExchangeCurrency() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		Currency currency = operation.findExchange(1, CurrencyShortName.BYR,
				CurrencyShortName.USD);
		checkCurrency(1, 1, CurrencyShortName.BYR, CurrencyShortName.USD,
				11000d, currency);
	}

	@Test(timeout = 10000)
	public void transferWithNoSuchExchange() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		Account account = operation.transfer(accounts.get(1), accounts.get(3));
		assertNull(account);
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public void transferWithNullTo() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		operation.transfer(null, accounts.get(1));
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public void transferWithNullFrom() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		operation.transfer(accounts.get(1), null);
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public void transferWithNonexistenrTo() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		operation.transfer(accounts.get(1), new Account(15, 1, 1,
				CurrencyShortName.BYR, 100d));
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public void transferWithNonexistenrFrom() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		operation.transfer(new Account(15, 1, 1,
				CurrencyShortName.BYR, 100d), accounts.get(1));
	}
	
	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public void transferFtomDifferentBanks() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		operation.transfer(accounts.get(0), accounts.get(2));
	}
	
	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public void transferFromDifferentPeople() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		operation.transfer(accounts.get(0), accounts.get(4));
	}
	
	@Test(timeout = 10000)
	public void transfer() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		Account account = operation.transfer(accounts.get(0), accounts.get(1));
		checkAccount(2, 1, 1, CurrencyShortName.USD, 300d, account);
	}
	
	@Test(timeout = 10000)
	public void exchangeWithNonexistentExchange() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		Account account = operation.exchange(accounts.get(1), CurrencyShortName.EUR);
		assertNull(account);
	}
	
	@Test(timeout = 10000, expected=IllegalArgumentException.class)
	public void exchangeWithNullAccount() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		operation.exchange(null, CurrencyShortName.EUR);
	}
	
	@Test(timeout = 10000, expected=IllegalArgumentException.class)
	public void exchangeWithNullToCurrency() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		operation.exchange(accounts.get(1), null);
	}
	
	@Test(timeout = 10000)
	public void exchange() throws SQLException {
		BankOperations operation = new BankOperations(currencyDAO, accountDAO);
		Account account = operation.exchange(accounts.get(0), CurrencyShortName.USD);
		checkAccount(1, 1, 1, CurrencyShortName.USD, 100d, account);
	}

	private void checkCurrency(Integer id, Integer bankId,
			CurrencyShortName from, CurrencyShortName to, Double cost,
			Currency actual) {
		checkProperty(id, actual.getId());
		checkProperty(bankId, actual.getBankId());
		checkProperty(from, actual.getFromCurrency());
		checkProperty(to, actual.getToCurrency());
		checkProperty(cost, actual.getCurrencyCost());
	}

	private void checkAccount(Integer id, Integer personId, Integer bankId,
			CurrencyShortName currency, Double cost, Account actual) {
		checkProperty(id, actual.getId());
		checkProperty(personId, actual.getPersonId());
		checkProperty(bankId, actual.getBankId());
		checkProperty(currency, actual.getCurrency());
		checkProperty(cost, actual.getAmount());
	}

	private void checkProperty(Object expected, Object actual) {
		assertEquals(expected, actual);
	}
}
