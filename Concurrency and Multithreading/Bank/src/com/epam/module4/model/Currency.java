package com.epam.module4.model;

public class Currency extends BankObject {

	public static final String FROM_CURRENCY = "from";
	public static final String TO_CURRENCY = "to";
	public static final String CURRENCY_COST = "cost";
	public static final String BANK_ID = "bankId";

	public Currency(Integer id, Integer bankId, CurrencyShortName from,
			CurrencyShortName to, Double cost) {
		super(id);
		properties.put(BANK_ID, bankId);
		properties.put(FROM_CURRENCY, from);
		properties.put(TO_CURRENCY, to);
		properties.put(CURRENCY_COST, cost);
	}

	public Integer getBankId() {
		return (Integer) properties.get(BANK_ID);
	}

	public void setBankId(Integer bankId) {
		properties.put(BANK_ID, bankId);
	}

	public CurrencyShortName getFromCurrency() {
		return (CurrencyShortName) properties.get(FROM_CURRENCY);
	}

	public void setFromCurrency(CurrencyShortName from) {
		properties.put(FROM_CURRENCY, from);
	}

	public CurrencyShortName getToCurrency() {
		return (CurrencyShortName) properties.get(TO_CURRENCY);
	}

	public void setToCurrency(CurrencyShortName from) {
		properties.put(TO_CURRENCY, from);
	}

	public Double getCurrencyCost() {
		return (Double) properties.get(CURRENCY_COST);
	}

	public void setBankId(Double cost) {
		properties.put(CURRENCY_COST, cost);
	}

	@Override
	public String toString() {
		return "Currency [getId()=" + getId() + ", getBankId()=" + getBankId()
				+ ", getFromCurrency()=" + getFromCurrency()
				+ ", getToCurrency()=" + getToCurrency()
				+ ", getCurrencyCost()=" + getCurrencyCost() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 13;
		int result = 11;
		result = prime * result + this.getId() + this.getBankId()
				+ this.getCurrencyCost().hashCode()
				+ this.getFromCurrency().hashCode()
				+ this.getToCurrency().hashCode();

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
		if (!(obj instanceof Currency)) {
			return false;
		}
		Currency other = (Currency) obj;

		return this.getId().equals(other.getId())
				&& this.getBankId().equals(other.getBankId())
				&& this.getFromCurrency() == other.getFromCurrency()
				&& this.getToCurrency() == other.getToCurrency()
				&& this.getCurrencyCost().equals(other.getCurrencyCost());
	}

}
