package com.larregle.mnistlearning.networks;

import java.util.ArrayList;
import java.util.List;

public class Network {

    private static final Network instance;

    private final List<Layer> layers;

    static {
        instance = new Network();
    }

    private Network() {
        layers = new ArrayList<>();
    }

    public static Network getInstance() { return instance; }

    /**
     * Stochastic gradient descent
     *
     * @param epochs number of epochs to iterate through
     * @param miniBatchSize size of mini batches to randomly pick
     */
    public final void SGD(int epochs, int miniBatchSize) { }


    public final void updateBatch() { }

}
