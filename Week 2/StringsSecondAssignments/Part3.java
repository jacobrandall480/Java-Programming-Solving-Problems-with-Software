/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 * 
 * @author jacobrandall480
 * @version July 31, 2023
 */

public class Part3 {

    public static int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = startIndex;
        while(currIndex < dna.length()){
            if(currIndex + stopCodon.length() <= dna.length()){
                String currCodon = dna.substring(currIndex, currIndex + stopCodon.length());
                if(currCodon.equals(stopCodon) && (currIndex-startIndex)%3 == 0){
                    return currIndex;
                }
            }
            currIndex += 3;
        }
        return dna.length();
    }
  
    public static String findGene(String dna){
        int start = dna.indexOf("ATG");
        if(start == -1){
            return "";
        }
        
        int stopTAA = findStopCodon(dna, start, "TAA");
        int stopTAG = findStopCodon(dna, start, "TAG");
        int stopTGA = findStopCodon(dna, start, "TGA");
        
        int minStop = Math.min(stopTAA, Math.min(stopTAG, stopTGA));
        if(minStop == dna.length()){
            return "";
        }
        return dna.substring(start, minStop+3);
    }
    
    public static void printAllGenes(String dna){
        while(true){
            String gene = findGene(dna);
            if(gene.isEmpty()){
                break;
            }
            System.out.println("Found gene: " + gene);
            int startIndex = dna.indexOf(gene)+gene.length();
            dna = dna.substring(startIndex);
        }
    }
    
    public static int countGenes(String dna){
        int count = 0;
        while(true){
            String gene = findGene(dna);
            if(gene.isEmpty()){
                break;
            }
            count++;
            dna = dna.substring(dna.indexOf(gene) + gene.length());
        }
        return count;
    }
    
    public static void testCountGenes(){
        System.out.println("Testing countGenes method\n----------");
        
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println("Test 1\nDNA: " + dna + "\nNumber of genes found: " + countGenes(dna)); // Expected: 2

        dna = "AAATATGAAATGAATAATTTTGATAGAATAAATAG";
        System.out.println("\nTest 2\nDNA: " + dna + "\nNumber of genes found: " + countGenes(dna)); // Expected: 1

        dna = "AAATATGTTTGTATGTGTAAGTATGAAATTG";
        System.out.println("\nTest 3\nDNA: " + dna + "\nNumber of genes found: " + countGenes(dna)); // Expected: 0
    }
    
    public static void main(String args[]){
        Part3 test = new Part3();
        test.testCountGenes();
    }

}
