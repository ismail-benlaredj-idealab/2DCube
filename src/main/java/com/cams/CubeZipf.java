package com.cams;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class CubeZipf {
    private Random rnd = new Random(System.currentTimeMillis());
    private int size;
    private double skew;
    private double bottom = 0;
    private int[][] zipfCube;
    private String csvCubePath = "src/main/resource/2DZipfCube.csv";
    public CubeZipf(int cubeSize) {
        this.size = 255;
        this.skew = 1.5;
        this.zipfCube = new int[cubeSize][cubeSize];

        for (int i = 1; i <= size; i++) {
            this.bottom += (1.0 / Math.pow(i, this.skew));
        }
    }

    // the next() method returns a random rank id.
    // The frequency of returned rank ids follows the Zipf distribution.
    public int next() {
        int rank;
        double frequency;
        double dice;

        do {
            rank = rnd.nextInt(size);
            frequency = (1.0 / Math.pow(rank + 1, this.skew)) / this.bottom;
            dice = rnd.nextDouble();
        } while (!(dice < frequency));
        return rank+1;
    }
public int [][] generateZipfCube() {
      // Create a 2D array and write it to a CSV file
      try (FileWriter writer = new FileWriter(csvCubePath)) {
        for (int i = 0; i < zipfCube.length; i++) {
            for (int j = 0; j < zipfCube[i].length; j++) {
                int zipfValue = next();
                zipfCube[i][j] = (int) zipfValue;
                writer.append(Integer.toString(zipfCube[i][j]));
                if (j < zipfCube[i].length - 1) {
                    writer.append(',');
                }
            }
            writer.append('\n');
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return zipfCube;
}
}
