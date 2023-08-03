/**
 * <pre>
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 * Data set: https://www.dukelearntoprogram.com//course2/data/nc_weather.zip
 * </pre>
 *
 * @author jacobrandall480
 * @version August 2, 2023
 */ 

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class ParsingWeatherData {
    public CSVRecord getColdestOfTwo(CSVRecord currentRow, CSVRecord coldestSoFar) {
        if (coldestSoFar == null) {
            coldestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if ((currentTemp < coldestTemp) && (currentTemp > -9999)) {
                coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;
    }

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser) {
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }

    public void testColdestHourInFile() {
        System.out.println("Testing coldestHourInFile method");
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature: " + coldest.get("TemperatureF") + " at " + coldest.get(0));
    }

    public String fileWithColdestTemperature() {
        String filePath = "";
        double coldestSoFar = 9999;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (currentTemp < coldestSoFar) {
                coldestSoFar = currentTemp;
                filePath = f.getPath();
            }
        }
        return filePath;
    }

    public void testfileWithColdestTemperature() {
        System.out.println("Testing fileWithColdestTemperature method");
        String filePath = fileWithColdestTemperature();

        File file = new File(filePath);
        String fileName = file.getName();
        System.out.println("Coldest day was in file " + fileName);

        FileResource fr = new FileResource(filePath);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on the day: " + coldest.get("TemperatureF"));
        System.out.println("Every temperatures on the coldest day: ");
        for (CSVRecord record : fr.getCSVParser()) {
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
    }

    public CSVRecord getlowestHumidityOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
        if (!currentRow.get("Humidity").equals("N/A")) {
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            } else {
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity"));
                if (currentHumidity < lowestHumidity) {
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            lowestSoFar = getlowestHumidityOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }

    public void testLowestHumidityInFile() {
        System.out.println("Testing lowestHumidityInFile method");
        FileResource fr = new FileResource();
        CSVRecord lowestHumidity = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest humidity: " + lowestHumidity.get("Humidity") + " at " + lowestHumidity.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getlowestHumidityOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }

    public void testLowestHumidityInManyFiles() {
        System.out.println("Testing lowestHumidityInManyFiles method");
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity: " + lowestHumidity.get("Humidity") + " at " + lowestHumidity.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double averageTemp = 0.0;
        double sumOfTemp = 0.0;
        int totalRec = 0;
        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            sumOfTemp += currentTemp;
            totalRec += 1;
        }
        averageTemp = sumOfTemp / totalRec;
        return averageTemp;
    }

    public void testAverageTemperatureInFile() {
        System.out.println("Testing averageTemperatureInFile method");
        FileResource fr = new FileResource();
        double average = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature: " + average);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double averageTemp = 0.0;
        double sumOfTemp = 0.0;
        int totalRec = 0;
        for (CSVRecord currentRow : parser) {
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (currentHumidity >= value) {
                sumOfTemp += currentTemp;
                totalRec += 1;
            }
        }
        averageTemp = sumOfTemp / totalRec;
        return averageTemp;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        System.out.println("Testing averageTemperatureWithHighHumidityInFile method");
        int humidity = 80;
        FileResource fr = new FileResource();
        double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), humidity);
        if (Double.isNaN(average)) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average temp when high humidity is " + average);
        }
    }

    public static void main(String[] args) {
        ParsingWeatherData test = new ParsingWeatherData();
        Scanner in = new Scanner(System.in);
        System.out.println("Choose a method for testing:");
        System.out.println("Put '1' for call 'testColdestHourInFile'" +
                "\nPut '2' for call 'testfileWithColdestTemperature'" +
                "\nPut '3' for call 'testLowestHumidityInFile'" +
                "\nPut '4' for call 'testLowestHumidityInManyFiles'" +
                "\nPut '5' for call 'testAverageTemperatureInFile'" +
                "\nPut '6' for call 'testAverageTemperatureWithHighHumidityInFile'");
        int choice = in.nextInt();
        if (choice == 1) {
            test.testColdestHourInFile();
        } else if (choice == 2) {
            test.testfileWithColdestTemperature();
        } else if (choice == 3) {
            test.testLowestHumidityInFile();
        } else if (choice == 4) {
            test.testLowestHumidityInManyFiles();
        } else if (choice == 5) {
            test.testAverageTemperatureInFile();
        } else if (choice == 6) {
            test.testAverageTemperatureWithHighHumidityInFile();
        } else {
            System.out.println("Bad input");
        }
    }
}
