package com.epam.module4.model;

public class Account extends BankObject {

	public static final String PERSON_ID = "personId";
	public static final String BANK_ID = "bankId";
	public static final String AMOUNT = "amount";
	public static final String CURRENCY = "sum";

	public Account(Integer id, Integer personId, Integer bankId,
			CurrencyShortName currency, Double amount) {
		super(id);

		properties.put(BANK_ID, bankId);
		properties.put(PERSON_ID, personId);
		properties.put(CURRENCY, currency);
		properties.put(AMOUNT, amount);
	}

	public Integer getPersonId() {
		return (Integer) properties.get(PERSON_ID);
	}

	public void setPersonId(Integer personId) {
		properties.put(PERSON_ID, personId);
	}

	public Integer getBankId() {
		return (Integer) properties.get(BANK_ID);
	}

	public void setBankId(Integer bankId) {
		properties.put(BANK_ID, bankId);
	}

	public CurrencyShortName getCurrency() {
		return (CurrencyShortName) properties.get(CURRENCY);
	}

	public void setCurrency(CurrencyShortName currencyShortName) {
		properties.put(CURRENCY, currencyShortName);
	}

	public Double getAmount() {
		return (Double) properties.get(AMOUNT);
	}

	public void setAmount(Double amount) {
		properties.put(AMOUNT, amount);
	}

	@Override
	public String toString() {
		return "Account [getId()=" + getId() + ", getPersonId()="
				+ getPersonId() + ", getBankId()=" + getBankId()
				+ ", getCurrency()=" + getCurrency() + ", getAmount()="
				+ getAmount() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 17;
		int result = 2;
		result = prime * result + this.getId() + this.getBankId()
				+ this.getPersonId() + +this.getAmount().hashCode()
				+ this.getCurrency().hashCode();

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Account)) {
			return false;
		}
		Account other = (Account) obj;

		return this.getId().equals(other.getId())
				&& this.getBankId().equals(other.getBankId())
				&& this.getPersonId().equals(other.getPersonId())
				&& this.getCurrency() == other.getCurrency()
				&& this.getAmount().equals(other.getAmount());
	}
}
