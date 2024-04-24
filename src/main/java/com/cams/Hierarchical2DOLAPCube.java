// package com.cams;

// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.Random;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// class TreeNode {
//     private final String value;
//     public   List<TreeNode> children;

//     public TreeNode(String value) {
//         this.value = value;
//         this.children = new ArrayList<>();
//     }

//     public void addChild(TreeNode child) {
//         children.add(child);
//     }
//     public void removeChild(int index) {
//         children.remove(index);
//     }
//     public String getValue() {
//         return value;
//     }

//     public List<TreeNode> getChildren() {
//         return children;
//     }
// }

// public class Hierarchical2DOLAPCube {
//     public static int charNum = 1;

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         String csvuniformCUbePath = "src/main/resource/2DCube.csv";

//         System.out.println("Enter depth");
//         int depth = scanner.nextInt();
//          // depth of the tree
//         System.out.println("Enter fan-out");
//         int fanOut = scanner.nextInt(); // fan-out of each node
//         // Generate trees
//         TreeNode Dim1 = generateTree(depth, fanOut);
//         charNum = 1;
//         TreeNode Dim2 = generateTree(depth, fanOut);
//         printTree(Dim1, 1);
//         System.out.println("*****************************");
//         printTree(Dim2, 1);

//         int rows = (int) Math.pow(fanOut, depth);

//         List<String> indexer = new ArrayList<>();
//         CubeUniform cube = new CubeUniform(rows, 5, 100);
//         int[][] uniformCUbe = cube.generateCube();

//         scanner.nextLine();
//         System.out.println("Enter node  1");
//         String input1 = scanner.nextLine();
//         int ind1 = getIndex(Dim1, input1, indexer);
//         System.out.println("Reading 1 index " + ind1);

//         System.out.println("Enter node  2");
//         String input2 = scanner.nextLine();
//         indexer = new ArrayList<>();
//         int ind2 = getIndex(Dim2, input2, indexer);


//         System.out.println("Reading 2 index " + ind2);

//         System.out.println("Enter update value");
//         int addVal = scanner.nextInt();

//         System.out.println("Add " + addVal);

//         uniformCUbe[ind2][ind1] = uniformCUbe[ind2][ind1] + addVal;

//         // update CSV file
//         try (FileWriter writer = new FileWriter(csvuniformCUbePath)) {
//             for (int i = 0; i < uniformCUbe.length; i++) {
//                 for (int j = 0; j < uniformCUbe[i].length; j++) {
//                     writer.append(Integer.toString(uniformCUbe[i][j]));
//                     if (j < uniformCUbe[i].length - 1) {
//                         writer.append(',');
//                     }
//                 }
//                 writer.append('\n');
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         System.out.println(generateRandomChars(52).toString());

//         CubeGauss gauss = new CubeGauss(16);
//         gauss.generateCsvCube();

//         CubeZipf zipf = new CubeZipf(16);
//         zipf.generateZipfCube();
//     }

//     // Generate a tree
//     private static TreeNode generateTree(int depth, int fanOut) {
//         if (depth <= 0) {
//             return new TreeNode(generateRandomChars(charNum));
//         }

//         TreeNode node = new TreeNode(generateRandomChars(charNum));
//         for (int i = 0; i < fanOut; i++) {
//             charNum++;
//             node.addChild(generateTree(depth - 1, fanOut));
//         }
//         return node;
//     }

//     //// Generate random chars 2
//     public static String generateRandomChars(int number) {
//         String result = "";
//         String reapet = "";
//         int base = 26;
//         if (number > base) {
//             int count = number / base;
//             if (number % base == 0) {
//                 count--;
//             }
//             for (int i = 0; i < count; i++) {
//                 reapet = reapet + "A";
//             }
//         }

//         int count = 0;
//         number = number - 1;
//         while (count <= number) {
//             result = Character.toString((char) ('A' + (number % base)));
//             count++;
//         }
//         return reapet + result;
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
