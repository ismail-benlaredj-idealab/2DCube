// package com.cams;

// import java.util.ArrayList;
// import java.util.List;
// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.HashMap;
// import java.util.Map;
// import java.io.FileWriter;
// import java.util.Random;

// public class WriteHeirarchy {
//     public static int charNum = 1;

//     public static void main(String[] args) {
//         String settingsFilePath = "src/main/resource/settings.dat";
//         final String heirarchyFilePath_1 = "src/main/resource/heirarchy_1.dat";
//         final String heirarchyFilePath_2 = "src/main/resource/heirarchy_2.dat";

//         // Scanner scanner = new Scanner(System.in);
//         Map<String, String> variables = new HashMap<>();
//         try {
//             variables = readFile(settingsFilePath);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         final int cubeSize = Integer.parseInt(variables.get("CUBE_SIZE"));
//         final int FAN_OUT = Integer.parseInt(variables.get("FAN_OUT"));

//         TreeNode treeOne = buildTree(cubeSize, FAN_OUT);
//         TreeNode treeTwo = buildTree(cubeSize, FAN_OUT);
//         // Print the tree
//         printTree(treeOne, 0, heirarchyFilePath_1);
//         System.out.println("*****************************");
//         printTree(treeTwo, 0, heirarchyFilePath_2);


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

//     public static TreeNode readHeirarchies(String heirarchyFilePath) throws IOException {
//         List<TreeNode> listOfNodes = new ArrayList<>();
//         TreeNode root = new TreeNode(""), node = new TreeNode("");
//         try {
//             BufferedReader reader = new BufferedReader(new FileReader(heirarchyFilePath));
//             String line;
//             int value = 0, prev = 0;

//             while ((line = reader.readLine()) != null) {
//                 // System.out.println(line);
//                 String[] parts = line.split(" ");
//                 int nodeOrder = parts.length;
//                 if (nodeOrder == 1) {
//                     root = new TreeNode(parts[nodeOrder - 1]);
//                     listOfNodes.add(root);
//                 } else if (value < nodeOrder) {
//                     TreeNode tree = new TreeNode(parts[nodeOrder - 1]);
//                     int len = listOfNodes.size() - 1;
//                     listOfNodes.get(len).addChild(tree);
//                     listOfNodes.add(tree);
//                     prev = 1;
//                     value = nodeOrder;
//                 } else if (value == nodeOrder) {
//                     if (prev == 1) {
//                         listOfNodes.remove(listOfNodes.size() - 1);
//                         System.out.println(parts[nodeOrder - 1]);

//                     }
//                     prev = 0;
//                     TreeNode tree = new TreeNode(parts[nodeOrder - 1]);
//                     listOfNodes.get(listOfNodes.size() - 1).addChild(tree);

//                 } else {
//                     TreeNode tree = new TreeNode(parts[nodeOrder - 1]);
//                     if (listOfNodes.size() > 1)
//                         listOfNodes.remove(listOfNodes.size() - 1);
//                     listOfNodes.get(listOfNodes.size() - 1).addChild(tree);
//                     listOfNodes.add(tree);
//                     value = nodeOrder;
//                 }

//             }
//             reader.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }

//         return root;
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
//     private static void printTree(TreeNode node, int level, String heirarchyFilePath) {
//         StringBuilder sb = new StringBuilder();
//         for (int i = 0; i < level; i++) {
//             sb.append("  ");
//         }
//         sb.append(node.getValue());
//         System.out.println(sb.toString());
//         try (FileWriter fileWriter = new FileWriter(heirarchyFilePath, true)) {
//             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//             bufferedWriter.write(sb.toString());
//             bufferedWriter.newLine();
//             bufferedWriter.close();
//             fileWriter.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         for (TreeNode child : node.getChildren()) {
//             printTree(child, level + 1, heirarchyFilePath);
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
//                 // create parent node
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

//     public static void generateDataStreamsCams(String dataStreamsFilePath, int cubeSize, int fanOut) {
//         int x = (cubeSize / fanOut) + 1;
//         int min = ((cubeSize / x + 1) * fanOut) + cubeSize;
//         int max = min + cubeSize;
//         try (FileWriter fileWriter = new FileWriter(dataStreamsFilePath, true)) {

//             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

//             Random random = new Random();
//             int randomCharNum = random.nextInt(cubeSize) + 1;
//             String D1 = generateRandomChars(randomCharNum);
//             randomCharNum = random.nextInt((max - min + 1) + 1) + min;
//             System.out.println("/-/-/-/-/-/-/--/--/" + D1);
//             String D2 = generateRandomChars(randomCharNum);
//             System.out.println("/-/-/-/-/-/-/--/--/" + D2);
//             int UPDATE_VALUE = random.nextInt((99999 - 10000) + 1) + 10000;
//             bufferedWriter.write(D1 + "," + D2 + "," + UPDATE_VALUE);
//             bufferedWriter.newLine();
//             bufferedWriter.close();
//             fileWriter.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }

//     }
// }
