package util;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class ImageProcessor {
    public static double[] process(String filepath) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File(filepath));
        BufferedImage grayscaleImage = convertToGrayscale(originalImage, 28, 28);

        return normalizeImage(grayscaleImage);
    }

    private static BufferedImage convertToGrayscale(BufferedImage originalImage, int width, int height) {
        BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        grayscaleImage.getGraphics().drawImage(originalImage, 0, 0, width, height, null);
        return grayscaleImage;
    }

    private static double[] normalizeImage(BufferedImage image) {
        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        double[] normalizedPixels = new double[pixels.length];

        for (int i = 0; i < pixels.length; i++) {
            normalizedPixels[i] = (pixels[i] & 0xFF) / 255.0;
        }

        return normalizedPixels;
    }

//    public static void main(String[] args) throws IOException {
//        double[] arr = process("image_7.jpg");
//        System.out.println(Arrays.toString(arr));
//    }
}