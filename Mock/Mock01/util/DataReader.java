package util;

import container.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DataReader {
    public static Data[] loader(String filepath) {
        ArrayList<Data> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(processLine(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list.toArray(new Data[0]);
    }

    private static Data processLine(String line) {
        ArrayList<Double> imageData = new ArrayList<>();
        String[] parts = line.split(",");
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].startsWith("z")) {
                String number = parts[i].substring(1);
                for (int j = 0; j < Integer.parseInt(number); j++) {
                    imageData.add((double) 0);
                }
            } else {
                imageData.add(Double.parseDouble(parts[i]));
            }
        }

        double[] imageDataArray = imageData.stream().mapToDouble(Double::doubleValue).toArray();

        double[] result = new double[10];
        result[Integer.parseInt(parts[0])] = 1;

        return new Data(imageDataArray, result);
    }

//    public static void main(String[] args) {
//
//        Data[] dataArray = loader("mnist-train-processed.txt");
//
//        // Print the loaded data
//        for (Data data : dataArray) {
//            System.out.println(Arrays.toString(data.getData()));
//            System.out.println(Arrays.toString(data.getOutput()));
//        }
//    }
}
