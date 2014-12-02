package com.epam.ejb.client.util;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.epam.bank.entitymanagers.AccountManager;
import com.epam.bank.entitymanagers.BankManager;
import com.epam.bank.entitymanagers.CurrencyManager;
import com.epam.bank.entitymanagers.PersonManager;
import com.epam.bank.session.BankOperationExchange;
import com.epam.bank.session.BankOperationTransfer;

public class BankModelBeanCreator {

	public static BankManager getBankManager() {
		try {
			return (BankManager) getContext()
					.lookup("ejbTask1-2.0-SNAPSHOT/BankManagerBean!com.epam.bank.entitymanagers.BankManager");
		} catch (NamingException e) {
			return null;
		}
	}

	public static AccountManager getAccountManager() {
		try {
			return (AccountManager) getContext()
					.lookup("ejbTask1-2.0-SNAPSHOT/AccountManagerBean!com.epam.bank.entitymanagers.AccountManager");
		} catch (NamingException e) {
			return null;
		}
	}

	public static CurrencyManager getCurrencyManager() {
		try {
			return (CurrencyManager) getContext()
					.lookup("ejbTask1-2.0-SNAPSHOT/CurrencyManagerBean!com.epam.bank.entitymanagers.CurrencyManager");
		} catch (NamingException e) {
			return null;
		}
	}

	public static PersonManager getPersonManager() {
		try {
			return (PersonManager) getContext()
					.lookup("ejbTask1-2.0-SNAPSHOT/PersonManagerBean!com.epam.bank.entitymanagers.PersonManager");
		} catch (NamingException e) {
			return null;
		}
	}

	public static BankOperationExchange getBankOperationExcange()
			throws NamingException, IOException {
		return (BankOperationExchange) getContext()
				.lookup("ejbTask1-2.0-SNAPSHOT/BankOperationExchangeBean!com.epam.bank.session.BankOperationExchangeLocal");
	}

	public static BankOperationTransfer getBankOperationTransfer()
			throws NamingException, IOException {
		return (BankOperationTransfer) getContext()
				.lookup("ejbTask1-2.0-SNAPSHOT/BankOperationTransferBean!com.epam.bank.session.BankOperationTransfer");
	}

	private static Context getContext() throws NamingException {
		Hashtable<String, Object> p = new Hashtable<String, Object>();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		p.put("jboss.naming.client.ejb.context", true);
		p.put(Context.PROVIDER_URL, "remote://localhost:4447/");
		p.put(InitialContext.SECURITY_PRINCIPAL, "nika1");
		p.put(InitialContext.SECURITY_CREDENTIALS, "nika2");
		p.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT",
				"false");
		final Context context = new InitialContext(p);
		return context;
	}

}
