package test.com.epam.unittesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.com.epam.unittesting.tests.BankModelTesting;

@RunWith(Suite.class)
@SuiteClasses({BankModelTesting.class})
public class BankModelTestSuit {

}
