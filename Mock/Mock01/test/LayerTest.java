package test;

import container.Layer;
import container.Neuron;
import function.Function;
import org.junit.jupiter.api.Test;
import util.Activation;
import util.GenRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LayerTest {
	private final static double DELTA = 0.0001;

	@Test
	public void testConstructorWithFunction() {
		Function function = Function.SIGMOID;
		int inNeurons = 2;
		int nNeurons = 3;
		Layer layer = new Layer(inNeurons, nNeurons, function);
		assertEquals(nNeurons, layer.getNeurons().length);
		assertEquals(function, layer.getFunction());

		Neuron[] neurons = layer.getNeurons();

		for (Neuron n : neurons) {
			assertEquals(inNeurons, n.getWeights().length);
		}

	}

	@Test
	public void testConstructorWithInput() {
		double[] input = { 0.5, -0.3, 0.1 };
		Layer layer = new Layer(input);
		Neuron[] neurons = layer.getNeurons();

		assertEquals(input.length, neurons.length);
		assertEquals(0.5, neurons[0].getValue(), DELTA);
		assertEquals(-0.3, neurons[1].getValue(), DELTA);
		assertEquals(0.1, neurons[2].getValue(), DELTA);
		assertNull(layer.getFunction());
	}

	@Test
	public void testApplyActivationSigmoid() {
		double x = 0.5;
		Layer layer = new Layer(1, 1, Function.SIGMOID);
		assertEquals(Activation.sigmoid(x), layer.applyActivation(x), 0.0001);
	}

	@Test
	public void testApplyActivationException() {
		double x = 0.5;
		Layer layer = new Layer(1, 1, null);
		assertThrows(IllegalArgumentException.class, () -> layer.applyActivation(x));
	}

	@Test
	public void testApplyActivationDerivativeSigmoid() {
		double x = 0.5;
		Layer layer = new Layer(1, 1, Function.SIGMOID);
		assertEquals(Activation.sigmoidDerivative(x), layer.applyActivationDerivative(x), 0.0001);
	}

	@Test
	public void testApplyDerivativeException() {
		double x = 0.5;
		Layer layer = new Layer(1, 1, null);
		assertThrows(IllegalArgumentException.class, () -> layer.applyActivationDerivative(x));
	}

	@Test
	public void testApplyActivationTanh() {
		double x = 0.5;
		Layer layer = new Layer(1, 1, Function.TANH);
		assertEquals(Activation.tanh(x), layer.applyActivation(x), 0.0001);
	}

	@Test
	public void testApplyActivationDerivativeTanh() {
		double x = 0.5;
		Layer layer = new Layer(1, 1, Function.TANH);
		assertEquals(Activation.tanhDerivative(x), layer.applyActivationDerivative(x), 0.0001);
	}

	@Test
	public void testApplyActivationRelu() {
		double x = 0.5;
		Layer layer = new Layer(1, 1, Function.RELU);
		assertEquals(Activation.relu(x), layer.applyActivation(x), 0.0001);
	}

	@Test
	public void testApplyActivationDerivativeRelu() {
		double x = 0.5;
		Layer layer = new Layer(1, 1, Function.RELU);
		assertEquals(Activation.reluDerivative(x), layer.applyActivationDerivative(x), 0.0001);
	}

	@Test
	public void testSetNeurons() {
		Layer layer = new Layer(2, 2, Function.RELU);
		Neuron[] ns = new Neuron[3];
		for (int i = 0; i < ns.length; i++) {
			double[] w = new double[2];
			for (int j = 0; j < w.length; j++) {
				w[j] = j;
			}
			Neuron n = new Neuron(w, 0.5);
			ns[i] = n;
		}

		layer.setNeurons(ns);
		assertEquals(ns, layer.getNeurons());

	}

	@Test
	public void testSetFunction() {
		Layer layer = new Layer(2, 2, Function.RELU);
		layer.setFunction(Function.SIGMOID);
		assertEquals(Function.SIGMOID, layer.getFunction());
		layer.setFunction(Function.TANH);
		assertEquals(Function.TANH, layer.getFunction());
		layer.setFunction(Function.RELU);
		assertEquals(Function.RELU, layer.getFunction());

	}

}
