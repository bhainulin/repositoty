package com.epam.unittesting.model;


public class Bank extends BankObject {

	public static final String BANK_NAME = "name";
	public static final String BANK_STREET = "street";
	public static final String BANK_BUILDING = "building";
	public static final String BANK_OFFICE = "office";

	public Bank(Integer id, String name, String city, String street,
			Integer building) {
		super(id);

		properties.put(BANK_NAME, name);
		properties.put(CITY, city);
		properties.put(BANK_STREET, street);
		properties.put(BANK_BUILDING, building);
	}

	public Bank(Integer id, String name, String city, String street,
			Integer building, Integer office, String phone) {
		this(id, name, city, street, building);
		properties.put(BANK_OFFICE, office);
		properties.put(PHONE, phone);
	}

	public String getName() {
		return (String) properties.get(BANK_NAME);
	}

	public void setName(String name) {
		properties.put(BANK_NAME, name);
	}

	public String getCity() {
		return (String) properties.get(CITY);
	}

	public void setCity(String city) {
		properties.put(CITY, city);
	}

	public String getStreet() {
		return (String) properties.get(BANK_STREET);
	}

	public void setStreet(String street) {
		properties.put(BANK_STREET, street);
	}

	public Integer getBuilding() {
		return (Integer) properties.get(BANK_BUILDING);
	}

	public void setBuilding(Integer building) {
		properties.put(BANK_BUILDING, building);
	}

	public Integer getOffice() {
		return (Integer) properties.get(BANK_OFFICE);
	}

	public void setOffice(Integer office) {
		properties.put(BANK_OFFICE, office);
	}

	public String getPhone() {
		return (String) properties.get(PHONE);
	}

	public void setPhone(String phone) {
		properties.put(PHONE, phone);
	}

	@Override
	public String toString() {
		return "Bank [getId()=" + getId() + ", getName()=" + getName()
				+ ", getCity()=" + getCity() + ", getStreet()=" + getStreet()
				+ ", getBuilding()=" + getBuilding() + ", getOffice()="
				+ getOffice() + ", getPhone()=" + getPhone() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.getId() + this.getBuilding()
				+ this.getName().hashCode() + this.getCity().hashCode()
				+ this.getStreet().hashCode()
				+ (this.getOffice() == null ? 0 : this.getOffice())
				+ (this.getPhone() == null ? 0 : this.getPhone().hashCode());

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

		return this.getId().equals(other.getId())
				&& this.getName().equals(other.getName())
				&& this.getCity().equals(other.getCity())
				&& this.getStreet().equals(other.getStreet())
				&& this.getBuilding().equals(other.getBuilding())
				&& ((this.getOffice() == null && other.getOffice() == null) || this
						.getOffice().equals(other.getOffice()))
				&& ((this.getPhone() == null && other == null) || (this
						.getPhone().equals(other.getPhone())));
	}

}
