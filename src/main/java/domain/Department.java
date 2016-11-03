package domain;

/**
 * Created by syrovo01 on 02.11.2016.
 */
public class Department {
	int id;
	String name;

	public Department (int id, String name){
		this.id = id;
		this.name = name;

	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
