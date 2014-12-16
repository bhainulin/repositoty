package com.epam.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblPersonal")
public class EmployeePersonalInfo implements Serializable {

	private static final long serialVersionUID = 2072837224910074340L;

	@Id
	@Column(name = "id")
	private int id;

	@Enumerated(EnumType.ORDINAL)
	private Gender gender;

	private String phone;

	private String ssn;

	// @OneToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "Id", referencedColumnName = "id", insertable = false,
	// updatable = false)
	@OneToOne
	@JoinColumn(name="id")
	private Employee employee;

	public EmployeePersonalInfo() {
		super();
	}

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Gender getGender() {
		return gender;
	}



	public void setGender(Gender gender) {
		this.gender = gender;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getSsn() {
		return ssn;
	}



	public void setSsn(String ssn) {
		this.ssn = ssn;
	}



	public Employee getEmployee() {
		return employee;
	}



	public void setEmployee(Employee employee) {
		this.employee = employee;
	}



	public EmployeePersonalInfo(int id, Gender gender, String phone,
			String ssn, Employee employee) {
		super();
		this.id = id;
		this.gender = gender;
		this.phone = phone;
		this.ssn = ssn;
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "EmployeePersonalInfo [id=" + id + ", gender=" + gender
				+ ", phone=" + phone + ", ssn=" + ssn + ", employee=";
		// + employee + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		/*
		 * result = prime * result + ((employee == null) ? 0 :
		 * employee.hashCode());
		 */
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
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
		if (!(obj instanceof EmployeePersonalInfo)) {
			return false;
		}
		EmployeePersonalInfo other = (EmployeePersonalInfo) obj;
		if (employee == null) {
			if (other.employee != null) {
				return false;
			}
		} else if (!employee.equals(other.employee)) {
			return false;
		}
		if (gender != other.gender) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}
		if (ssn == null) {
			if (other.ssn != null) {
				return false;
			}
		} else if (!ssn.equals(other.ssn)) {
			return false;
		}
		return true;
	}
}
