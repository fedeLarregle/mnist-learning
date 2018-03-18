package com.larregle.mnistlearning.functions;

public interface ActivationFunction {

    /**
     * Must be implemented with an specific activation function.
     * This method will apply that function in 'z'
     *
     * @param z input value to the activation function
     * @return resulting output of applying the activation function in 'z'
     */
    double activation(double z);

    /**
     * Must be implemented with an specific derivative of our activation function
     * @see ActivationFunction#activation(double)
     *
     * @param z
     * @return resulting output of applying derivative of the activation function in 'z'
     */
    double derivative(double z);
}
