package refactored.statistics;

import java.io.IOException;

/**
 * interface for statistics calculation
 * Created by olgasyrova on 30.10.16.
 */
public interface StatisticsCalculatorService {

	// output files
	String MEDIAN_INCOME_BY_DEPARTMENT = "income-by-department.csv";
	String INCOME_95_BY_DEPARTMENT = "income-95-by-department.csv";
	String AVERAGE_INCOME_BY_AGE_RANGE = "income-average-by-age-range.csv";
	String MEDIAN_EMPLOYEE_AGE_BY_DEPARTMENT = "employee-age-by-department.csv";

	// column names
	String DEPARTMENT = "department";
	String INCOME = "income";
	String AGE = "age";
	String AGE_RANGE = "age_range";

    public void getMedianIncomeByDepartment() throws IOException;

    public void get95PercentileIncomeByDepartment() throws IOException;

    public void getMedianEmployeeAgeByDepartment() throws IOException;

    public void getAverageIncomeByAgeRange() throws IOException;

}

