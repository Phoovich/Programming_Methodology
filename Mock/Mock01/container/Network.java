package container;

import function.Function;

import java.io.*;
import java.util.Objects;

public class Network {
    private static Layer[] layers;
    private static Data[] datasets;

    public Network(int[] nLayers, Function[] functions, Data[] data) {
        Neuron.setRangeWeight(-1, 1);

        layers = new Layer[nLayers.length];
        layers[0] = null; // Initialize input layer
        for (int i = 1; i < nLayers.length; i++) {
            layers[i] = new Layer(nLayers[i - 1], nLayers[i], functions[i - 1]);
        }

        datasets = new Data[data.length];
        System.arraycopy(data, 0, datasets, 0, data.length);
    }

    public static void forward(double[] inputs) {
        layers[0] = new Layer(inputs);

        for (int i = 1; i < layers.length; i++) {
            for (int j = 0; j < layers[i].getNeurons().length; j++) {
                double sum = layers[i].getNeurons()[j].getBias();
                for (int k = 0; k < layers[i - 1].getNeurons().length; k++) {
                    sum += layers[i - 1].getNeurons()[k].getValue() * layers[i].getNeurons()[j].getWeights()[k];
                }
                double output = layers[i].applyActivation(sum);
                layers[i].getNeurons()[j].setValue(output);
            }
        }
    }

    public static void backward(double learningRate, Data tData) {
        int nLayers = layers.length;
        int outIndex = nLayers - 1;

        // Output layer
        for (int i = 0; i < layers[outIndex].getNeurons().length; i++) {
            double output = layers[outIndex].getNeurons()[i].getValue();
            double target = tData.getOutput()[i];
            double derivative = output - target;
            double delta = derivative * layers[outIndex].applyActivationDerivative(output);
            layers[outIndex].getNeurons()[i].setGradient(delta);

            // Update weights and bias
            for (int j = 0; j < layers[outIndex].getNeurons()[i].getWeights().length; j++) {
                double previousOutput = layers[outIndex - 1].getNeurons()[j].getValue();
                double error = delta * previousOutput;
                layers[outIndex].getNeurons()[i].getWeights()[j] -= learningRate * error;
            }
            layers[outIndex].getNeurons()[i].setBias(layers[outIndex].getNeurons()[i].getBias() - learningRate * delta);
        }

        // Hidden layers
        for (int i = outIndex - 1; i > 0; i--) {
            for (int j = 0; j < layers[i].getNeurons().length; j++) {
                double output = layers[i].getNeurons()[j].getValue();
                double gradientSum = sumGradient(j, i + 1);
                double delta = gradientSum * layers[i].applyActivationDerivative(output);
                layers[i].getNeurons()[j].setGradient(delta);

                // Update weights and bias
                for (int k = 0; k < layers[i].getNeurons()[j].getWeights().length; k++) {
                    double previousOutput = layers[i - 1].getNeurons()[k].getValue();
                    double error = delta * previousOutput;
                    layers[i].getNeurons()[j].getWeights()[k] -= learningRate * error;
                }
                layers[i].getNeurons()[j].setBias(layers[i].getNeurons()[j].getBias() - learningRate * delta);
            }
        }
    }

    public static double sumGradient(int nIndex, int lIndex) {
        double gradientSum = 0;
        Layer currentLayer = layers[lIndex];
        for (Neuron neuron : currentLayer.getNeurons()) {
            gradientSum += neuron.getWeights()[nIndex] * neuron.getGradient();
        }
        return gradientSum;
    }

    public static void train(int trainingIterations, double learningRate) {
        for(int i = 0; i < trainingIterations; i++) {
            double totalLoss = 0;

            for (Data dataset : datasets) {
                forward(dataset.getInput());

                double instanceLoss = calculateLoss(layers[layers.length - 1].getNeurons(), dataset.getOutput());
                totalLoss += instanceLoss;

                backward(learningRate, dataset);
            }

            double averageLoss = totalLoss / datasets.length;
            System.out.println("Iteration " + (i + 1) + ", Average Loss: " + averageLoss);
        }
    }

    public static double calculateLoss(Neuron[] outputNeurons, double[] expectedOutput) {
        double squaredError = 0.0;
        for (int i = 0; i < outputNeurons.length; i++) {
            double error = expectedOutput[i] - outputNeurons[i].getValue();
            squaredError += error * error; // MSQ ERR
        }
        return 0.5 * squaredError;
    }

    public static double[] predict(double[] data) {
        assert data.length == layers[0].getNeurons().length;
        forward(data);
        double[] prediction = new double[layers[layers.length - 1].getNeurons().length];
        for (int i = 0; i < prediction.length; i++) {
            prediction[i] = layers[layers.length - 1].getNeurons()[i].getValue();
        }

        return prediction;
    }

    public void save(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(layers.length + "\n");

            for (int i = 0; i < layers.length; i++) {
                Layer layer = layers[i];
                if (i == 0) {  // Input layer
                    writer.write(layer.getNeurons().length + "\n");
                    writer.write("null\n");  // No activation function for input layer
                    for (Neuron neuron : layer.getNeurons()) {
                        writer.write(neuron.getValue() + "\n");
                    }
                } else {  // Hidden and output layers
                    writer.write(layer.getNeurons().length + "\n");
                    writer.write(layer.getFunction() + "\n");
                    for (Neuron neuron : layer.getNeurons()) {
                        writer.write(neuron.getBias() + "\n");
                        for (double weight : neuron.getWeights()) {
                            writer.write(weight + " ");
                        }
                        writer.write("\n");
                    }
                }
            }
            System.out.println("Network saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving network: " + e.getMessage());
        }
    }

    public static void load(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int layerCount = Integer.parseInt(reader.readLine().trim());
            Layer[] loadedLayers = new Layer[layerCount];

            for (int i = 0; i < layerCount; i++) {
                int neuronCount = Integer.parseInt(Objects.requireNonNull(readNonEmptyLine(reader)));
                String functionStr = readNonEmptyLine(reader);
                assert functionStr != null;
                Function function = (functionStr.equals("null")) ? null : Function.valueOf(functionStr);

                if (i == 0) {  // Input Layer
                    double[] inputs = new double[neuronCount];
                    for (int j = 0; j < neuronCount; j++) {
                        inputs[j] = Double.parseDouble(Objects.requireNonNull(readNonEmptyLine(reader)));
                    }
                    loadedLayers[i] = new Layer(inputs);
                } else {  // Hidden and Output Layers
                    int prevLayerSize = loadedLayers[i-1].getNeurons().length;
                    loadedLayers[i] = new Layer(prevLayerSize, neuronCount, function);
                    Neuron[] neurons = new Neuron[neuronCount];
                    for (int j = 0; j < neuronCount; j++) {
                        double bias = Double.parseDouble(Objects.requireNonNull(readNonEmptyLine(reader)));
                        String[] weightStr = Objects.requireNonNull(readNonEmptyLine(reader)).split(" ");
                        double[] weights = new double[weightStr.length];
                        for (int k = 0; k < weightStr.length; k++) {
                            weights[k] = Double.parseDouble(weightStr[k]);
                        }
                        neurons[j] = new Neuron(weights, bias);
                    }
                    loadedLayers[i].setNeurons(neurons);
                }
            }

            setLayers(loadedLayers);
            System.out.println("Network loaded successfully from " + filename);
        } catch (IOException e) {
            System.err.println("Error loading network: " + e.getMessage());
        }
    }

    private static String readNonEmptyLine(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                return line;
            }
        }
        return null;
    }

    public static Layer[] getLayers() {
        return layers;
    }

    public static void setLayers(Layer[] layers) {
        Network.layers = layers;
    }

    public static Data[] getDatasets() {
        return datasets;
    }

    public static void setDatasets(Data[] datasets) {
        Network.datasets = datasets;
    }

}
