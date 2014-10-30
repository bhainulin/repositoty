package com.epam.module4.model;

public class Person extends BankObject {

	public static final String NICK_NAME = "nick";
	public static final String FIRST_NAME = "first";
	public static final String LAST_NAME = "last";

	public Person(Integer id, String nickName, String firstName, String lastName) {
		super(id);

		properties.put(NICK_NAME, nickName);
		properties.put(FIRST_NAME, firstName);
		properties.put(LAST_NAME, lastName);
	}

	public String getNickName() {
		return (String) properties.get(NICK_NAME);
	}

	public void setNickName(String name) {
		properties.put(NICK_NAME, name);
	}

	public String getFirstName() {
		return (String) properties.get(FIRST_NAME);
	}

	public void setFirstName(String name) {
		properties.put(FIRST_NAME, name);
	}

	public String getLastName() {
		return (String) properties.get(LAST_NAME);
	}

	public void setLastName(String name) {
		properties.put(LAST_NAME, name);
	}

	public String getCity() {
		return (String) properties.get(CITY);
	}

	public void setCity(String city) {
		properties.put(CITY, city);
	}

	public String getPhone() {
		return (String) properties.get(PHONE);
	}

	public void setPhone(String city) {
		properties.put(PHONE, city);
	}

	@Override
	public String toString() {
		return "Person [getId()=" + getId() + ", getNickName()="
				+ getNickName() + ", getFirstName()=" + getFirstName()
				+ ",getLastName()=" + getLastName() + ", getCity()=" + getCity()
				+ ",getPhone()=" + getPhone() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 7;
		int result = 4;
		result = prime * result + this.getId() + +this.getNickName().hashCode()
				+ this.getFirstName().hashCode()
				+ this.getLastName().hashCode()
				+ +(this.getCity() == null ? 0 : this.getCity().hashCode())
				+ +(this.getPhone() == null ? 0 : this.getPhone().hashCode());

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
		if (!(obj instanceof Person)) {
			return false;
		}
		Person other = (Person) obj;

		return this.getId().equals(other.getId())
				&& this.getNickName().equals(other.getNickName())
				&& this.getLastName().equals(other.getLastName())
				&& this.getFirstName().equals(other.getFirstName())
				&& ((this.getCity() == null && other.getCity() == null) || this
						.getCity().equals(other.getCity()))
				&& ((this.getPhone() == null && other == null) || (this
						.getPhone().equals(other.getPhone())));
	}
}
