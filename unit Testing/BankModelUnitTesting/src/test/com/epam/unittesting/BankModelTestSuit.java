package test.com.epam.unittesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.com.epam.unittesting.tests.AccountTest;
import test.com.epam.unittesting.tests.BankTests;
import test.com.epam.unittesting.tests.CurrencyTest;
import test.com.epam.unittesting.tests.PersonTest;

@RunWith(Suite.class)
@SuiteClasses({ BankTests.class, PersonTest.class, CurrencyTest.class,
		AccountTest.class })
public class BankModelTestSuit {

}
