// package com.cams;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// public class GenerateTree {

//     public static void main(String[] args) {

//         int[] arr2;

//         // List<String[]> tree = new ArrayList<>();
//         int charNum = 1;
//         TreeNode tree1 = new TreeNode(generateRandomChars(charNum));

//         int index = 0;
//         int fanOut = 2;
//         int row = 10;
//         // while (index < row) {
//         //     tree1.addChild(new TreeNode(generateRandomChars(charNum)));
//         //     index++;
//         //     charNum++;
//         // }
//         // index = 0;
//         // while (index <= row) {
//         //     if (index + fanOut > row) {

//         //         tree1.addChild(new TreeNode(generateRandomChars(charNum)));
//         //         tree1.removeChild(0);
//         //         charNum++;
//         //         index++;
//         //     } else {
//         //         for (int i = index; i < fanOut; i++) {
//         //             tree1.addChild(new TreeNode(generateRandomChars(charNum)));
//         //             tree1.removeChild(0);
//         //             charNum++;
//         //             index++;
//         //         }
//         //     }
         
//         // }
//         // index = 0;
//         // while (true) {
//         // if (index + fanOut > tree1.getChildren().size()) {
//         // TreeNode parent = new TreeNode(generateRandomChars(charNum));
//         // for (TreeNode x : tree1.getChildren().subList(index,
//         // tree1.getChildren().size())) {
//         // parent.addChild(x);
//         // }
//         // tree1.addChild(parent);
//         // index=index+1;
//         // charNum++;
//         // break;
//         // }else{

//         // TreeNode parent = new TreeNode(generateRandomChars(charNum));
//         // charNum++;
//         // for (TreeNode x : tree1.getChildren().subList(index, index+fanOut)) {
//         // parent.addChild(x);

//         // }
//         // index=index+fanOut;
//         // tree1.addChild(parent);
//         // }
//         // }

//         // for (TreeNode x : tree1.getChildren()) {
//         //     System.out.println("******************" + x.getValue());
//         //     // for (TreeNode y : x.getChildren()) {
//         //     // System.out.println(y.getValue());
//         //     // }
//         // }
//         // int length = tree1.getChildren().size();
//         // TreeNode root = tree1.getChildren().get(length-1);
//         // printNode(root);

    
//         TreeNode root = buildTree(10);

//         // Print the tree
//         printTree(root, 0);
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
// ////////////////
// public static TreeNode buildTree(int row) {
//     List<TreeNode> currentLevel = new ArrayList<>();
//     int charNum=1;
//     // Create leaf nodes for each element
//     for (int i = 0; i < row; i++) {
//         currentLevel.add(new TreeNode(generateRandomChars(charNum)));
//         charNum++;
//     }

//     while (currentLevel.size() > 1) {
//         List<TreeNode> nextLevel = new ArrayList<>();

//         // Pair up nodes to create parents
//         for (int i = 0; i < currentLevel.size(); i += 2) {
//             TreeNode left = currentLevel.get(i);
//             TreeNode right = (i + 1 < currentLevel.size()) ? currentLevel.get(i + 1) : null;

//             // Create parent node
//             TreeNode parent = new TreeNode(generateRandomChars(charNum));
//             charNum++;
//             parent.addChild(left);
//             if (right != null) {
//                 parent.addChild(right);
//             }
//             nextLevel.add(parent);
//         }
//         currentLevel = nextLevel;
//     }

//     return currentLevel.get(0); // Return the root node
// }
// }
