package com.epam.bank.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblAccount")
public class Account implements Serializable {

	private static final long serialVersionUID = -7794639866732603154L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "personId", nullable = false)
	private int personId;
	
	@Column(name = "bankId", nullable = false)
	private int bankId;
	
	@Column(name = "currency", nullable = false)
	@Enumerated(EnumType.STRING)
	private CurrencyShortName currency;
	
	@Column(name = "amount", nullable = false)
	private double amount;

	public Account() {
	}

	public Account(int id, int personId, int bankId,
			CurrencyShortName currency, double amount) {
		super();
		this.id = id;
		this.personId = personId;
		this.bankId = bankId;
		this.currency = currency;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public CurrencyShortName getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyShortName currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", personId=" + personId + ", bankId="
				+ bankId + ", currency=" + currency + ", amount=" + amount
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + bankId;
		result = prime * result
				+ ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + id;
		result = prime * result + personId;
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
		if (Double.doubleToLongBits(amount) != Double
				.doubleToLongBits(other.amount)) {
			return false;
		}
		if (bankId != other.bankId) {
			return false;
		}
		if (currency != other.currency) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (personId != other.personId) {
			return false;
		}
		return true;
	}
}
