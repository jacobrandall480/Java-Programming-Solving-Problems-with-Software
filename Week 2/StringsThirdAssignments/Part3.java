/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 * 
 * @author jacobrandall480
 * @version July 31, 2023
 */

import edu.duke.*;

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
    
    public static StorageResource getAllGenes(String dna){
        StorageResource genes = new StorageResource();
        while(true){
            String gene = findGene(dna);
            if(gene.isEmpty()){
                break;
            }
            genes.add(gene);
            dna = dna.substring(dna.indexOf(gene)+gene.length());
        }
        return genes;
    }

    
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
  
    public static void processGenes(StorageResource sr){
        // count characters
        int count9 = 0;
        int count_long = 0;
        for (String g : sr.data()) {
            if (g.length() > 9) {
                count9++;
            }
            if (g.length() > 60) {
                count_long += 1;
            }
        }
        System.out.println("Number of strings longer than 9 characters: " + count9);
        System.out.println("Number of strings longer than 60 characters: " + count_long);

        // count cg ratio
        int cg_ratio_count = 0;
        for (String g : sr.data()) {
            double cgRatio = cgRatio(g);
            if (cgRatio > 0.35) {
                cg_ratio_count++;
            }
        }
        System.out.println("Number of strings whose C-G-ratio is higher than 0.35: " + cg_ratio_count);

        // determine lonegest gene
        String longestGene = "";
        for (String g : sr.data()) {
            if (g.length() > longestGene.length()) {
                longestGene = g;
            }
        }
        System.out.println("The longest gene is: " + "\n" + longestGene + "\nit's length is: " + longestGene.length());
    }
    
     public static void testProcessGenes() {
       System.out.println("Test processGenes method with DNA string in external file.\n----------");
       FileResource fr = new FileResource("brca1line.fa");
         
       String dna = fr.asString();
       dna = dna.toUpperCase();
         
       StorageResource sr = getAllGenes(dna);
       processGenes(sr);
         
       System.out.println("The number of genes found in file: " + countGenes(dna));
       System.out.println("The codon of CTG appear " + countCTG(dna) + " times in DNA");
    }

    public static void testProcessGenes2(){
        System.out.println("Test processGenes method with DNA strings to use as test cases.\n----------");
        StorageResource sr = new StorageResource();
        
        String dna = "AAATATGAAAGTATGTTAGTGGTTTTGGAAAATGGTAXXXGATTTGAAGTAG";
        System.out.println("Test 1: \nDNA: " + dna);
        processGenes(getAllGenes(dna));
        
        dna = "AAATATCAAATAGTAAATAA";
        System.out.println("\nTest 2:\nDNA: " + dna);
        processGenes(getAllGenes(dna));
        
        dna = "TAAAAAATGAGTTAGATGCCCGCGAAACATGATTAAAAAATGAAACATGATTAA";
        System.out.println("\nTest 3: \nDNA: " + dna);
        processGenes(getAllGenes(dna));
        
        dna = "AAATATGTTTGTATGTGTAAAATTG";
        System.out.println("\nTest 4: \nDNA: " + dna);
        processGenes(getAllGenes(dna));
        
        dna = "xxxATGxxxTAAxxxATGxxxTAGxxxTGA";
        System.out.println("\nTest 5: \nDNA: " + dna);
        processGenes(getAllGenes(dna));
    }

    public static void main(String args[]){
        Part3 test = new Part3();
        //test.testProcessGenes();
        test.testProcessGenes2();
    }
        
}
