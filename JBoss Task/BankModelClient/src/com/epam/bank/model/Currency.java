package com.epam.bank.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblCurrency")
public class Currency implements Serializable {
	private static final long serialVersionUID = -2761645811461673538L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "from", nullable = false)
	private CurrencyShortName from;
	
	@Column(name = "to", nullable = false)
	private CurrencyShortName to;
	
	@Column(name = "bankId", nullable = false)
	private int bankId;
	
	@Column(name = "cost", nullable = false)
	private double cost;

	public Currency() {
	}

	public Currency(int id, CurrencyShortName from, CurrencyShortName to,
			int bankId, double cost) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.bankId = bankId;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CurrencyShortName getFrom() {
		return from;
	}

	public void setFrom(CurrencyShortName from) {
		this.from = from;
	}

	public CurrencyShortName getTo() {
		return to;
	}

	public void setTo(CurrencyShortName to) {
		this.to = to;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Currency [id=" + id + ", from=" + from + ", to=" + to
				+ ", bankId=" + bankId + ", cost=" + cost + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bankId;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + id;
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		if (bankId != other.bankId) {
			return false;
		}
		if (Double.doubleToLongBits(cost) != Double
				.doubleToLongBits(other.cost)) {
			return false;
		}
		if (from != other.from) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (to != other.to) {
			return false;
		}
		return true;
	}

}
