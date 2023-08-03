/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 * 
 * @author jacobrandall480
 * @version July 31, 2023
 */

public class Part2 {

    public static String findSimpleGeneCase(String dna, String startCodon, String stopCodon){
        int start = dna.toUpperCase().indexOf(startCodon);
        int stop = dna.toUpperCase().indexOf(stopCodon, start + 3);
        if ((start == -1) || (stop == -1)) {
            return "";
        }
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop + 3);
        }
        return "";
    }  
    
    public static void testFindSimpleGeneCase(){
        String startCodon = "ATG";
        String stopCodon = "TAA";
        System.out.println("Test findSimpleGeneCase method\n----------");
        // Test case 1: start and stop codon, multiple of 3 --> valid
        System.out.println("1st gene: " + findSimpleGeneCase("ATGGAATAAGTC", startCodon, stopCodon));
        // Test case 2: no stop codon
        System.out.println("2nd gene: " + findSimpleGeneCase("aatgggtagttattcg", startCodon, stopCodon));
        // Test case 3: no start codon 
        System.out.println("3rd gene: " + findSimpleGeneCase("AATTAGTAGTTAGTCG", startCodon, stopCodon));
        // Test case 4: start and stop codon, multiple of 3 --> valid lowercase
        System.out.println("4th gene: " + findSimpleGeneCase("aatgtggatgtaatggttaattg", startCodon, stopCodon));
        // Test case 5: start and stop codon, not a multiple of 3
        System.out.println("5th gene: " + findSimpleGeneCase("AATGGGTAATCGTTAGTCG", startCodon, stopCodon));  
    }

     public static void main(String[] args) {
        Part2 test = new Part2();
        test.testFindSimpleGeneCase();
    }
}
