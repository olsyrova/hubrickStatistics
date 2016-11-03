package utils;

import org.apache.log4j.Logger;

/**
 * Created by olgasyrova on 01.11.16.
 */
public class Validator {
    static final Logger logger = Logger.getLogger(Validator.class);

    public static final String DELIMITER = ",";


    public static boolean isValidLineFormat(String line, int expectedSize){
        if (line.split(DELIMITER).length != expectedSize){
            logger.error("line '" + line + "' has wrong format => line was skipped");
            return false;
        }
        return true;
    }

    public static boolean isValidDepartment(String line){
        String[] values = line.split(DELIMITER);
        if (Integer.parseInt(values[0]) < 0 || Integer.parseInt(values[0]) > 7){
            logger.error("department '" + values[0] + "' does not exist => line was skipped");
            return false;
        }
        return true;
    }



}
