package com.epam.bank;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.epam.bank.entitymanagers.BankManager;
import com.epam.bank.model.Bank;

public class Client {

	public static void main(String[] args) throws NamingException {
		BankManager bankManager = (BankManager) getContext()
				.lookup("ejb3-1.0-SNAPSHOT/BankManagerBean!com.epam.bank.entitymanagers.BankManager");

		List<Bank> banks = bankManager.getAll();
		for (Bank bank : banks) {
			System.out.println(bank);
		}

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

	/*
	 * public static ShoppingCart getCard() throws NamingException, IOException
	 * { return (ShoppingCart) getContext().lookup(
	 * "ejb3-1.0-SNAPSHOT/ShoppingCartBean!com.epam.shopping.ShoppingCart"); }
	 * 
	 * public static SearchFacade search() throws NamingException, IOException {
	 * return (SearchFacade) getContext().lookup(
	 * "ejb3-1.0-SNAPSHOT/SearchFacade!com.epam.search.SearchFacade"); }
	 * 
	 * public static HelloWorldRemote getHello() throws NamingException,
	 * IOException { return (HelloWorldRemote) getContext().lookup(
	 * "BankingSystem/HelloWorld!com.epam.hello.HelloWorldRemote"); }
	 */

}
