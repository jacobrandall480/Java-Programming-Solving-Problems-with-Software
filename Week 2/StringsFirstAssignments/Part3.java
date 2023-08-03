/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 * 
 * @author jacobrandall480
 * @version July 31, 2023
 */

public class Part3 {

    public static boolean twoOccurrences(String a, String b){
        int first = b.indexOf(a);
        if(first == -1){
            return false;
        }
        int second = b.indexOf(a, first+1);
        return second != -1;
    }    
  
    public static void testing(){
        System.out.println("Test twoOccurrences method\n----------");
        System.out.println("Tested Strings: A story by Abby Long & by"); 
        System.out.println(twoOccurrences("by", "A story by Abby Long")); 
        System.out.println("Tested strings: banana & a");
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println("Tested strings: ctgtatgta & atg");
        System.out.println(twoOccurrences("atg", "ctgtatgta")); 
        System.out.println("Tested strings: banana & an");
        System.out.println(lastPart("an", "banana")); 
        System.out.println("Tested strings: forest & zoo");
        System.out.println(lastPart("zoo", "forest")); 
    }
      
    public static String lastPart(String a, String b){
        if(b.contains(a)){
            int index = b.indexOf(a);
            return b.substring(index, a.length()+2);
        } else{
            return b;
        }
    }

    public static void main(String[] args) {
        Part3 test = new Part3();
        test.testing();
    }
}
