/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 * 
 * @author jacobrandall480
 * @version July 31, 2023
 */

import edu.duke.*;

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
    
    public static void testGetAllGenes(){
        System.out.println("Testing getAllGenes method\n----------");
        
        String dna = "xxxATGxxxTAAxxxGTAxxxATGxxxTAGxxxTGA";
        System.out.println("Test 1:\nDNA: " + dna + " \nFound genes:");
        StorageResource genes = getAllGenes(dna);
        for (String g : genes.data()) {
            System.out.println(g);
        }

        dna = "AAATATGAAATGATAATTTTGATAGAAATAG";
        System.out.println("\nTest 2:\nDNA: " + dna + " \nFound genes:");
        genes = getAllGenes(dna);
        for (String g : genes.data()) {
            System.out.println(g);
        }
    }

    public static void main(String args[]){
        Part1 test = new Part1();
        test.testGetAllGenes();
    }
}
