/**
 * Programming Exercise (PDF-version):
 * https://d396qusza40orc.cloudfront.net/phoenixassets/duke-java-programming/ProgrammingExercise-BatchGrayscale.pdf
 *
 * @author jacobrandall480
 * @version August 2, 2023
 */

import edu.duke.*;
import java.io.*;

public class BatchGrayscale {
    public ImageResource makeGray(ImageResource in) {
        ImageResource out = new ImageResource(in.getWidth(), in.getHeight());
        for (Pixel p : out.pixels()) {
            Pixel pixel = in.getPixel(p.getX(), p.getY());
            int red = pixel.getRed();
            int green = pixel.getGreen();
            int blue = pixel.getBlue();
            int average = (red + green + blue) / 3;
            p.setRed(average);
            p.setGreen(average);
            p.setBlue(average);
        }
        return out;
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource in = new ImageResource(f);
            ImageResource gray = makeGray(in);

            String fileName = in.getFileName();
            String name = "gray-" + fileName;

            gray.setFileName(name);
            gray.draw();
            gray.save();
        }
    }

    public static void main(String args[]) {
        BatchGrayscale run = new BatchGrayscale();
        run.selectAndConvert();
    }
}