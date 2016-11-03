package refactored;

import org.apache.log4j.Logger;
import refactored.statistics.StatisticsCalculatorService;
import refactored.statistics.StatisticsCalculatorServiceImpl;

import java.io.IOException;

/**
 * Main class to start the application, main method accepts exactly one argument what is the path to the resource files
 * NOTE : provided path should be an absolute path (i.e. C:\Users\syrovo01\Projects\hubrick\src\main\resources)
 * Created by syrovo01 on 21.10.2016.
 */
public class Main {

    static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException{


		if (args.length != 1){
			logger.error("please provide directory path with source data");
			return;
		}

        StatisticsCalculatorService calculatorService = new StatisticsCalculatorServiceImpl(args[0]);
        logger.info("calculating median income pro department .. ");
        calculatorService.getMedianIncomeByDepartment();
        logger.info("done!");
        logger.info("calculating 95th percentile income pro department .. ");
        calculatorService.get95PercentileIncomeByDepartment();
        logger.info("done!");
        logger.info("calculating median employee age pro department .. ");
        calculatorService.getMedianEmployeeAgeByDepartment();
        logger.info("done!");
        logger.info("calculating average income by age .. ");
        calculatorService.getAverageIncomeByAgeRange();
        logger.info("done!");

	}

}
