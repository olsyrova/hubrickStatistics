package refactored.statistics;

import refactored.domain.Employee;
import refactored.utils.MathUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * this class is used for statistics calculations only
 * Created by syrovo01 on 02.11.2016.
 */
public class StatisticsCalculator {

	/**
	 * calculates median income by department
	 * @param employees - list of employees created from source file
	 * @return map, containing department name as a key and median income as a value
	 */
	public static Map<String, Double> calculateMedianIncomeByDepartment(List<Employee> employees){
		return getEmployeesByDepartment(employees).entrySet()
				.stream()
				.collect(
						Collectors.toMap(
								Map.Entry::getKey,
								value -> MathUtils.calculateMedian(value.getValue().stream().map(Employee::getSalary).collect(Collectors.toList())),
								(k,v) -> { throw new RuntimeException(String.format("Duplicate key %s", k));},
								TreeMap::new)
						);
	}

	/**
	 * calculates 95th percentile income by department
	 * @param employees - list of employees created from source file
	 * @return map, containing department name as a key and 95th percentile income as a value
	 */
	public static Map<String, Double> calculate95PercentileIncomeByDepartment(List<Employee> employees){
		return getEmployeesByDepartment(employees).entrySet()
				.stream()
				.collect(
						Collectors.toMap(
								Map.Entry::getKey,
								value -> MathUtils.calculate95thPercentile(value.getValue().stream().map(Employee::getSalary).collect(Collectors.toList())),
								(k,v) -> { throw new RuntimeException(String.format("Duplicate key %s", k));},
								TreeMap::new)
						);

	}

	/**
	 * calculates median employee age by department
	 * @param employees - list of employees created from source file
	 * @return map, containing department name as a key and median employee age as a value
	 */
	public static Map<String, Double> calculateMedianEmployeeAgeByDepartment(List<Employee> employees){

		return getEmployeesByDepartment(employees).entrySet()
				.stream()
				.collect(
						Collectors.toMap(
								Map.Entry::getKey,
								value -> MathUtils.calculateMedian(value.getValue().stream().map(Employee::getAge).map(number -> (double) number).collect(Collectors.toList())),
								(k,v) -> { throw new RuntimeException(String.format("Duplicate key %s", k));},
								TreeMap::new)
						);

	}

	/**
	 * calculates average income by age range
	 * @param employees - list of employees created from source file
	 * @return map, age range as a key and average income as a value
	 */
	public static Map<String, Double> calculateAverageIncomeByAgeRange(List<Employee> employees) {
		return getIncomeByAgeMap(employees).entrySet()
				.stream()
				.collect(
						Collectors.toMap(
								key -> AgeRange.getStringFromOrdinal(key.getKey()),
								value -> MathUtils.calculateAverage(value.getValue().stream().map(Employee::getSalary).collect(Collectors.toList())),
								(k,v) -> { throw new RuntimeException(String.format("Duplicate key %s", k));},
								LinkedHashMap::new)
						);
	}

	/**
	 * splits employees by department
	 * @param employees
	 * @return map containing department name as a key and list of employees as a value
	 */
	private static Map<String, List<Employee>> getEmployeesByDepartment(List<Employee> employees){
		return employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartmentName));
	}

	/**
	 * splits employees into groups by age
	 * @param employees
	 * @return map containing employees age as a key and list of employees as a value
	 */
	private static Map<Integer, List<Employee>> getIncomeByAgeMap(List<Employee> employees){
		return employees.stream()
				.collect(Collectors.groupingBy(Employee::getAgeRange));
	}

}
