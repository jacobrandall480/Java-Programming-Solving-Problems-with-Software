
/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 * 
 * @author jacobrandall480
 * @version August 2, 2023
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
    
    public static String countryInfo(CSVParser parser, String country){
        for (CSVRecord record : parser){
            if (record.get("Country").equals(country)) {
                return record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return "Country " + country + ": NOT FOUND";
    }
    
    public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    public static int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }

    public static void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }

    public static void tester() {
        System.out.println("Testing 'Parsing Export Data'");
        FileResource fr = new FileResource();
        
        System.out.println("\nTest countryInfo method");
        CSVParser parser = fr.getCSVParser();
        String country = "Nauru";
        System.out.println(countryInfo(parser, country));

        System.out.println("\nTest listExportersTwoProducts method");
        parser = fr.getCSVParser();
        String product1 = "cotton";
        String product2 = "flowers";
        System.out.println("Countries that export " + "'" + product1 + "'" + " and " + "'" + product2 + "'" + ":");
        listExportersTwoProducts(parser, product1, product2);
        
        System.out.println("\nTest numberOfExporters method");
        parser = fr.getCSVParser();
        String exportItem = "cocoa";
        System.out.println("Number of countries that export " + "'" + exportItem + "'" + ": " + numberOfExporters(parser, exportItem));
        
        System.out.println("\nTest bigExporters method");
        parser = fr.getCSVParser();
        String valueAmount = "$999,999,999,999";
        System.out.println("Countries with export value more than " + valueAmount + ":");
        bigExporters(parser, valueAmount);
    }

    public static void main(String[] args) {
        ParsingExportData test = new ParsingExportData();
        test.tester();
    }
    
}
