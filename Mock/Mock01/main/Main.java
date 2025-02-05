package main;

import container.Data;
import container.Network;
import function.Function;
import util.DataReader;
import util.ImageProcessor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

//        int[] layerSizes = {784, 241, 88, 10};
//        Function[] activationFunctions = {Function.TANH, Function.TANH, Function.TANH};
//
//        Data[] data = DataReader.loader("mnist-train-processed.txt"); // Data so large, ask me for Dataset, I'll give it to you !
//
//        Network network = new Network(layerSizes, activationFunctions, data);
//
//        Network.train(20, 0.000761); // Takes around 5 mins to train, too long to train in test room !
//
//        network.save("saved_weights.txt");

        Network.load("src/saved_weights.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input filename from sample folder: ");
        String line = scanner.nextLine().trim();
        double[] sample = ImageProcessor.process("src/image/" + line + ".jpg");
        double[] result = Network.predict(sample);
        int index = findIndexOfMax(result);

        System.out.printf("The prediction is: %d, Confidence: %.6f%n", index, result[index]);
    }

    public static int findIndexOfMax(double[] values) {
        int indexOfMax = 0;
        double max = values[0];

        for (int i = 1; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
                indexOfMax = i;
            }
        }

        return indexOfMax;
    }
}
