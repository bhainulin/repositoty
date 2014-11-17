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

import com.epam.unittesting.dao.CurrencyDAO;
import com.epam.unittesting.model.Currency;
import com.epam.unittesting.model.CurrencyShortName;
import com.epam.unittesting.service.bank.CurrencyFunctionality;
import com.epam.unittesting.utils.BankModelComparator;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyTest {
	private static CurrencyDAO currencyDAO;
	private static List<Currency> currencyList;

	@BeforeClass
	public static void init() throws SQLException {
		currencyList = new ArrayList<Currency>();

		currencyList.add(new Currency(1, 1, BYR, USD, 11000d));
		currencyList.add(new Currency(2, 1, RUR, USD, 50d));
		currencyList.add(new Currency(3, 2, BYR, USD, 11000d));
		currencyList.add(new Currency(4, 2, RUR, EUR, 60d));
		currencyDAO = Mockito.mock(CurrencyDAO.class);
	}

	@AfterClass
	public static void destroy() throws SQLException {
		currencyList = null;
		currencyDAO = null;
	}

	@Test(timeout = 10000)
	public final void getAll() throws SQLException {
		Mockito.when(currencyDAO.getAll()).thenReturn(currencyList);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		List<Currency> actual = functionality.getAll();
		assertEquals(4, actual.size());
		checkElements(1, 1, BYR, USD, 11000d, actual.get(0));
		checkElements(2, 1, RUR, USD, 50d, actual.get(1));
		checkElements(3, 2, BYR, USD, 11000d, actual.get(2));
		checkElements(4, 2, RUR, EUR, 60d, actual.get(3));
	}

	@Test(timeout = 10000)
	public final void getAllFromEmptyDB() throws SQLException {
		Mockito.when(currencyDAO.getAll()).thenReturn(null);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		List<Currency> actual = functionality.getAll();
		assertEquals(0, actual.size());
	}

	@Test(timeout = 10000)
	public final void getByKey() throws SQLException {
		Mockito.when(currencyDAO.get(1)).thenReturn(
				new Currency(1, 1, BYR, USD, 11000d));
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		checkElements(1, 1, BYR, USD, 11000d, functionality.get(1));
	}

	@Test(timeout = 10000)
	public final void getByNonExistingKey() throws SQLException {
		Mockito.when(currencyDAO.get(1)).thenReturn(null);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		assertNull(functionality.get(1));
	}

	@Test(timeout = 10000)
	public final void searchByFromCurrency() throws SQLException {
		Mockito.when(currencyDAO.getAll()).thenReturn(currencyList);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		List<Currency> actual = functionality.search(Currency.FROM_CURRENCY,
				CurrencyShortName.BYR);
		assertEquals(2, actual.size());
		checkElements(1, 1, BYR, USD, 11000d, actual.get(0));
		checkElements(3, 2, BYR, USD, 11000d, actual.get(1));
	}

	@Test(timeout = 10000)
	public final void searchByCurrencyCost() throws SQLException {
		Mockito.when(currencyDAO.getAll()).thenReturn(currencyList);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		List<Currency> actual = functionality.search(Currency.CURRENCY_COST,
				new Double(11000));
		assertEquals(2, actual.size());
		checkElements(1, 1, BYR, USD, 11000d, actual.get(0));
		checkElements(3, 2, BYR, USD, 11000d, actual.get(1));
	}

	@Test(timeout = 10000)
	public final void searchByBankId() throws SQLException {
		Mockito.when(currencyDAO.getAll()).thenReturn(currencyList);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		List<Currency> actual = functionality.search(Currency.BANK_ID, 1);
		assertEquals(2, actual.size());
		checkElements(1, 1, BYR, USD, 11000d, actual.get(0));
		checkElements(2, 1, RUR, USD, 50d, actual.get(1));
	}

	@Test(timeout = 10000)
	public final void searchById() throws SQLException {
		Mockito.when(currencyDAO.getAll()).thenReturn(currencyList);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		List<Currency> actual = functionality.search(Currency.OBJECT_ID,
				new Integer(2));
		assertEquals(1, actual.size());
		checkElements(2, 1, RUR, USD, 50d, actual.get(0));
		;
	}

	@Test(timeout = 10000)
	public final void searchByNull() throws SQLException {
		Mockito.when(currencyDAO.getAll()).thenReturn(currencyList);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		List<Currency> actual = functionality.search(Currency.CURRENCY_COST,
				null);
		assertEquals(0, actual.size());
	}

	@Test(timeout = 10000)
	public final void searchByNonexistentValue() throws SQLException {
		Mockito.when(currencyDAO.getAll()).thenReturn(currencyList);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		List<Currency> actual = functionality.search(Currency.BANK_ID, 100);
		assertEquals(0, actual.size());
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public final void searchByNonexistentProperty() throws SQLException {
		Mockito.when(currencyDAO.getAll()).thenReturn(currencyList);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		functionality.search("Nonexistent", new Integer(150));
	}

	@Test(timeout = 10000)
	public final void orderByCity() throws SQLException {
		Mockito.when(currencyDAO.getAll()).thenReturn(currencyList);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		List<Currency> actual = functionality.order(Currency.FROM_CURRENCY,
				BankModelComparator.ASCENDING);

		assertEquals(4, actual.size());
		checkElements(1, 1, BYR, USD, 11000d, actual.get(0));
		checkElements(2, 1, RUR, USD, 50d, actual.get(2));
		checkElements(3, 2, BYR, USD, 11000d, actual.get(1));
		checkElements(4, 2, RUR, EUR, 60d, actual.get(3));
	}

	@Test(timeout = 10000)
	public final void orderByCurrencyCost() throws SQLException {
		Mockito.when(currencyDAO.getAll()).thenReturn(currencyList);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		List<Currency> actual = functionality.order(Currency.CURRENCY_COST,
				BankModelComparator.ASCENDING);
		assertEquals(4, actual.size());
		checkElements(1, 1, BYR, USD, 11000d, actual.get(2));
		checkElements(2, 1, RUR, USD, 50d, actual.get(0));
		checkElements(3, 2, BYR, USD, 11000d, actual.get(3));
		checkElements(4, 2, RUR, EUR, 60d, actual.get(1));
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public final void orderByNonexistingProperty() throws SQLException {
		Mockito.when(currencyDAO.getAll()).thenReturn(currencyList);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		functionality.order("Nonexistent", BankModelComparator.ASCENDING);
	}

	@Test(timeout = 10000)
	public final void orderByDesc() throws SQLException {
		Mockito.when(currencyDAO.getAll()).thenReturn(currencyList);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);

		List<Currency> actual = functionality.order(Currency.TO_CURRENCY,
				BankModelComparator.DESCENDING);
		assertEquals(4, actual.size());
		checkElements(1, 1, BYR, USD, 11000d, actual.get(1));
		checkElements(2, 1, RUR, USD, 50d, actual.get(3));
		checkElements(3, 2, BYR, USD, 11000d, actual.get(2));
		checkElements(4, 2, RUR, EUR, 60d, actual.get(0));
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public final void orderByIncorrectOrder() throws SQLException {
		Mockito.when(currencyDAO.getAll()).thenReturn(currencyList);
		CurrencyFunctionality functionality = new CurrencyFunctionality(
				currencyDAO);
		functionality.order(Currency.FROM_CURRENCY, 12);

	}

	private void checkElements(Integer id, Integer bankId,
			CurrencyShortName from, CurrencyShortName to, Double cost,
			Currency actual) {
		checkProperty(id, actual.getId());
		checkProperty(bankId, actual.getBankId());
		checkProperty(from, actual.getFromCurrency());
		checkProperty(to, actual.getToCurrency());
		checkProperty(cost, actual.getCurrencyCost());
	}

	private void checkProperty(Object expected, Object actual) {
		assertEquals(expected, actual);
	}
}