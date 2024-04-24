// package com.cams;

// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.Random;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// class TreeNode {
//     private final String value;
//     private final List<TreeNode> children;

//     public TreeNode(String value) {
//         this.value = value;
//         this.children = new ArrayList<>();
//     }

//     public void addChild(TreeNode child) {
//         children.add(child);
//     }

//     public String getValue() {
//         return value;
//     }

//     public List<TreeNode> getChildren() {
//         return children;
//     }
// }

// public class RandomTree {

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         String csvMatrixPath = "src/main/resource/matrix.csv";

//         int depth = 3; // depth of the tree
//         int fanOut = 2; // fan-out of each node

//         // Generate trees
//         TreeNode Dim1 = generateTree(depth, fanOut);
//         TreeNode Dim2 = generateTree(depth, fanOut);
//         printTree(Dim1, 1);
//         System.out.println("*****************************");
//         printTree(Dim2, 1);

//         int rows = 8;
//         int columns = 8;

//         int[][] matrix = new int[rows][columns];
//         Random random = new Random();
//         int minRandom = 5;
//         int maxRandom = 100;

//         List<String> indexer = new ArrayList<>();


//         // Create a 2D array and write it to a CSV file
//         try (FileWriter writer = new FileWriter(csvMatrixPath)) {
//             for (int i = 0; i < matrix.length; i++) {
//                 for (int j = 0; j < matrix[i].length; j++) {
//                     double randomNum = minRandom + (maxRandom - minRandom) * random.nextDouble();
//                     matrix[i][j] = (int) randomNum;
//                     writer.append(Integer.toString(matrix[i][j]));
//                     if (j < matrix[i].length - 1) {
//                         writer.append(',');
//                     }
//                 }
//                 writer.append('\n');
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         System.out.println("Enter node value 1");
//         String input1 = scanner.nextLine();
//         int ind1 = getIndex(Dim1, input1, indexer);
//         System.out.println("Reading 1 index " + ind1);

//         System.out.println("Enter node value 2");
//         String input2 = scanner.nextLine();
//         indexer = new ArrayList<>();
//         int ind2 = getIndex(Dim2, input2, indexer);
//         System.out.println("Reading 2 index " + ind2);

//         int addVal = scanner.nextInt();

//         System.out.println("Add " + addVal);

//         matrix[ind1][ind2] = matrix[ind1][ind2] + addVal;
//         // update CSV file
//         try (FileWriter writer = new FileWriter(csvMatrixPath)) {
//             for (int i = 0; i < matrix.length; i++) {
//                 for (int j = 0; j < matrix[i].length; j++) {
//                     writer.append(Integer.toString(matrix[i][j]));
//                     if (j < matrix[i].length - 1) {
//                         writer.append(',');
//                     }
//                 }
//                 writer.append('\n');
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // Generate a tree
//     private static TreeNode generateTree(int depth, int fanOut) {
//         if (depth <= 0) {
//             return new TreeNode(generateRandomChars());
//         }

//         TreeNode node = new TreeNode(generateRandomChars());
//         for (int i = 0; i < fanOut; i++) {
//             node.addChild(generateTree(depth - 1, fanOut));
//         }
//         return node;
//     }

//     //// Generate random chars 2
//     public static String generateRandomChars() {
//         Random random = new Random();
//         char[] chars = new char[2];
//         for (int i = 0; i < 2; i++) {
//             chars[i] = (char) (random.nextInt(26) + 'a');
//         }
//         return new String(chars);
//     }

//     ///// Print tree
//     public static void printNode(TreeNode node) {
//         printTree(node, 2);
//     }

//     private static void printTree(TreeNode node, int level) {
//         StringBuilder sb = new StringBuilder();
//         for (int i = 0; i < level; i++) {
//             sb.append("  ");
//         }
//         sb.append(node.getValue());
//         System.out.println(sb.toString());

//         for (TreeNode child : node.getChildren()) {
//             printTree(child, level + 1);
//         }
//     }

//     static int index = -1;

//     public static int getIndex(TreeNode node, String value, List<String> indexer) {
//         if (node.getChildren().size() == 0) {
//             if (node.getValue().equals(value)) {
//                 index = indexer.size();
//             }
//             indexer.add(node.getValue());
//         } else {
//             for (TreeNode child : node.getChildren()) {
//                 getIndex(child, value, indexer);
//             }
//         }
//         return index;
//     }
   
// }
