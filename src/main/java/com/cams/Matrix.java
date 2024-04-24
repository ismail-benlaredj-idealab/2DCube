// package com.cams;

// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.Random;

// public class Matrix {
 

// public static void main(String[] args) {
//     String csvMatrixPath = "src/main/resource/matrix.csv";
//     int rows = 18; 
//     int columns = 18; 


//     int[][] matrix = new int[rows][columns];
//     Random random = new Random();
//     int minRandom=5;
//     int maxRandom=100;

//     for (int i = 0; i < rows; i++) {
//         for (int j = 0; j < columns; j++) {
//             double randomNum = minRandom + (maxRandom - minRandom) * random.nextDouble();
//             matrix[i][j] =(int)randomNum; 
//         }
//     }

//     try (FileWriter writer = new FileWriter(csvMatrixPath)) {
//         for (int i = 0; i < matrix.length; i++) {
//             for (int j = 0; j < matrix[i].length; j++) {
//                 writer.append(Integer.toString(matrix[i][j]));
//                 System.out.println(matrix[i][j]);
//                 if (j < matrix[i].length - 1) {
//                     writer.append(',');
//                 }
//             }
//             writer.append('\n');
//         }
//     } catch (IOException e) {
//         e.printStackTrace();
//     }
// }
// }
  

