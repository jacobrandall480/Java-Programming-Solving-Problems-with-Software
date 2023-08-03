/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 * 
 * @author jacobrandall480
 * @version July 31, 2023
 */

import edu.duke.*;

public class Part2 {
    
    public static double cgRatio(String dna){
        int cgCount = 0;
        for(int i = 0; i < dna.length(); i++){
            char nucleotide = dna.charAt(i);
            
            if(nucleotide == 'C' || nucleotide == 'G'){
                cgCount++;
            }
        }
        return (double) cgCount/dna.length();
    }

    public static void testCgRatio(){
        System.out.println("Testing cgRatio method\n----------");
        
        String dna = "ATGCCAAATAG";
        System.out.println("Test 1:\nDNA: " + dna + " \nThe C-G ratio is:"); 
        System.out.printf("%.7f", cgRatio(dna));

        dna = "ATGGTAGTTTAGTTGCC";
        System.out.println("\n\nTest 2: \nDNA: " + dna + " \nThe C-G ratio is:");
        System.out.printf("%.7f", cgRatio(dna));

        dna = "CTGCTAAAG";
        System.out.println("\n\nTest 3:\nDNA: " + dna + " \nThe C-G ratio is:");
        System.out.printf("%.7f", cgRatio(dna));
    }
    
    public static int countCTG(String dna){
        int count = 0;
        int startIndex = 0;
        
        while(true){
            startIndex = dna.indexOf("CTG", startIndex);
            if(startIndex == -1){
                break;
            }
            count++;
            startIndex += 3;
        }
        return count;
    }
    
    public static void testCountCTG(){
        System.out.println("\nTesting countCTG method\n----------");
        
        String dna = "CTGTGACTGGTACTG";
        System.out.println("Test 1: \nDNA: " + dna + "\nCTG occurs " + countCTG(dna) + " times in DNA"); // Expected: 3 

        dna = "ATGGTAxxxGTTGxxxCCA";
        System.out.println("\nTest 2: \nDNA: " + dna + "\nCTG occurs " + countCTG(dna) + " times in DNA"); // Expected: 0 

        dna = "CTGCTGCTGCTG";
        System.out.println("\nTest 3: \nDNA: " + dna + "\nCTG occurs " + countCTG(dna) + " times in DNA"); // Expected: 4
    }

    public static void main(String args[]){
        Part2 test = new Part2();
        test.testCgRatio();
        test.testCountCTG();
    }
        
}
