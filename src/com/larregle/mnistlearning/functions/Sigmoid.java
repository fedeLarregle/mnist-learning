package com.larregle.mnistlearning.functions;

public class Sigmoid implements ActivationFunction {

    private static final Sigmoid instance;

    static {
        instance = new Sigmoid();
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
        return 1.0/(1.0 + Math.exp(z));
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
