package test;

import container.Neuron;
import container.NeuronDemo;

import org.junit.jupiter.api.Test;
import util.GenRandom;

import static org.junit.jupiter.api.Assertions.*;

public class NeuronDemoTest {

	@Test
	public void testDemonstrate() {

		NeuronDemo nDemo = new NeuronDemo();
		Neuron neu = nDemo.getN();
		assertNull(neu);
		
		nDemo.demonstrate();
		neu = nDemo.getN();
		assertEquals(0.2, neu.getBias(), 0.001);
		assertEquals(0.6, neu.getGradient(), 0.001);
		assertEquals(1, neu.getValue(), 0.001);
		assertEquals(0.7, neu.getMaxWeightValue(), 0.001);
		assertEquals(0.1, neu.getMinWeightValue(), 0.001);

		double[] d = neu.getWeights();

		assertEquals(0.2, d[0], 0.001);
		assertEquals(0.3, d[1], 0.001);
		assertEquals(0.4, d[2], 0.001);

	}
}
