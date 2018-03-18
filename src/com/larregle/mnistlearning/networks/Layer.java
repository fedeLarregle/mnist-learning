package com.larregle.mnistlearning.networks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Layer {

    private final List<Neuron> neurons;

    public Layer(int size) {
        neurons = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            neurons.add(new Neuron());
        }
    }

    public final static class Neuron {
        private static final Random rand;

        private double bias;
        private double weight;

        static {
            rand = new Random();
        }

        /**
         * Starting point for our SGD (stochastic gradient descent in {@link Network})
         */
        public Neuron() {
            bias = rand.nextGaussian();
            weight = rand.nextGaussian();
        }

        public double getBias() {
            return bias;
        }

        public void setBias(double bias) {
            this.bias = bias;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

}
