/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 * 
 * @author jacobrandall480
 * @version July 30, 2023
 */

public class Part1 {

    public static String findSimpleGene(String dna){
        boolean foundAtg = false;
        if(dna.contains("ATG")){
            foundAtg = true;
            int startIndex = dna.indexOf("ATG");
            
            if(foundAtg){
                boolean foundTaa = false;
                if(dna.contains("TAA")){
                    foundTaa = true;
                    int stopIndex = dna.indexOf("TAA");
                    if(stopIndex < startIndex){
                        return "";
                    }else if( (startIndex-stopIndex)%3 == 0){
                         return dna.substring(startIndex, stopIndex+2);
                    }   
                }else{
                    return "";
                }
            }else{
                return "";
            }
        } 
        return "";
    }  
    
    public static void testFindSimpleGene(){
        System.out.println("Test findSimpleGene method\n----------");
        // Test case 1: no ATG
        System.out.println("1st gene: " + findSimpleGene("TAAGTGGAATTAA"));
        // Test case 2: no TAA
        System.out.println("2nd gene: " + findSimpleGene("AATGAAGGTA"));
        // Test case 3: no ATG or TAA
        System.out.println("3rd gene: " + findSimpleGene("AGGATTA"));
        // Test case 4: ATG and TAA, but not a multiple of 3
        System.out.println("4th gene: " + findSimpleGene("ATGAGAAATAAG"));
        // Test case 5: ATG and TAA, multiple of 3 --> valid
        System.out.println("5th gene: " + findSimpleGene("ATGAAGGGGTAA"));
    }

    public static void main(String args[]){
        Part1 test = new Part1();
        test.testFindSimpleGene();
    }
}
