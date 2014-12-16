package com.epam.jpa.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblEmployee")
public class Employee implements Serializable {
	private static final long serialVersionUID = -3712065591951923594L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(nullable = false)
	private String first;

	@Column(nullable = true)
	private String middle;

	@Column(nullable = false)
	private String last;

	@Enumerated(EnumType.STRING)
	private EmployeeStatus status;

	@Embedded
	private Address address;

	// @OneToOne(mappedBy="employee", fetch=FetchType.LAZY)
	// @JoinColumn(name = "Id", insertable = false, updatable = false)
	// @OneToOne(cascade = CascadeType.ALL)
	// @PrimaryKeyJoinColumn
	@OneToOne(mappedBy = "employee")
	private EmployeePersonalInfo personalInfo;

	@ManyToMany
	@JoinTable(name = "tblemployee_project", joinColumns = @JoinColumn(name = "employeeId"), inverseJoinColumns = @JoinColumn(name = "projectId"))
	private Collection<Project> projects;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "unitId")
	private Unit unit;

	public Employee() {
	}

	public Employee(int id, String first, String middle, String last,
			EmployeeStatus status, Address address, Unit unit) {
		super();
		this.id = id;
		this.first = first;
		this.middle = middle;
		this.last = last;
		this.status = status;
		this.address = address;
		this.unit = unit;
	}

	public Employee(int id, String first, String middle, String last,
			EmployeeStatus status, Address address) {
		super();
		this.id = id;
		this.first = first;
		this.middle = middle;
		this.last = last;
		this.status = status;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public EmployeeStatus getStatus() {
		return status;
	}

	public void setStatus(EmployeeStatus status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EmployeePersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(EmployeePersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public Collection<Project> getProjects() {
		return projects;
	}

	public void setProjects(Collection<Project> projects) {
		this.projects = projects;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		String value = "Employee [id=" + id + ", first=" + first + ", middle="
				+ middle + ", last=" + last + ", status=" + status;
		if (address != null) {
			value = value + " address=" + address;
		}

		/*
		 * + ", address=" + address + ", personalInfo=" + personalInfo +"]";
		 */
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((middle == null) ? 0 : middle.hashCode());
		/*
		 * result = prime * result + ((personalInfo == null) ? 0 :
		 * personalInfo.hashCode()); result = prime * result + ((status == null)
		 * ? 0 : status.hashCode());
		 */
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee other = (Employee) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (first == null) {
			if (other.first != null) {
				return false;
			}
		} else if (!first.equals(other.first)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (last == null) {
			if (other.last != null) {
				return false;
			}
		} else if (!last.equals(other.last)) {
			return false;
		}
		if (middle == null) {
			if (other.middle != null) {
				return false;
			}
		} else if (!middle.equals(other.middle)) {
			return false;
		}
		/*
		 * if (personalInfo == null) { if (other.personalInfo != null) { return
		 * false; } } else if (!personalInfo.equals(other.personalInfo)) {
		 * return false; } if (projects == null) { if (other.projects != null) {
		 * return false; } } else if (!projects.equals(other.projects)) { return
		 * false; }
		 */
		if (status != other.status) {
			return false;
		}
		if (unit == null) {
			if (other.unit != null) {
				return false;
			}
		} else if (!unit.equals(other.unit)) {
			return false;
		}
		return true;
	}

}
