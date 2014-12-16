package com.epam.jpa.client;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.epam.jpa.entitymanagers.DepartmentManager;
import com.epam.jpa.entitymanagers.EmployeeManager;
import com.epam.jpa.entitymanagers.EmployeePersonalInfoManager;
import com.epam.jpa.entitymanagers.ProjectManager;
import com.epam.jpa.entitymanagers.UnitManager;
import com.epam.jpa.model.Address;
import com.epam.jpa.model.Employee;
import com.epam.jpa.model.EmployeePersonalInfo;
import com.epam.jpa.model.EmployeeStatus;
import com.epam.jpa.model.Gender;
import com.epam.jpa.model.Project;
import com.epam.jpa.model.Unit;

public class TestAPI {

	public static void main(String[] args) throws NamingException {
		 testProject();

		 testUnit();

		 testEmployee();

		 testDepartmentManager();

		testPersonalInfo();

	}

	public static void testProject() throws NamingException {
		ProjectManager projectManager = getProjectManager();

		// add
		System.out.println("Add project starts");

		Project project1 = new Project(0, "First" + System.currentTimeMillis());
		Project project2 = new Project(0, "Second" + System.currentTimeMillis());

		projectManager.add(project1);
		projectManager.add(project2);

		System.out.println("Add project ends");

		// get all

		List<Project> projects = projectManager.getAll();

		System.out.println("All projects");

		for (Project project : projects) {
			System.out.println(project);
		}

		System.out.println("All ends");

		int existingId1 = projects.get(0).getId();

		// getById

		System.out.println("Get Project By Id");

		Project projectById = projectManager.get(existingId1);
		System.out.println(projectById);

		System.out.println("End Project By Id");

		// update

		System.out.println("Update project");

		projectById.setName("UPDATED");
		projectManager.update(projectById);

		projectById = projectManager.get(existingId1);
		System.out.println(projectById);

		System.out.println("Update project end");

		// delete

		System.out.println("Delete project");
		projectManager.delete(projectById.getId());

		projects = projectManager.getAll();

		for (Project project : projects) {
			System.out.println(project);
		}
		System.out.println("Delete project end");

	}

	public static void testDepartmentManager() throws NamingException {
		EmployeeManager employeeManager = getEmployeeManager();
		UnitManager unitManager = getUnitManager();
		DepartmentManager departmentManager = getDepartmentManager();

		List<Employee> employees = employeeManager.getAll();
		List<Unit> units = unitManager.getAll();

		int employeeId = employees.get(employees.size() - 1).getId();
		int unitId = units.get(units.size() - 1).getId();

		System.out.println("=================================");
		System.out.println("Assign employee to Unit started");
		departmentManager.addEmployeeToUnit(employeeId, unitId);

		Employee employee = employeeManager.get(employeeId);
		Unit unit = unitManager.get(unitId);
		// Unit unit = unitManager.get(unitId);

		System.out.println(employee);
		System.out.println(employee.getUnit());
		System.out.println(unit);

		System.out.println("Assign employee to Unit ended");
		System.out.println("=================================");

		ProjectManager projectManager = getProjectManager();
		List<Project> projects = projectManager.getAll();

		Project project = projects.get(projects.size() - 1);
		int projectId = project.getId();
		System.out.println("=================================");
		System.out.println("Assign employee to Project started");

		departmentManager.assignEmployeeToProject(employeeId, projectId);

		System.out.println("=================================");
		System.out.println("All Employees for Project");
		for (Employee empl : projectManager.getEmployees(projectId)) {
			System.out.println(empl);
		}
		System.out.println("All Employees for Project");
		System.out.println("=================================");

		System.out.println("=================================");
		System.out.println("All Projects for Employee");
		for (Project proj : employeeManager.getEmployeeProject(employeeId)) {
			System.out.println(proj);
		}
		System.out.println("All Projects for Employee");
		System.out.println("=================================");

		System.out.println("Assign employee to Project ended");
		System.out.println("=================================");

	}

	public static void testEmployee() throws NamingException {
		EmployeeManager employeeManager = getEmployeeManager();
		Employee employee1 = new Employee(0, "first", "second", "last",
				EmployeeStatus.FULL_TIME_EMPLOYEE, null);
		Address address = new Address("city", "country", "zip", "street");

		Employee employee2 = new Employee(0, "first1", "second1", "las1t",
				EmployeeStatus.PART_TIME_EMPLOYEE, address);

		System.out.println("=================================");
		System.out.println("Add employee started");
		employeeManager.add(employee1);
		employeeManager.add(employee2);

		System.out.println("Add employee ended");
		System.out.println("=================================");

		System.out.println("=================================");
		System.out.println("List of employees started");
		List<Employee> employees = employeeManager.getAll();

		System.out.println("All employees");

		for (Employee employee : employees) {
			System.out.println(employee);
		}
		System.out.println("List of employees ended");
		System.out.println("=================================");

		System.out.println("=================================");
		System.out.println("Get employee by ID started");

		int employeeId = employees.get(0).getId();
		employee1 = employeeManager.get(employeeId);
		System.out.println(employee1);

		System.out.println("Get employee by ID ended");
		System.out.println("=================================");

		System.out.println("=================================");
		System.out.println("Update by ID started");

		employee1.setFirst("UPDATED");
		employee1.setLast("UPDATED");

		employeeManager.update(employee1);

		employee1 = employeeManager.get(employeeId);
		System.out.println(employee1);

		System.out.println("Update employee by ID ended");
		System.out.println("=================================");

		System.out.println("=================================");
		System.out.println("Delete by ID started");

		employeeManager.delete(employeeId);

		employees = employeeManager.getAll();

		System.out.println("All employees");

		for (Employee employee : employees) {
			System.out.println(employee);
		}

		System.out.println("Update employee by ID ended");
		System.out.println("=================================");
	}

	public static void testPersonalInfo() throws NamingException {
		EmployeeManager employeeManager = getEmployeeManager();
		EmployeePersonalInfoManager personalManager = getPersonalManager();

		List<Employee> employees = employeeManager.getAll();

		int employeeId = employees.get(employees.size() - 1).getId();
		Employee employee = employeeManager.get(employeeId);

		EmployeePersonalInfo employeePersonalInfo = new EmployeePersonalInfo(
				employeeId, Gender.FEMALE, "8888", "2222", employee);

		System.out.println("=================================");
		System.out.println("Add employee personal info started");

		personalManager.add(employeePersonalInfo);

		System.out.println("Add employee personal info ended");
		System.out.println("=================================");

		System.out.println("=================================");
		System.out.println("View all employee personal info started");

		List<EmployeePersonalInfo> employeePersonalInfos = personalManager
				.getAll();

		for (EmployeePersonalInfo p : employeePersonalInfos) {
			System.out.println(p);
			System.out.println(p.getEmployee());
		}

		System.out.println("View all employee personal info ended");
		System.out.println("=================================");

		System.out.println("=================================");
		System.out.println("Get employee personal info started");
		int id = employeePersonalInfos.get(0).getId();

		EmployeePersonalInfo info = personalManager.get(id);

		System.out.println(info);
		System.out.println(info.getEmployee());

		System.out.println("Get employee personal info ended");
		System.out.println("=================================");

		System.out.println("=================================");
		System.out.println("Update employee personal info started");

		info.setPhone("Updated");
		personalManager.update(info);

		employeePersonalInfos = personalManager.getAll();

		for (EmployeePersonalInfo p : employeePersonalInfos) {
			System.out.println(p);
			System.out.println(p.getEmployee());
		}
		System.out.println("Update employee personal info ended");
		System.out.println("=================================");

		System.out.println("=================================");
		System.out.println("Delete employee personal info started");
		personalManager.delete(employeeId);

		employeePersonalInfos = personalManager.getAll();

		for (EmployeePersonalInfo p : employeePersonalInfos) {
			System.out.println(p);
			System.out.println(p.getEmployee());
		}
		System.out.println("Delete employee personal info ended");
		System.out.println("=================================");

	}

	public static void testUnit() throws NamingException {
		UnitManager unitManager = getUnitManager();

		// add
		System.out.println("Add unit starts");

		Unit unit1 = new Unit(0, "TestUnit1", System.currentTimeMillis()
				% 10000 + "");
		Unit unit2 = new Unit(0, "TestUnit2", System.currentTimeMillis()
				% 10000 + 1 + "");

		unitManager.add(unit1);
		unitManager.add(unit2);

		System.out.println("Add unit ends");

		// get all

		List<Unit> units = unitManager.getAll();

		System.out.println("All units");

		for (Unit unit : units) {
			System.out.println(unit);
		}

		System.out.println("All ends");

		int existingId1 = units.get(0).getId();

		// getById

		System.out.println("Get Unit By Id");

		Unit unitById = unitManager.get(existingId1);
		System.out.println(unitById);

		System.out.println("End Unit By Id");

		// update

		System.out.println("Update unit");

		unitById.setName("UPDATED");
		unitById.setCode("UPDATED");
		unitManager.update(unitById);

		unitById = unitManager.get(existingId1);
		System.out.println(unitById);

		System.out.println("Update unit end");

		// delete

		System.out.println("Delete unit");
		unitManager.delete(unitById.getId());

		units = unitManager.getAll();

		for (Unit unit : units) {
			System.out.println(unit);
		}
		System.out.println("Delete unit end");

	}

	public static ProjectManager getProjectManager() throws NamingException {

		return (ProjectManager) getContext()
				.lookup("jpaArtifact-1-SNAPSHOT/ProjectManagerBean!com.epam.jpa.entitymanagers.ProjectManager");
	}

	public static UnitManager getUnitManager() throws NamingException {

		return (UnitManager) getContext()
				.lookup("jpaArtifact-1-SNAPSHOT/UnitManagerBean!com.epam.jpa.entitymanagers.UnitManager");
	}

	public static EmployeeManager getEmployeeManager() throws NamingException {

		return (EmployeeManager) getContext()
				.lookup("jpaArtifact-1-SNAPSHOT/EmployeeManagerBean!com.epam.jpa.entitymanagers.EmployeeManager");
	}

	public static EmployeePersonalInfoManager getPersonalManager()
			throws NamingException {

		return (EmployeePersonalInfoManager) getContext()
				.lookup("jpaArtifact-1-SNAPSHOT/EmployeePersonalInfoManagerBean!com.epam.jpa.entitymanagers.EmployeePersonalInfoManager");
	}

	public static DepartmentManager getDepartmentManager()
			throws NamingException {

		return (DepartmentManager) getContext()
				.lookup("jpaArtifact-1-SNAPSHOT/DepartmentManagerBean!com.epam.jpa.entitymanagers.DepartmentManager");
	}

	private static Context getContext() throws NamingException {
		Hashtable<String, Object> p = new Hashtable<String, Object>();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		p.put("jboss.naming.client.ejb.context", true);
		p.put(Context.PROVIDER_URL, "remote://localhost:4447/");
		p.put(InitialContext.SECURITY_PRINCIPAL, "nika1");
		p.put(InitialContext.SECURITY_CREDENTIALS, "nika2");
		p.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT",
				"false");
		final Context context = new InitialContext(p);
		return context;
	}

}
