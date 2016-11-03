package utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by syrovo01 on 01.11.2016.
 */
public class MathUtils {

	private static final Double PERCENTILE_95_TH = 0.95d;

    public static double calculateMedian(List<Double> items){
        int middle = items.size()/2;
        if (items.size() % 2 == 1) {
            return items.get(middle);

        } else {
            return (items.get(middle-1) + items.get(middle)) / 2.0 ;
        }
    }

    public static double calculateAverage(List<Double> list){
		return list
				.stream()
				.mapToDouble(a -> a)
				.average()
				.getAsDouble();
	}

	public static double calculate95thPercentile(List<Double> salaries){
		// 1. get the total sum of salaries
        double totalSum = salaries.stream().mapToDouble(Double::doubleValue).sum();

		double currentSum = 0f;
		double percentileSum = PERCENTILE_95_TH * totalSum;
		// 2. calculate 95th percentile : return salary which reaches 95% of the whole sum
		for (Double salary : salaries){
			currentSum += salary;
			if (currentSum >= percentileSum){
				return salary;
			}
		}
		return 0f;
	}

}
