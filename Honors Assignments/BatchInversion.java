/**
 * Programming Exercise (PDF-version):
 * https://d396qusza40orc.cloudfront.net/phoenixassets/duke-java-programming/ProgrammingExercise-BatchGrayscale.pdf
 *
 * @author jacobrandall480
 * @version August 2, 2023
 */

import edu.duke.*;
import java.io.*;


public class BatchInversion {
    public ImageResource makeInversion(ImageResource in) {
        ImageResource out = new ImageResource(in.getWidth(), in.getHeight());
        for (Pixel p : out.pixels()) {
            Pixel pixel = in.getPixel(p.getX(), p.getY());
            int red = (255 - pixel.getRed());
            int green = (255 - pixel.getGreen());
            int blue = (255 - pixel.getBlue());
            p.setRed(red);
            p.setGreen(green);
            p.setBlue(blue);
        }
        return out;
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource in = new ImageResource(f);
            ImageResource gray = makeInversion(in);

            String fileName = in.getFileName();
            String name = "inverted-" + fileName;

            gray.setFileName(name);
            gray.draw();
            gray.save();
        }
    }

    public static void main(String args[]) {
        BatchInversion run = new BatchInversion();
        run.selectAndConvert();
    }
}