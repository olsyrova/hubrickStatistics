package refactored.statistics;

import refactored.domain.Employee;
import refactored.utils.ContentParser;
import refactored.utils.CsvWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * class combining functionality of statistics calculations and writing data to files
 * Created by syrovo01 on 02.11.2016.
 */
public class StatisticsCalculatorServiceImpl implements StatisticsCalculatorService {

	private static List<Employee> employees;
	private static final String DELIMITER = ",";

	public StatisticsCalculatorServiceImpl(String path) throws IOException {
		ContentParser contentParser = ContentParser.getInstance();
		contentParser.setResourcesPath(path);
		employees = contentParser.parseEmployees();
	}

	/**
	 * writes results of median income by department calculations to a file
	 * @throws IOException
	 */
	@Override public void getMedianIncomeByDepartment() throws IOException{
		CsvWriter.writeToCsv(
				StatisticsCalculator.calculateMedianIncomeByDepartment(employees),
				StatisticsCalculatorService.MEDIAN_INCOME_BY_DEPARTMENT,
				StatisticsCalculatorService.DEPARTMENT + DELIMITER + StatisticsCalculatorService.INCOME);
	}

	/**
	 * writes results of 95th percentile income by department calculations to a file
	 * @throws IOException
	 */
	@Override public void get95PercentileIncomeByDepartment() throws IOException{
		CsvWriter.writeToCsv(
				StatisticsCalculator.calculate95PercentileIncomeByDepartment(employees),
				StatisticsCalculatorService.INCOME_95_BY_DEPARTMENT,
				StatisticsCalculatorService.DEPARTMENT + DELIMITER + StatisticsCalculatorService.INCOME);
	}

	/**
	 * writes results of median employee age by department calculations to a file
	 * @throws IOException
	 */
	@Override public void getMedianEmployeeAgeByDepartment() throws IOException{
		CsvWriter.writeToCsv(
				StatisticsCalculator.calculateMedianEmployeeAgeByDepartment(employees),
				StatisticsCalculatorService.MEDIAN_EMPLOYEE_AGE_BY_DEPARTMENT,
				StatisticsCalculatorService.DEPARTMENT + DELIMITER + StatisticsCalculatorService.AGE);
	}

	/**
	 * writes results of average income by age range calculations to a file
	 * @throws IOException
	 */
	@Override public void getAverageIncomeByAgeRange() throws IOException{
		CsvWriter.writeToCsv(
				StatisticsCalculator.calculateAverageIncomeByAgeRange(employees),
				StatisticsCalculatorService.AVERAGE_INCOME_BY_AGE_RANGE,
				StatisticsCalculatorService.AGE_RANGE + DELIMITER + StatisticsCalculatorService.INCOME);
	}
}
