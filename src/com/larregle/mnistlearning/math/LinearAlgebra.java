package com.larregle.mnistlearning.math;

public class LinearAlgebra {

    private static final LinearAlgebra instance;

    static {
        instance = new LinearAlgebra();
    }

    private LinearAlgebra() {}

    public static LinearAlgebra getInstance() { return instance; }

    public double[][] add(double[][] a, double[] b) { return null; }

    public double[][] add(double[][] a, double[][] b) { return null; }

    public double[][] dot(double[][] a, double[][] b) { return null; }
}
