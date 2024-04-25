package com.cams;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;


public class DataStructure {
    public static int charNum = 1;

    public static void main(String[] args) {
        String settingsFilePath = "src/main/resource/settings.dat";
        final String heirarchyFilePath_1 = "src/main/resource/heirarchy_1.dat";
        final String heirarchyFilePath_2 = "src/main/resource/heirarchy_2.dat";

        Map<String, String> variables = new HashMap<>();
        try {
            variables = readFile(settingsFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int cubeSize = Integer.parseInt(variables.get("CUBE_SIZE"));
        final int UMIN = Integer.parseInt(variables.get("UMIN"));
        final int UMAX = Integer.parseInt(variables.get("UMAX"));
        CubeUniform cube = new CubeUniform(cubeSize, UMIN, UMAX);
        cube.generateCube();

        final int FAN_OUT = Integer.parseInt(variables.get("FAN_OUT"));
        final int DEPTH = Integer.parseInt(variables.get("DEPTH"));
        TreeNode treeOne = buildTree(cubeSize,FAN_OUT,DEPTH);
        TreeNode treeTwo = buildTree(cubeSize,FAN_OUT,DEPTH);
        // Print the tree
        printTree(treeOne, 0, heirarchyFilePath_1);
        System.out.println("*****************************");
        printTree(treeTwo, 0, heirarchyFilePath_2);
    }

    // METHODS
    public static Map<String, String> readFile(String settingsFilePath) throws IOException {
        Map<String, String> variables = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(settingsFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(":", 2);
                    if (parts.length == 2) {
                        String variable = parts[0].trim();
                        String value = parts[1].trim();
                        variables.put(variable, value);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return variables;
    }

    ///// Print tree
    private static void printTree(TreeNode node, int level, String heirarchyFilePath) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
        sb.append(node.getValue());
        // System.out.println(sb.toString());
        try (FileWriter fileWriter = new FileWriter(heirarchyFilePath, true)) {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(sb.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (TreeNode child : node.getChildren()) {
            printTree(child, level + 1, heirarchyFilePath);
        }
    }

    //////////////// BUILD TREE
    public static TreeNode buildTree(int row, int fanOut, int depth) {
        List<TreeNode> currentLevel = new ArrayList<>();
        // Create leaf nodes for each element
        for (int i = 0; i < row; i++) {
            currentLevel.add(new TreeNode(generateRandomChars(charNum)));
            charNum++;
        }
        while (currentLevel.size() > 1) {
            List<TreeNode> nextLevel = new ArrayList<>();
            for (int i = 0; i < currentLevel.size(); i += fanOut) {
                // Create parent node
                TreeNode parent = new TreeNode(generateRandomChars(charNum));
                charNum++;

                if (i + fanOut > currentLevel.size()) {
                    for (int j = i; j < currentLevel.size(); j++) {
                        parent.addChild(currentLevel.get(j));
                    }
                } else {
                    for (int j = i; j < i + fanOut; j++) {
                        parent.addChild(currentLevel.get(j));
                    }
                }
                nextLevel.add(parent);
            }
            currentLevel = nextLevel;
            if(depth>1){
                depth--;
                    int size=row/fanOut+1;
                    System.out.println();
                    fanOut = ((int) Math.pow(size, 1/depth))+1;
                }
        }
        return currentLevel.get(0); // Return the root node
    }

    public static String generateRandomChars(int number) {
        String result = "";
        String reapet = "";
        int base = 26;
        if (number > base) {
            int count = number / base;
            if (number % base == 0) {
                count--;
            }
            for (int i = 0; i < count; i++) {
                reapet = reapet + "A";
            }
        }
        int count = 0;
        number = number - 1;
        while (count <= number) {
            result = Character.toString((char) ('A' + (number % base)));
            count++;
        }
        return reapet + result;
    }
}
