/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 * 
 * @author jacobrandall480
 * @version July 31, 2023
 */

public class Part1 {
    
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
    
    public static void testFindStopCodon(){
        System.out.println("\nTest findStopCodon method\n----------");
        String dna = "AAATATGAAATAGTAATTTTATTTTTT";
        System.out.println("Testing string of DNA: " + dna);

        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            System.out.println("No start codon");
        } else {
            // start codon is at 4
            System.out.println("Start codon ATG has index: " + startIndex);
        }

        int stopIndex = findStopCodon(dna, startIndex, "TAA");
        if (stopIndex == dna.length()) {
            System.out.println("No stop codon");
        } else {
            // stop codon TAA is at 13
            System.out.println("Stop codon TAA has index: " + stopIndex);
        }

        stopIndex = findStopCodon(dna, startIndex, "TAG");
        if (stopIndex == dna.length()) {
            System.out.println("No stop codon");
        } else {
            // stop codon TAG is at 10
            System.out.println("Stop codon TAG has index: " + stopIndex);
        }

        stopIndex = findStopCodon(dna, startIndex, "TGA");
        if (stopIndex == dna.length()) {
            System.out.println("No stop codon");
        } else {
            // there is no TGA stop codon
            System.out.println("Stop codon TGA has index: " + stopIndex);
        }
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
    
    public static void testFindGene(){
        System.out.println("\nTest findGene method\n----------");
        
        // Test case 1: valid gene ending in TAG
        String dna1 = "ATGAAATAGTTTAA";
        System.out.println("Test 1\nDNA: " + dna1 + "\nValid Gene: " + findGene(dna1)); 

        // Test case 2: valid gene ending in TGA
        String dna2 = "ATGAAATGAGTGA";
        System.out.println("\nTest 2\nDNA: " + dna2 + "\nValid Gene: " + findGene(dna2)); 

        // Test case 3: valid gene ending in TAA
        String dna3 = "ATGAAATGTTAAGGGTGA";
        System.out.println("\nTest 3\nDNA: " + dna3 + "\nValid Gene: " + findGene(dna3));

        // Test case 4: no start codon "ATG"
        String dna4 = "TTAGGAATGAGGGA";
        System.out.println("\nTest 4\nDNA: " + dna4 + "\nValid Gene: " + findGene(dna4));

        // Test case 5: no valid stop codon
        String dna5 = "ATGAAAGAGGGA";
        System.out.println("\nTest 5\nDNA: " + dna5 + "\nValid Gene: " + findGene(dna5)); 

        // Test case 6: valid stop codon, but not a multiple of 3
        String dna6 = "ATGAAAATGAGTGAGGA";
        System.out.println("\nTest 6\nDNA: " + dna6 + "\nValid Gene: " + findGene(dna6)); 
    }
    
    public static void printAllGenes(String dna){
        while(true){
            String gene = findGene(dna);
            if(gene.isEmpty()){
                break;
            }
            System.out.println(gene);
            dna = dna.substring(dna.indexOf(gene)+gene.length());
        }
    }

    public static void testPrintAllGenes(){
        System.out.println("\nTesting printAllGenes method\n----------");
        
        // Test case 1: 2 valid genes
        String dna = "xxxATGxxxTAAxxxATGxxxTGGxxxTGA";
        System.out.println("Test 1\nDNA: " + dna + " \nFound genes:");
        printAllGenes(dna);

        // Test case 2: 1 valid gene
        dna = "AAATATGAAATGACGTAATTTTGATAGAAATAG";
        System.out.println("\nTest 2\nDNA: " + dna + " \nFound genes:");
        printAllGenes(dna);

    }
    
    public static void main(String args[]){
        Part1 test = new Part1();
        test.testFindStopCodon();
        test.testFindGene();
        test.testPrintAllGenes();
    }
}
