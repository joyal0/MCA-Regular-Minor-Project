import edu.duke.*;
import java.io.*;


public class BatchGrayscale {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel p : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(p.getX(), p.getY());

            int red = inPixel.getRed();
            int green = inPixel.getGreen();
            int blue = inPixel.getBlue();
            int average = (red + green + blue) / 3;

            p.setRed(average);
            p.setGreen(average);
            p.setBlue(average);
        }
        return outImage;
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);

            String fileName = inImage.getFileName();
            String newName = "gray-" + fileName;

            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }

    public static void main(String args[]) {
        BatchGrayscale run = new BatchGrayscale();
        run.selectAndConvert();
    }
}