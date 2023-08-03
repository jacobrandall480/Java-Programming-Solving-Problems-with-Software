/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 *
 * @author jacobrandall480
 * @version July 22, 2023
 */

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int points = 0;
        for(Point p : s.getPoints()){
            points++;
        }
        return points;
    }

    public double getAverageLength(Shape s) {
        return getPerimeter(s)/(double)getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        Point prevPt = s.getLastPoint();
        double largestDistance = 0.0;
        
        for(Point currPt : s.getPoints()){
            double currDist = prevPt.distance(currPt);
            if(currDist > largestDistance){
                largestDistance = currDist;
            }
            prevPt = currPt;
        }
        return largestDistance;
    }

    public double getLargestX(Shape s) {
        double largestX = 0.0;
        for(Point currPt : s.getPoints()){
            double currX = currPt.getX();
            if(currX > largestX){
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPeri = 0.0;
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerimeter = getPerimeter(s);
            if(currPerimeter > largestPeri){
                largestPeri = getPerimeter(s);
            }
        }
        return largestPeri;
    }

    public String getFileWithLargestPerimeter() {
        File temp = null;
        String largest = "";
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(getPerimeter(s) > largestPerimeter){
                largestPerimeter = getPerimeter(s);
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        System.out.println("Testing perimeter");
        System.out.println("Perimeter: " + getPerimeter(s));
        System.out.println("Points: " + getNumPoints(s));
        System.out.println("Average side length: " +  getAverageLength(s));
        System.out.println("Largest side: " + getLargestSide(s));
        System.out.println("Largest x: " + getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println("Testing getLargestPerimeterWithMultipleFiles method");
        System.out.println("Largest perimeter: " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("Testing getFileWithLargestPerimeter method");
        System.out.println("The file with the largest perimeter: " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
