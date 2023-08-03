/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 * 
 * @author jacobrandall480
 * @version July 31, 2023
 */

import edu.duke.*;

public class Part4 {
    public static void printYouTubeLinks(String url){
        URLResource resource = new URLResource(url);
        for(String word : resource.words()){
            String wordLow = word.toLowerCase();
            int youtubeIndex = wordLow.indexOf("youtube.com");
            if(youtubeIndex != -1){
                int start = wordLow.lastIndexOf("\"", youtubeIndex);
                int end = wordLow.indexOf("\"", youtubeIndex + "youtube.com".length());
                
                if(start != -1 && end != -1){
                   System.out.println(word.substring(start+1, end));
                }
            }
        }
    }
    
    public static void main(String args[]){
        Part4 urls = new Part4();
        urls.printYouTubeLinks("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }

}
