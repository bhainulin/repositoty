package com.epam.bank.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tblBank")
@NamedQueries({
		@NamedQuery(name = Bank.QUERY_FIND_ALL, query = "SELECT g FROM Bank g ORDER BY g.name ASC"),
		@NamedQuery(name = Bank.QUERY_FIND_BY_NAME, query = "SELECT g FROM Bank g WHERE g.name = :name") })
public class Bank implements Serializable {
	private static final long serialVersionUID = -853737423557347002L;

	public static final String QUERY_FIND_ALL = "Bank.findAll";
	public static final String QUERY_FIND_BY_NAME = "Bank.findByName";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "building", nullable = false)
	private int buildig;

	@Column(name = "office", nullable = true)
	private Integer office;

	@Column(name = "phone", nullable = true)
	private String phone;

	public Bank() {
	}

	public int getId() {
		return id;
	}

	public Bank(int id, String name, String city, String street, int buildig) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.street = street;
		this.buildig = buildig;
	}

	public Bank(int id, String name, String city, String street, int buildig,
			Integer office, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.street = street;
		this.buildig = buildig;
		this.office = office;
		this.phone = phone;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getBuildig() {
		return buildig;
	}

	public void setBuildig(int buildig) {
		this.buildig = buildig;
	}

	public Integer getOffice() {
		return office;
	}

	public void setOffice(Integer office) {
		this.office = office;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Bank [id=" + id + ", name=" + name + ", city=" + city
				+ ", street=" + street + ", buildig=" + buildig + ", office="
				+ office + ", phone=" + phone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + buildig;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((office == null) ? 0 : office.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		if (!(obj instanceof Bank)) {
			return false;
		}
		Bank other = (Bank) obj;
		if (buildig != other.buildig) {
			return false;
		}
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (office != other.office) {
			return false;
		}
		if (phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}
		if (street == null) {
			if (other.street != null) {
				return false;
			}
		} else if (!street.equals(other.street)) {
			return false;
		}
		return true;
	}

}
