package com.cams;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CubeGauss {
    private int[][] cube;
    private String csvCubePath= "src/main/resource/2DGaussCube.csv";

    public CubeGauss( int size) {
        cube = new int[size][size];
    }
    public int [][] generateCsvCube() {
        try (FileWriter writer = new FileWriter(csvCubePath)) {
            for (int i = 0; i < cube.length; i++) {
                for (int j = 0; j < cube[i].length; j++) {
                    Random r = new Random();
                    double randomNum = r.nextGaussian() * 100;
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
