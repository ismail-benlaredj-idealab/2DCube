// package com.cams;

// import java.util.ArrayList;
// import java.util.List;
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Scanner;
// import java.io.FileWriter;

// public class GenerateTree {
//     public static int charNum = 1;

//     public static void main(String[] args) {
//         String settingsFilePath = "src/main/resource/settings.dat";
//         String csvUniformCubePath = "src/main/resource/2DCube.csv";

//         Scanner scanner = new Scanner(System.in);
//         Map<String, String> variables = new HashMap<>();
//         try {
//             variables = readFile(settingsFilePath);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         int cubeSize = Integer.parseInt(variables.get("CUBE_SIZE"));
//         CubeUniform cube = new CubeUniform(cubeSize, 5, 100);
//         List<String> indexer = new ArrayList<>();
//         int[][] uniformCube = cube.generateCube();
//         System.out.println(variables.get("FAN_OUT"));

//         TreeNode treeOne = buildTree(cubeSize, Integer.parseInt(variables.get("FAN_OUT")));
//         TreeNode treeTwo = buildTree(cubeSize, Integer.parseInt(variables.get("FAN_OUT")));
//         // Print the tree
//         printTree(treeOne, 0);
//         System.out.println("*****************************");
//         printTree(treeTwo, 0);

//         // // user input first node
//         // System.out.println("Enter node  1");
//         // String input1 = scanner.nextLine();
//         // int ind1 = getIndex(treeOne, input1, indexer);
//         // System.out.println("Reading 1 index " + ind1);

//         // // user input second node
//         // System.out.println("Enter node  2");
//         // String input2 = scanner.nextLine();
//         // int ind2 = getIndex(treeOne, input2, indexer);
//         // System.out.println("Reading 2 index " + ind2);
//          String input1 = variables.get("READ_1");
//          int ind1 = getIndex(treeOne, input1, indexer);
//          System.out.println("Reading*******************" + ind1);
//          index = -1;
//          indexer = new ArrayList<>();
//          String input2 = variables.get("READ_2");
//          int ind2= getIndex(treeTwo, input2, indexer);
//          System.out.println("Reading*******************" + ind2);


//         uniformCube[ind2][ind1] = uniformCube[ind2][ind1] + Integer.parseInt(variables.get("ADD_VALUE"));
//         // update CSV file
//         try (FileWriter writer = new FileWriter(csvUniformCubePath)) {
//             for (int i = 0; i < uniformCube.length; i++) {
//                 for (int j = 0; j < uniformCube[i].length; j++) {
//                     writer.append(Integer.toString(uniformCube[i][j]));
//                     if (j < uniformCube[i].length - 1) {
//                         writer.append(',');
//                     }
//                 }
//                 writer.append('\n');
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // METHODS

//     public static Map<String, String> readFile(String settingsFilePath) throws IOException {
//         Map<String, String> variables = new HashMap<>();
//         try {
//             BufferedReader reader = new BufferedReader(new FileReader(settingsFilePath));
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 line = line.trim();
//                 if (!line.isEmpty()) {
//                     String[] parts = line.split(":", 2);
//                     if (parts.length == 2) {
//                         String variable = parts[0].trim();
//                         String value = parts[1].trim();
//                         variables.put(variable, value);
//                     }
//                 }
//             }
//             reader.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }

//         return variables;
//     }

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

//     //////////////// BUILD TREE
//     public static TreeNode buildTree(int row, int fanOut) {
//         List<TreeNode> currentLevel = new ArrayList<>();
//         // Create leaf nodes for each element
//         for (int i = 0; i < row; i++) {
//             currentLevel.add(new TreeNode(generateRandomChars(charNum)));
//             charNum++;
//         }
//         while (currentLevel.size() > 1) {
//             List<TreeNode> nextLevel = new ArrayList<>();
//             // Pair up nodes to create parents
//             for (int i = 0; i < currentLevel.size(); i += fanOut) {
//                 // Create parent node
//                 TreeNode parent = new TreeNode(generateRandomChars(charNum));
//                 charNum++;

//                 if (i + fanOut > currentLevel.size()) {
//                     for (int j = i; j < currentLevel.size(); j++) {
//                         parent.addChild(currentLevel.get(j));
//                     }
//                 } else {
//                     for (int j = i; j < i + fanOut; j++) {
//                         parent.addChild(currentLevel.get(j));
//                     }
//                 }
//                 nextLevel.add(parent);
//             }
//             currentLevel = nextLevel;
//         }
//         return currentLevel.get(0); // Return the root node
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
