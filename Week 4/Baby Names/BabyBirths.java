/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 * Small data set: http://www.dukelearntoprogram.com/course2/data/us_babynames_small.zip
 * Full data set: http://www.dukelearntoprogram.com/course2/data/us_babynames.zip
 * 
 *
 * @author jacobrandall480
 * @version August 2, 2023
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class BabyBirths {
    public void totalBirths(FileResource fr) {
        int births = 0, boys = 0, girls = 0;
        int names = 0,boyNames = 0, girlNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            births += numBorn;
            names++;
            if (rec.get(1).equals("M")) {
                boys += numBorn;
                boyNames++;
            } else {
                girls += numBorn;
                girlNames++;
            }
        }
        System.out.println("Total births = " + births);
        System.out.println("Girls = " + girls);
        System.out.println("Boys = " + boys);
        System.out.println("Total names = " + names);
        System.out.println("Total boys names = " + boyNames);
        System.out.println("Total girls names = " + girlNames);
    }

    public void testTotalBirths() {
        System.out.println("Test totalBirths method");
        //FileResource fr = new FileResource("us_babynames_small/yob2012short.csv"); // small dataset
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob1905.csv"); // full dataset
        totalBirths(fr);
    }

    public int getRank(int year, String name, String gender) {
        int rank = 0;
        //FileResource fr = new FileResource("us_babynames_small/yob" + year + "short.csv"); // small dataset
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv"); // full dataset
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                rank += 1;
                if (record.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }

    public void testGetRank() {
        System.out.println("Test getRank method");
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        System.out.println("Name: " + "'" + name + "'" +
                " \nYear: " + "'" + year + "'" +
                " \nGender: " + "'" + gender + "'" +
                " \nRank: " + getRank(year, name, gender));
    }

    public String getName(int year, int rank, String gender) {
        int currentRank = 0;
        //FileResource fr = new FileResource("us_babynames_small/yob" + year + "short.csv"); // small dataset
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv"); // full dataset
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                currentRank++;
                if (currentRank == rank) {
                    return record.get(0);
                }
            }
        }
        return "NO NAME";
    }

    public void testGetName() {
        System.out.println("Test getName method");
        int year = 1982;
        int rank = 450;
        String gender = "M";
        System.out.println("Year: " + "'" + year + "'" +
                " \nRank: " + "'" + rank + "'" +
                " \nGender: " + "'" + gender + "'" +
                " \nName: " + getName(year, rank, gender));
    }

    public String whatIsNameInYear(String name, int year, int newYear, String gender) {
        String nameInYear = "";
        int rank = getRank(year, name, gender);
        return nameInYear = getName(newYear, rank, gender);
    }

    public void testWhatIsNameInYear() {
        System.out.println("Test whatIsNameInYear method");
        String name = "Owen";
        int ogYear = 1974;
        int newYear = 2014;
        String gender = "M";
        String pronoun = "";
        if (gender == "F") {
            pronoun = "she";
        } else {
            pronoun = "he";
        }
        System.out.println(name +
                " born in " + ogYear +
                " would be " + whatIsNameInYear(name, ogYear, newYear, gender) +
                " if " + pronoun + " was born in " + newYear + ".");
    }

    public int yearOfHighestRank(String name, String gender) {
        int yearOfHighestRank = -1;
        int highestRank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int currentYear = Integer.parseInt(fileName.substring(3, 7));
            int currentRank = getRank(currentYear, name, gender);
            if ((currentRank != -1) && (highestRank == 0)
                    || (currentRank != -1) && (currentRank < highestRank)) {
                highestRank = currentRank;
                yearOfHighestRank = currentYear;
            }
        }
        return yearOfHighestRank;
    }

    public void testYearOfHighestRank() {
        System.out.println("Test yearOfHighestRank method");
        String name = "Mich";
        String gender = "M";
        System.out.println("Name: " + "'" + name + "'" +
                " \nGender: " + "'" + gender + "'" +
                " \nYear of highest rank: " + yearOfHighestRank(name, gender));
    }

    public double getAverageRank(String name, String gender) {
        double averageRank = 0.0;
        int totalRank = 0;
        int countFiles = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            int currentYear = Integer.parseInt(f.getName().substring(3, 7));
            int currentRank = getRank(currentYear, name, gender);
            if (currentRank != -1) {
                countFiles += 1;
                totalRank += currentRank;
            }
        }
        if (totalRank == 0) {
            return -1;
        }
        averageRank = (double) totalRank / countFiles;
        return averageRank;
    }

    public void testGetAverageRank() {
        System.out.println("Test getAverageRank method");
        String name = "Robert";
        String gender = "M";
        System.out.printf("Name: " + "'" + name + "'" +
                " \nGender: " + "'" + gender + "'" +
                " \nAverage rank: " + "%.4f", getAverageRank(name, gender));
        System.out.println("\n–––––––––––––––––––––––––");
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirth = 0;
        int rank = 0;
        //FileResource fr = new FileResource("us_babynames_small/yob" + year + "short.csv"); // small dataset
       FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv"); // full dataset
        int currentRank = getRank(year, name, gender);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank += 1;
                if (rank < currentRank) {
                    totalBirth += Integer.parseInt(rec.get(2));
                }
            }
        }
        return totalBirth;
    }

    public void testGetTotalBirthsRankedHigher() {
        System.out.println("Test getTotalBirthsRankedHigher method");
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        System.out.println("Year: " + "'" + year + "'" +
                " \nName: " + "'" + name + "'" +
                " \nGender: " + "'" + gender + "'" +
                " \nBirths higher: " + getTotalBirthsRankedHigher(year, name, gender));
        System.out.println("–––––––––––––––––––––––––");
    }

    public static void main(String[] args) {
        BabyBirths test = new BabyBirths();
        Scanner in = new Scanner(System.in);
        System.out.println("Choose a method for testing:");
        System.out.println("Put '1' for call 'testTotalBirths'" +
                "\nPut '2' for call 'testGetRank'" +
                "\nPut '3' for call 'testGetName'" +
                "\nPut '4' for call 'testWhatIsNameInYear'" +
                "\nPut '5' for call 'testYearOfHighestRank'" +
                "\nPut '6' for call 'testGetAverageRank'" +
                "\nPut '7' for call 'testGetTotalBirthsRankedHigher'");
        int choice = in.nextInt();
        if (choice == 1) {
            test.testTotalBirths();
        } else if (choice == 2) {
            test.testGetRank();
        } else if (choice == 3) {
            test.testGetName();
        } else if (choice == 4) {
            test.testWhatIsNameInYear();
        } else if (choice == 5) {
            test.testYearOfHighestRank();
        } else if (choice == 6) {
            test.testGetAverageRank();
        } else if (choice == 7) {
            test.testGetTotalBirthsRankedHigher();
        } else {
            System.out.println("Bad input");
        }
    }
}