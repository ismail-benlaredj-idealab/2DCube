package com.cams;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CubeUniform {
    private int[][] cube;
    private int min;
    private int max;
    private String csvCubePath = "src/main/resource/2DCube.csv";

    public CubeUniform(int size, int min, int max) {
        cube = new int[size][size];
        this.min = min;
        this.max = max;
    }

    public int[][] generateCube() {
        Random random = new Random();
        // Create a 2D array and write it to a CSV file
        try (FileWriter writer = new FileWriter(csvCubePath)) {
            for (int i = 0; i < cube.length; i++) {
                for (int j = 0; j < cube[i].length; j++) {
                    double randomNum = min + (max - min) * random.nextDouble();
                    cube[i][j] = (int) randomNum;
                    writer.append(Integer.toString(cube[i][j]));
                    if (j < cube[i].length - 1) {
                        writer.append(',');
                    }
                }
                writer.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cube;
    }
}
