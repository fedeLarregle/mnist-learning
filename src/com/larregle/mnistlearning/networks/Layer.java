package com.larregle.mnistlearning.networks;

import java.util.ArrayList;
import java.util.List;

public class Layer {

    private final List<Neuron> neurons;
    private double[] bias;
    private double[] weights;

    public Layer() {
        neurons = new ArrayList<>();
    }

    public final static class Neuron {

    }
}
