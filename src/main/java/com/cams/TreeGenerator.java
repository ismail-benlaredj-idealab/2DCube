package com.cams;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    private final String value;
    private final List<TreeNode> children;

    public TreeNode(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public String getValue() {
        return value;
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}








// public class TreeGenerator {
//     private static final Random random = new Random();

//     public static void main(String[] args) {
//         int depth = 3; // depth of the tree
//         int fanOut = 3; // fan-out of each node

//         TreeNode root = generateTree(depth, fanOut);
//         printTree(root,1);
//     }

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

//     public static void printNode(TreeNode node) {
//         printTree(node,2);
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

//     public static String generateRandomChars() {
//         Random random = new Random();
//         char[] chars = new char[2];
//         for (int i = 0; i < 2; i++) {
//             chars[i] = (char) (random.nextInt(26) + 'a'); 
//         }
//         return new String(chars);
//     }

// }
