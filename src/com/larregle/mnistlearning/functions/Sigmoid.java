package com.larregle.mnistlearning.functions;

import java.util.HashMap;
import java.util.Map;

public class Sigmoid implements ActivationFunction {

    private static final Sigmoid instance;

    private final Map<Double, Double> cache;

    static {
        instance = new Sigmoid();
    }

    public Sigmoid() {
        cache = new HashMap<>();
    }

    public static Sigmoid getInstance() { return instance; }

    /**
     * {@inheritDoc}
     *
     * Basic sigmoid function impl
     * @param z input value to the activation function
     * @return
     */
    @Override
    public double activation(double z) {
        if (cache.get(z) == null) {
            cache.put(z, 1.0/(1.0 + Math.exp(z)));
        }
        return cache.get(z);
    }

    /**
     * {@inheritDoc}
     *
     * @param z
     * @return
     */
    @Override
    public double derivative(double z) {
        return activation(z) * (1 - activation(z));
    }
}
