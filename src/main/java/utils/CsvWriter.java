package refactored.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * class writes provided data to a .csv file
 * Created by olgasyrova on 09.10.16.
 */
public class CsvWriter {

    public static final String DELIMITER = ",";
    public static final String END_OF_LINE = "\r\n";

    /**
     * writes data to a file
     * @param values constructed by statistics calculations
     * @param filename
     * @param header
     * @throws IOException
     */
    public static void writeToCsv(Map<String, Double> values, String filename, String header) throws IOException{
        try {
            File file = new File(filename);
            FileWriter writer = new FileWriter(file);
            writer.append(header).append(END_OF_LINE);
            writer.append(constructLine(values));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private static String constructLine(Map<String, Double> data){

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Double> item : data.entrySet()){
            sb.append(item.getKey()).append(DELIMITER).append(new Formatter(Locale.US).format("%.2f", item.getValue())).append(END_OF_LINE);
        }

        return adaptToCsvConventions(sb.toString());
    }


    /**
     * adjusts line to csv format conventions
     * @param value - original line
     * @return adapted line
     */
    public static String adaptToCsvConventions(String value) {
        String result = value;
        if (result.contains("\"")) {
            result = result.replaceAll("\"", "\"\"");
        }
        if (result.contains(",")) {
            result = result.replaceAll(",", "\\,");
        }

        return result;
    }

}
