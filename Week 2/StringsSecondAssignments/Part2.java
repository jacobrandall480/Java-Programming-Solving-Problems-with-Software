/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 * 
 * @author jacobrandall480
 * @version July 31, 2023
 */

public class Part2 {

    public static int howMany(String a, String b){
        int count = 0;
        int startIndex = 0;
        
        while(true){
            int currIndex = b.indexOf(a, startIndex);
            if(currIndex == -1){
                break;
            }
            count++;
            startIndex = currIndex + a.length();
        }
        return count;
    }
    
    public static void testHowMany(){
        System.out.println("Test howMany method\n----------");
        
        String a = "GAA";
        String b = "ATGAACGAATTGAATC";
        System.out.println("Test 1\nDNA: " + b + " Gene: " + a);
        System.out.println("The gene occurs " + howMany(a, b) + " times in the DNA strand."); // Expected: 3
        
        a = "AA";
        b = "ATAAAA";
        System.out.println("\nTest 2\nDNA: " + b + " Gene: " + a);
        System.out.println("The gene occurs " + howMany(a, b) + " times in the DNA strand."); // Expected: 2       

        a = "ATG";
        b = "AAATATCAAATAGTAAATAATAAAGT";
        System.out.println("\nTest 3\nDNA: " + b + " Gene: " + a);
        System.out.println("The gene occurs " + howMany(a, b) + " times in the DNA strand."); // Expected: 0
        
        a = "GTA";
        b = "xxxxxGTAxxxTAAxxxxxxGTAGTAxxxTAGxxxTGA";
        System.out.println("\nTest 4\nDNA: " + b + " Gene: " + a);
        System.out.println("The gene occurs " + howMany(a, b) + " times in the DNA strand."); // Expected: 3
    }

    public static void main(String[] args) {
        Part2 test = new Part2();
        test.testHowMany();
    }

}
