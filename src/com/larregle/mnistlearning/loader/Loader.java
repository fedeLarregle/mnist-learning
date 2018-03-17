package com.larregle.mnistlearning.loader;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class for loading the labels and images for our MNIST learning purpose
 */
public final class Loader {

    private static final Loader instance;

    private static final Logger logger;

    private static final int LABEL_MAGIC_NUMBER;
    private static final int IMAGE_MAGIC_NUMBER;

    private int[] labels;
    private List<int[][]> images;

    static {
        instance = new Loader();
        logger = Logger.getLogger(Loader.class.getName());
        LABEL_MAGIC_NUMBER = 2049;
        IMAGE_MAGIC_NUMBER = 2051;
    }

    private Loader() {
        images = new ArrayList<>();
    }

    public static Loader getInstance() { return instance; }

    /**
     * This method will extract all the labels from a given "MNIST label file"
     * @param path to the MNIST labels file
     */
    public final void loadLabels(String path) {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(path))) {
            if (dataInputStream.readInt() != LABEL_MAGIC_NUMBER) {
                throw new RuntimeException("Wrong label magic number.");
            }
            int amountOfLabels = dataInputStream.readInt();
            int[] labels = new int[amountOfLabels];

            for (int i = 0; i < amountOfLabels; i++) {
                labels[i] = dataInputStream.readInt();
            }
            this.labels = labels;
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * This method will extract all the images from a given "MNIST images file"
     * @param path to the MNIST images file
     */
    public final void loadImages(String path) {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(path))) {
            if (dataInputStream.readInt() != IMAGE_MAGIC_NUMBER) {
                throw new RuntimeException("Wrong image magic number.");
            }
            int amountOfImages = dataInputStream.readInt();
            int rows = dataInputStream.readInt();
            int cols = dataInputStream.readInt();

            for (int i = 0; i < amountOfImages; i++) {
                images.add(readImage(rows, cols, dataInputStream));
            }

        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private int[][] readImage(int rows, int cols, DataInputStream data) throws IOException {
        int [][] image = new int[rows][];
        for (int i = 0; i < rows; i++) {
            int[] row = new int[cols];
            for (int j = 0; j < cols; j++) {
                 row[j] = data.readInt();
            }
            image[i] = row;
        }
        return image;
    }

    public int[] getLabels() {
        return labels;
    }

    public List<int[][]> getImages() {
        return images;
    }

}
