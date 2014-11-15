package test.com.epam.unittesting.tests;

import static com.epam.unittesting.model.CurrencyShortName.BYR;
import static com.epam.unittesting.model.CurrencyShortName.EUR;
import static com.epam.unittesting.model.CurrencyShortName.RUR;
import static com.epam.unittesting.model.CurrencyShortName.USD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.unittesting.dao.AccountDAO;
import com.epam.unittesting.model.Account;
import com.epam.unittesting.model.Currency;
import com.epam.unittesting.model.CurrencyShortName;
import com.epam.unittesting.service.bank.AccountFunctionality;
import com.epam.unittesting.utils.BankModelComparator;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
	private static AccountDAO accountDAO;
	private static List<Account> accountList;

	@BeforeClass
	public static void init() throws SQLException {
		accountList = new ArrayList<Account>();

		accountList.add(new Account(1, 1, 2, BYR, 100000d));
		accountList.add(new Account(2, 2, 2, USD, 100d));
		accountList.add(new Account(3, 3, 3, EUR, 200d));
		accountList.add(new Account(4, 3, 3, RUR, 500d));
		accountDAO = Mockito.mock(AccountDAO.class);
	}

	@AfterClass
	public static void destroy() throws SQLException {
		accountList = null;
		accountDAO = null;
	}

	@Test(timeout = 10000)
	public final void getAll() throws SQLException {
		Mockito.when(accountDAO.getAll()).thenReturn(accountList);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		List<Account> actual = functionality.getAll();
		assertEquals(4, actual.size());
		checkElements(1, 1, 2, BYR, 100000d, actual.get(0));
		checkElements(2, 2, 2, USD, 100d, actual.get(1));
		checkElements(3, 3, 3, EUR, 200d, actual.get(2));
		checkElements(4, 3, 3, RUR, 500d, actual.get(3));
	}

	@Test(timeout = 10000)
	public final void getAllFromEmptyDB() throws SQLException {
		Mockito.when(accountDAO.getAll()).thenReturn(null);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		List<Account> actual = functionality.getAll();
		assertEquals(0, actual.size());
	}

	@Test(timeout = 10000)
	public final void getByKey() throws SQLException {
		Mockito.when(accountDAO.get(1)).thenReturn(
				new Account(1, 1, 2, BYR, 100000d));
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		checkElements(1, 1, 2, BYR, 100000d, functionality.get(1));
	}

	@Test(timeout = 10000)
	public final void getByNonExistingKey() throws SQLException {
		Mockito.when(accountDAO.get(1)).thenReturn(null);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		assertNull(functionality.get(1));
	}

	@Test(timeout = 10000)
	public final void searchByCurrency() throws SQLException {
		Mockito.when(accountDAO.getAll()).thenReturn(accountList);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		List<Account> actual = functionality.search(Account.CURRENCY, BYR);
		assertEquals(1, actual.size());
		checkElements(1, 1, 2, BYR, 100000d, actual.get(0));
	}

	@Test(timeout = 10000)
	public final void searchByAmount() throws SQLException {
		Mockito.when(accountDAO.getAll()).thenReturn(accountList);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		List<Account> actual = functionality.search(Account.AMOUNT, new Double(
				100));
		assertEquals(1, actual.size());
		checkElements(2, 2, 2, USD, 100d, actual.get(0));
	}

	@Test(timeout = 10000)
	public final void searchByBankId() throws SQLException {
		Mockito.when(accountDAO.getAll()).thenReturn(accountList);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		List<Account> actual = functionality.search(Account.BANK_ID, 2);
		assertEquals(2, actual.size());
		checkElements(1, 1, 2, BYR, 100000d, actual.get(0));
		checkElements(2, 2, 2, USD, 100d, actual.get(1));
	}

	@Test(timeout = 10000)
	public final void searchByPersonId() throws SQLException {
		Mockito.when(accountDAO.getAll()).thenReturn(accountList);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		List<Account> actual = functionality.search(Account.PERSON_ID,
				new Integer(3));
		assertEquals(2, actual.size());
		checkElements(3, 3, 3, EUR, 200d, actual.get(0));
		checkElements(4, 3, 3, RUR, 500d, actual.get(1));
	}

	@Test(timeout = 10000)
	public final void searchByNull() throws SQLException {
		Mockito.when(accountDAO.getAll()).thenReturn(accountList);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		List<Account> actual = functionality.search(Account.CURRENCY, null);
		assertEquals(0, actual.size());
	}

	@Test(timeout = 10000)
	public final void searchByNonexistentValue() throws SQLException {
		Mockito.when(accountDAO.getAll()).thenReturn(accountList);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		List<Account> actual = functionality.search(Account.PERSON_ID, 100);
		assertEquals(0, actual.size());
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public final void searchByNonexistentProperty() throws SQLException {
		Mockito.when(accountDAO.getAll()).thenReturn(accountList);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		functionality.search("Nonexistent", new Integer(150));
	}

	@Test(timeout = 10000)
	public final void orderByCurrency() throws SQLException {
		Mockito.when(accountDAO.getAll()).thenReturn(accountList);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		List<Account> actual = functionality.order(Account.CURRENCY,
				BankModelComparator.ASCENDING);

		assertEquals(4, actual.size());
		checkElements(1, 1, 2, BYR, 100000d, actual.get(2));
		checkElements(2, 2, 2, USD, 100d, actual.get(0));
		checkElements(3, 3, 3, EUR, 200d, actual.get(1));
		checkElements(4, 3, 3, RUR, 500d, actual.get(3));
	}

	@Test(timeout = 10000)
	public final void orderByAmount() throws SQLException {
		Mockito.when(accountDAO.getAll()).thenReturn(accountList);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		List<Account> actual = functionality.order(Currency.CURRENCY_COST,
				BankModelComparator.ASCENDING);
		assertEquals(4, actual.size());
		checkElements(1, 1, 2, BYR, 100000d, actual.get(3));
		checkElements(2, 2, 2, USD, 100d, actual.get(0));
		checkElements(3, 3, 3, EUR, 200d, actual.get(1));
		checkElements(4, 3, 3, RUR, 500d, actual.get(2));
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public final void orderByNonexistingProperty() throws SQLException {
		Mockito.when(accountDAO.getAll()).thenReturn(accountList);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		functionality.order("Nonexistent", BankModelComparator.ASCENDING);
	}

	@Test(timeout = 10000)
	public final void orderByDesc() throws SQLException {
		Mockito.when(accountDAO.getAll()).thenReturn(accountList);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);

		List<Account> actual = functionality.order(Account.CURRENCY,
				BankModelComparator.DESCENDING);
		assertEquals(4, actual.size());

		checkElements(1, 1, 2, BYR, 100000d, actual.get(1));
		checkElements(2, 2, 2, USD, 100d, actual.get(3));
		checkElements(3, 3, 3, EUR, 200d, actual.get(2));
		checkElements(4, 3, 3, RUR, 500d, actual.get(0));
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public final void orderByIncorrectOrder() throws SQLException {
		Mockito.when(accountDAO.getAll()).thenReturn(accountList);
		AccountFunctionality functionality = new AccountFunctionality(
				accountDAO);
		functionality.order(Account.PERSON_ID, 12);
	}

	private void checkElements(Integer id, Integer personId, Integer bankId,
			CurrencyShortName currency, Double cost, Account actual) {
		checkProperty(id, actual.getId());
		checkProperty(personId, actual.getBankId());
		checkProperty(bankId, actual.getBankId());
		checkProperty(currency, actual.getCurrency());
		checkProperty(cost, actual.getAmount());
	}

	private void checkProperty(Object expected, Object actual) {
		assertEquals(expected, actual);
	}
}