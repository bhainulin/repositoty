package com.epam.bank.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import com.epam.bank.exception.IncorrectParametersException;
import com.epam.bank.exception.MissingParametersException;
import com.epam.bank.model.Account;
import com.epam.bank.model.Currency;

@Stateful
@Remote(BankOperationTransfer.class)
public class BankOperationTransferBean implements BankOperationTransfer {

	public BankOperationTransferBean() {
	}

	private List<Account> accounts = new ArrayList<Account>(2);

	@Override
	public void transfer(Account from, Account to, Currency currency)
			throws MissingParametersException, IncorrectParametersException {
		if (from == null || to == null || currency == null) {
			throw new MissingParametersException("Incorrect input parameters.");
		}

		// cannot transfer between different people
		if (from.getPersonId() != to.getPersonId()) {
			throw new IncorrectParametersException(
					"Cannot transfer between different people.");
		}

		// cannot transfer between different banks
		if (from.getBankId() != to.getBankId()) {
			throw new IncorrectParametersException(
					"Cannot transfer between different banks.");
		}

		if (currency.getBankId() != to.getBankId()) {
			throw new IncorrectParametersException(
					"Currency exchahge from different bank.");
		}
		
		if(currency.getFrom() != from.getCurrency()){
			throw new IncorrectParametersException(
					"From currency is not correct.");
		}
		
		if(currency.getTo() != to.getCurrency()){
			throw new IncorrectParametersException(
					"To currency is not correct.");
		}
		

		Double amount = from.getAmount() / currency.getCost();

		from.setAmount(0d);
		to.setAmount(to.getAmount() + amount);
		
		accounts.add(from);
		accounts.add(to);
	}
	
	
	@Override
	@Remove
	public List<Account> getAccounts() {
		return accounts;
	}

}
