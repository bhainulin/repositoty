package test.com.epam.unittesting.tests;

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

import com.epam.unittesting.dao.BankDAO;
import com.epam.unittesting.model.Bank;
import com.epam.unittesting.service.bank.BankFunctionality;
import com.epam.unittesting.utils.BankModelComparator;

@RunWith(MockitoJUnitRunner.class)
public class BankTests {

	private static BankDAO daoMock;
	private static List<Bank> bankList;

	@BeforeClass
	public static void init() throws SQLException {
		bankList = new ArrayList<Bank>();
		bankList.add(new Bank(2, "Bank2", "City1", "street", 99, 6, "+1111"));
		bankList.add(new Bank(3, "Bank1", "City3", "street", 17));
		bankList.add(new Bank(1, "Bank3", "City2", "street", 56, null, "+2222"));
		daoMock = Mockito.mock(BankDAO.class);
	}
	
	@AfterClass
	public static void destroy() throws SQLException {
		bankList = null;
		daoMock = null;
	}

	@Test(timeout = 10000)
	public final void getAll() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(bankList);
		BankFunctionality functionality = new BankFunctionality(daoMock);
		List<Bank> actual = functionality.getAll();
		assertEquals(3, actual.size());
		checkElements(2, "Bank2", "City1", "street", 99, 6, "+1111",
				actual.get(0));
		checkElements(3, "Bank1", "City3", "street", 17, null, null,
				actual.get(1));
		checkElements(1, "Bank3", "City2", "street", 56, null, "+2222",
				actual.get(2));
	}

	@Test(timeout = 10000)
	public final void getAllFromEmptyDB() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(null);
		BankFunctionality functionality = new BankFunctionality(daoMock);
		List<Bank> actual = functionality.getAll();
		assertEquals(0, actual.size());
	}

	@Test(timeout = 10000)
	public final void getByKey() throws SQLException {
		Mockito.when(daoMock.get(1)).thenReturn(
				new Bank(1, "Bank3", "City2", "street", 56, null, "+2222"));
		BankFunctionality functionality = new BankFunctionality(daoMock);
		checkElements(1, "Bank3", "City2", "street", 56, null, "+2222",
				functionality.get(1));
	}

	@Test(timeout = 10000)
	public final void getByNonExistingKey() throws SQLException {
		Mockito.when(daoMock.get(1)).thenReturn(null);
		BankFunctionality functionality = new BankFunctionality(daoMock);
		assertNull(functionality.get(1));
	}

	@Test(timeout = 10000)
	public final void searchByStreet() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(bankList);
		BankFunctionality functionality = new BankFunctionality(daoMock);
		List<Bank> actual = functionality.search(Bank.BANK_STREET, "street");

		assertEquals(3, actual.size());
		checkElements(2, "Bank2", "City1", "street", 99, 6, "+1111",
				actual.get(0));
		checkElements(3, "Bank1", "City3", "street", 17, null, null,
				actual.get(1));
		checkElements(1, "Bank3", "City2", "street", 56, null, "+2222",
				actual.get(2));
	}

	@Test(timeout = 10000)
	public final void searchByCity() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(bankList);
		BankFunctionality functionality = new BankFunctionality(daoMock);
		List<Bank> actual = functionality.search(Bank.CITY, "City3");

		assertEquals(1, actual.size());
		checkElements(3, "Bank1", "City3", "street", 17, null, null,
				actual.get(0));
	}

	@Test(timeout = 10000)
	public final void searchByPhone() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(bankList);
		BankFunctionality functionality = new BankFunctionality(daoMock);
		List<Bank> actual = functionality.search(Bank.PHONE, "+2222");

		assertEquals(1, actual.size());
		checkElements(1, "Bank3", "City2", "street", 56, null, "+2222",
				actual.get(0));
	}

	@Test(timeout = 10000)
	public final void searchByBuilding() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(bankList);
		BankFunctionality functionality = new BankFunctionality(daoMock);
		List<Bank> actual = functionality.search(Bank.BANK_BUILDING,
				new Integer(56));

		assertEquals(1, actual.size());
		checkElements(1, "Bank3", "City2", "street", 56, null, "+2222",
				actual.get(0));
	}
	
	@Test(timeout = 10000)
	public final void searchByNull() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(bankList);
		BankFunctionality functionality = new BankFunctionality(daoMock);
		List<Bank> actual = functionality.search(Bank.BANK_OFFICE,
				null);

		assertEquals(2, actual.size());
		checkElements(1, "Bank3", "City2", "street", 56, null, "+2222",
				actual.get(1));
		checkElements(3, "Bank1", "City3", "street", 17, null, null,
				actual.get(0));
	}

	@Test(timeout = 10000)
	public final void searchByNonexistentValue() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(bankList);
		BankFunctionality functionality = new BankFunctionality(daoMock);
		List<Bank> actual = functionality.search(Bank.BANK_BUILDING,
				new Integer(150));

		assertEquals(0, actual.size());
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public final void searchByNonexistentProperty() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(bankList);
		BankFunctionality functionality = new BankFunctionality(daoMock);
		functionality.search("Nonexistent", new Integer(150));
	}

	@Test(timeout = 10000)
	public final void orderByCity() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(bankList);
		BankFunctionality functionality = new BankFunctionality(daoMock);
		List<Bank> actual = functionality.order(Bank.CITY,
				BankModelComparator.ASCENDING);

		assertEquals(3, actual.size());
		checkElements(2, "Bank2", "City1", "street", 99, 6, "+1111",
				actual.get(0));
		checkElements(1, "Bank3", "City2", "street", 56, null, "+2222",
				actual.get(1));
		checkElements(3, "Bank1", "City3", "street", 17, null, null,
				actual.get(2));
	}

	@Test(timeout = 10000)
	public final void orderByPhone() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(bankList);
		BankFunctionality functionality = new BankFunctionality(daoMock);
		List<Bank> actual = functionality.order(Bank.PHONE,
				BankModelComparator.ASCENDING);

		assertEquals(3, actual.size());
		checkElements(2, "Bank2", "City1", "street", 99, 6, "+1111",
				actual.get(0));
		checkElements(1, "Bank3", "City2", "street", 56, null, "+2222",
				actual.get(1));
		checkElements(3, "Bank1", "City3", "street", 17, null, null,
				actual.get(2));
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public final void orderByNonexistingProperty() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(bankList);
		BankFunctionality functionality = new BankFunctionality(daoMock);
		functionality.order("Nonexistent", BankModelComparator.ASCENDING);
	}

	@Test(timeout = 10000)
	public final void orderByDesc() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(bankList);
		BankFunctionality functionality = new BankFunctionality(daoMock);

		List<Bank> actual = functionality.order(Bank.BANK_NAME,
				BankModelComparator.DESCENDING);

		assertEquals(3, actual.size());
		checkElements(2, "Bank2", "City1", "street", 99, 6, "+1111",
				actual.get(1));
		checkElements(1, "Bank3", "City2", "street", 56, null, "+2222",
				actual.get(0));
		checkElements(3, "Bank1", "City3", "street", 17, null, null,
				actual.get(2));
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public final void orderByIncorrectOrder() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(bankList);
		BankFunctionality functionality = new BankFunctionality(daoMock);

		functionality.order(Bank.BANK_NAME, 12);

	}
	


	private void checkElements(Integer id, String name, String city,
			String street, Integer building, Integer office, String phone,
			Bank actual) {
		checkProperty(id, actual.getId());
		checkProperty(name, actual.getName());
		checkProperty(city, actual.getCity());
		checkProperty(street, actual.getStreet());
		checkProperty(building, actual.getBuilding());
		checkProperty(office, actual.getOffice());
		checkProperty(phone, actual.getPhone());
	}

	private void checkProperty(Object expected, Object actual) {
		assertEquals(expected, actual);
	}

}
