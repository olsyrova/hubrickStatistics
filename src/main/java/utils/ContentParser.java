package refactored.utils;

import refactored.domain.Department;
import refactored.domain.Employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * class responsible for parsing information needed for statistics calculations from source files and representing it as a list of Employee objects
 * Created by olgasyrova on 30.10.16.
 */
public class ContentParser {

	public static final String DELIMITER = ",";
	// source files
	public static final String DEPARTMENTS = "departments.csv";
	public static final String EMPLOYEES = "employees.csv";
	public static final String AGES = "ages.csv";
	private static String path = "";

	private static ContentParser contentParser = null;

	private ContentParser() {
	}

	public static ContentParser getInstance() {
		if (contentParser == null) {
			contentParser = new ContentParser();
		}
		return contentParser;
	}

	public void setResourcesPath(String path) {
		if (!"".equalsIgnoreCase(path) && !path.endsWith(File.separator)) {
			path = path + File.separator;
		}
		ContentParser.path = path;
	}

	/**
	 * reads data stream from a file
	 * @param filename
	 * @return list of lines
	 * @throws IOException
	 */
	private List<String> readFile(String filename) throws IOException {
		List<String> lines;
		try (Stream<String> stream = Files.lines(Paths.get(filename))) {

			lines = stream.collect(Collectors.toList());
		} catch (FileNotFoundException e) {
			throw new IOException("check if provided directory contain source files");
		}
		return lines;
	}

	/**
	 * parses information about departments
	 * @return list of department names sorted alphabetically
	 * @throws IOException
	 */
	private List<String> parseDepartments() throws IOException {
		return readFile(path + DEPARTMENTS).stream().sorted().collect(Collectors.toList());
	}

	/**
	 * parses information about employees ages
	 * @return map containing employee name as a key and age as a value
	 * @throws IOException
	 */
	private Map<String, Integer> parseAges() throws IOException {
		Map<String, Integer> ages = new HashMap<>();
		readFile(path + AGES)
				.stream()
				.filter(line -> Validator.isValidLineFormat(line, 2))
				.forEach(
						s -> {
							String[] values = s.split(DELIMITER);
							ages.put(values[0], Integer.parseInt(values[1]));
						}
				);
		return ages;
	}

	/**
	 * parses employees information from a file and creates list of employees based on the information parsed from all source files
	 * @return list of employees
	 * @throws IOException
	 */
	public List<Employee> parseEmployees() throws IOException {
		Map<String, Integer> ages = parseAges();
		List<String> departments = parseDepartments();
		List<Employee> employees = new ArrayList<>();
		try {
			readFile(path + EMPLOYEES)
					.stream()
					.filter(line -> (Validator.isValidLineFormat(line, 4) && Validator.isValidDepartment(line)))
					.forEach(
							s -> {
								String[] infos = s.split(DELIMITER);
								Department department = new Department(Integer.parseInt(infos[0]), departments.get(Integer.parseInt(infos[0]) - 1));
								employees.add(new Employee(department, infos[1], Double.parseDouble(infos[3]), ages.get(infos[1])));
							}
					);
		} catch (NumberFormatException nfe) {
			throw new NumberFormatException("fix number format and rerun the application : " + nfe);
		}

		return employees;
	}
}
