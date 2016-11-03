package domain;
/**
 * Created by syrovo01 on 31.10.2016.
 */
public class Employee {
	String name;
	int age;
	Double salary;
	Department department;
	int departmentID;
	String departmentName;

	public Employee(Department department, String name, Double salary, int age ){
		this.name = name;
		this.age = age;
		this.department = department;
		this.salary = salary;
		this.departmentID = department.getId();
		this.departmentName = department.getName();
	}

	public int getAgeRange(){
		return this.age / 10;
	}

	public int getAge() {
		return age;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public Double getSalary() {
		return salary;
	}
}
