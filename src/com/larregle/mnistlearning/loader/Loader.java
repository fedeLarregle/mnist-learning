package com.larregle.mnistlearning.loader;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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
        ByteBuffer buffer = allocateBuffer(path);

        if (buffer.getInt() != LABEL_MAGIC_NUMBER) {
            throw new RuntimeException("Wrong label magic number.");
        }
        int amountOfLabels = buffer.getInt();
        int[] labels = new int[amountOfLabels];

        for (int i = 0; i < amountOfLabels; i++) {
            labels[i] = buffer.get() & 0xFF; // to unsigned
        }
        this.labels = labels;

    }

    /**
     * This method will extract all the images from a given "MNIST images file"
     *
     * @param path to the MNIST images file
     */
    public final void loadImages(String path) {
        ByteBuffer byteBuffer = allocateBuffer(path);

        if (byteBuffer.getInt() != IMAGE_MAGIC_NUMBER) {
            throw new RuntimeException("Wrong image magic number.");
        }

        int amountOfImages = byteBuffer.getInt();
        int rows = byteBuffer.getInt();
        int cols = byteBuffer.getInt();

        for (int i = 0; i < amountOfImages; i++) {
            try {
                images.add(readImage(rows, cols, byteBuffer));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private ByteBuffer allocateBuffer(String path) {
        ByteBuffer byteBuffer = null;
        ByteArrayOutputStream outputStream = null;

        try (FileInputStream file = new FileInputStream(path); FileChannel fileChannel = file.getChannel()) {
            int fileSize = (int) fileChannel.size();
            byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            outputStream = new ByteArrayOutputStream();

            for (int i = 0; i < fileSize; i++) {
                outputStream.write(byteBuffer.get());
            }

        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            byteBuffer.clear();
        }
        return ByteBuffer.wrap(outputStream.toByteArray());
    }

    private int[][] readImage(int rows, int cols, ByteBuffer data) throws IOException {
        int [][] image = new int[rows][];
        for (int i = 0; i < rows; i++) {
            int[] row = new int[cols];
            for (int j = 0; j < cols; j++) {
                row[j] = data.get() & 0xFF; // To unsigned
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
