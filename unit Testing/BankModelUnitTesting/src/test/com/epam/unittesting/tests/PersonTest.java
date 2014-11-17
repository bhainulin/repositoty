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

import com.epam.unittesting.dao.PersonDAO;
import com.epam.unittesting.model.Person;
import com.epam.unittesting.service.bank.PersonFunctionality;
import com.epam.unittesting.utils.BankModelComparator;

@RunWith(MockitoJUnitRunner.class)
public class PersonTest {
	private static PersonDAO daoMock;
	private static List<Person> personList;

	@BeforeClass
	public static void init() throws SQLException {
		personList = new ArrayList<Person>();

		personList.add(new Person(1, "nick1", "first1", "last", null, null));
		personList.add(new Person(2, "nick2", "first2", "last", "city2",
				"phone"));
		personList.add(new Person(3, "nick3", "first3", "last", "city3", null));

		daoMock = Mockito.mock(PersonDAO.class);
	}

	@AfterClass
	public static void destroy() throws SQLException {
		personList = null;
		daoMock = null;
	}

	@Test(timeout = 10000)
	public final void getAll() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(personList);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		List<Person> actual = functionality.getAll();
		assertEquals(3, actual.size());
		checkElements(1, "nick1", "first1", "last", null, null, actual.get(0));
		checkElements(2, "nick2", "first2", "last", "city2", "phone",
				actual.get(1));
		checkElements(3, "nick3", "first3", "last", "city3", null,
				actual.get(2));
	}

	@Test(timeout = 10000)
	public final void getAllFromEmptyDB() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(null);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		List<Person> actual = functionality.getAll();
		assertEquals(0, actual.size());
	}

	@Test(timeout = 10000)
	public final void getByKey() throws SQLException {
		Mockito.when(daoMock.get(1)).thenReturn(
				new Person(1, "nick1", "first1", "last", null, null));
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		checkElements(1, "nick1", "first1", "last", null, null,
				functionality.get(1));
	}

	@Test(timeout = 10000)
	public final void getByNonExistingKey() throws SQLException {
		Mockito.when(daoMock.get(1)).thenReturn(null);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		assertNull(functionality.get(1));
	}

	@Test(timeout = 10000)
	public final void searchByLast() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(personList);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		List<Person> actual = functionality.search(Person.LAST_NAME, "last");

		assertEquals(3, actual.size());
		checkElements(1, "nick1", "first1", "last", null, null, actual.get(0));
		checkElements(2, "nick2", "first2", "last", "city2", "phone",
				actual.get(1));
		checkElements(3, "nick3", "first3", "last", "city3", null,
				actual.get(2));
	}

	@Test(timeout = 10000)
	public final void searchByNick() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(personList);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		List<Person> actual = functionality.search(Person.NICK_NAME, "nick2");

		assertEquals(1, actual.size());
		checkElements(2, "nick2", "first2", "last", "city2", "phone",
				actual.get(0));
	}

	@Test(timeout = 10000)
	public final void searchByCity() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(personList);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		List<Person> actual = functionality.search(Person.CITY, "city3");

		assertEquals(1, actual.size());
		checkElements(3, "nick3", "first3", "last", "city3", null,
				actual.get(0));
	}

	@Test(timeout = 10000)
	public final void searchById() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(personList);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		List<Person> actual = functionality.search(Person.OBJECT_ID,
				new Integer(2));
		assertEquals(1, actual.size());
		checkElements(2, "nick2", "first2", "last", "city2", "phone",
				actual.get(0));
		;
	}

	@Test(timeout = 10000)
	public final void searchByNull() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(personList);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		List<Person> actual = functionality.search(Person.PHONE, null);

		assertEquals(2, actual.size());
		checkElements(1, "nick1", "first1", "last", null, null, actual.get(0));
		checkElements(3, "nick3", "first3", "last", "city3", null,
				actual.get(1));
	}

	@Test(timeout = 10000)
	public final void searchByNonexistentValue() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(personList);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		List<Person> actual = functionality.search(Person.FIRST_NAME,
				"Nonexistent");
		assertEquals(0, actual.size());
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public final void searchByNonexistentProperty() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(personList);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		functionality.search("Nonexistent", new Integer(150));
	}

	@Test(timeout = 10000)
	public final void orderByCity() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(personList);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		List<Person> actual = functionality.order(Person.CITY,
				BankModelComparator.ASCENDING);

		assertEquals(3, actual.size());
		checkElements(1, "nick1", "first1", "last", null, null, actual.get(2));
		checkElements(2, "nick2", "first2", "last", "city2", "phone",
				actual.get(0));
		checkElements(3, "nick3", "first3", "last", "city3", null,
				actual.get(1));
	}

	@Test(timeout = 10000)
	public final void orderByFirstName() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(personList);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		List<Person> actual = functionality.order(Person.FIRST_NAME,
				BankModelComparator.ASCENDING);

		assertEquals(3, actual.size());
		checkElements(1, "nick1", "first1", "last", null, null, actual.get(0));
		checkElements(2, "nick2", "first2", "last", "city2", "phone",
				actual.get(1));
		checkElements(3, "nick3", "first3", "last", "city3", null,
				actual.get(2));
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public final void orderByNonexistingProperty() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(personList);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		functionality.order("Nonexistent", BankModelComparator.ASCENDING);
	}

	@Test(timeout = 10000)
	public final void orderByDesc() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(personList);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);

		List<Person> actual = functionality.order(Person.NICK_NAME,
				BankModelComparator.DESCENDING);

		assertEquals(3, actual.size());
		checkElements(1, "nick1", "first1", "last", null, null, actual.get(2));
		checkElements(2, "nick2", "first2", "last", "city2", "phone",
				actual.get(1));
		checkElements(3, "nick3", "first3", "last", "city3", null,
				actual.get(0));
	}

	@Test(timeout = 10000, expected = IllegalArgumentException.class)
	public final void orderByIncorrectOrder() throws SQLException {
		Mockito.when(daoMock.getAll()).thenReturn(personList);
		PersonFunctionality functionality = new PersonFunctionality(daoMock);
		functionality.order(Person.LAST_NAME, 12);

	}

	private void checkElements(Integer id, String nick, String first,
			String last, String city, String phone, Person actual) {
		checkProperty(id, actual.getId());
		checkProperty(nick, actual.getNickName());
		checkProperty(first, actual.getFirstName());
		checkProperty(last, actual.getLastName());
		checkProperty(city, actual.getCity());
		checkProperty(phone, actual.getPhone());
	}

	private void checkProperty(Object expected, Object actual) {
		assertEquals(expected, actual);
	}

}