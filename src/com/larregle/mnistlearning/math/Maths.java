package com.larregle.mnistlearning.math;

public final class Maths {

    public static double[] add(double[] a, double[] b) {
        double[] result = new double[a.length];

        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }

        return result;
    }

    public static double[][] add(double[][] a, double[] b) {
        return null;
    }

    public static double[][] add(double[][] a, double[][] b) {
        return null;
    }

    public static double dot(double[] a, double[] b) {
        double result = 0;

        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }

        return Math.sqrt(result);
    }

}
