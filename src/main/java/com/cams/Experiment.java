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

public class Experiment {
    public static int charNum = 1;
    static final String settingsFilePath = "src/main/resource/settings.dat";
    static final String csvUniformCubePath = "src/main/resource/2DCube.csv";
    static final String dataStreamsFilePath = "src/main/resource/dataStreams2.dat";
    static final String heirarchyFilePath_1 = "src/main/resource/heirarchy_1.dat";
    static final String heirarchyFilePath_2 = "src/main/resource/heirarchy_2.dat";
    static final String resultsMemoSpaceFile = "src/main/resource/results.csv";

    public static void main(String[] args) {
        long beforeUsedMem = ( Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) /(1024*1024);
        int dataStreamsSize = 0;
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
         int[][] uniformCube = cube.generateCube();
        List<String> indexer = new ArrayList<>();
        TreeNode treeOne = null, treeTwo = null;
        try {
            treeOne = readHeirarchies(heirarchyFilePath_1);
            treeTwo = readHeirarchies(heirarchyFilePath_2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean close = false;
        // for (int rep = 1; rep < 4; rep++) {

            try {
                String line;
                BufferedReader  reader = new BufferedReader(new FileReader(dataStreamsFilePath));

                while (!close) {
                    if ((line = reader.readLine()) != null) {
                        dataStreamsSize++;
                        line = line.trim();
                        if (!line.isEmpty()) {
                            String[] parts = line.split(",", 3);
                            String D1 = parts[0].trim();
                            String D2 = parts[1].trim();
                            int UPDATE_VALUE = Integer.parseInt(parts[2].trim());

                            int ind1 = getIndex(treeOne, D1, indexer);
                            // System.out.println("*******" + D1 + "******" + ind1);

                            // index = -1;
                            indexer = new ArrayList<>();
                            int ind2 = getIndex(treeTwo, D2, indexer);
                            // System.out.println("*******" + D2 + "******" + ind2);

                            indexer = new ArrayList<>();

                            uniformCube[ind2][ind1] = uniformCube[ind2][ind1] + UPDATE_VALUE;
                            System.out.println("Reading Data Steams...");
                        }
                    } else {
                        close = true;
                    }
                }
                   // update CSV file
                   try (FileWriter writer = new FileWriter(csvUniformCubePath)) {
                    for (int i = 0; i < uniformCube.length; i++) {
                        for (int j = 0; j < uniformCube[i].length; j++) {
                            writer.append(Integer.toString(uniformCube[i][j]));
                            if (j < uniformCube[i].length - 1) {
                                writer.append(',');
                            }
                        }
                        writer.append('\n');
                    }
                    // System.out.println("waiting for data");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                close = true;
                e.printStackTrace();
            }

            long afterUsedMem = ( Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) /(1024*1024);
            long actualMemUsed = afterUsedMem - beforeUsedMem;

            try (FileWriter fileWriter = new FileWriter(resultsMemoSpaceFile, true)) {

                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                bufferedWriter.write(dataStreamsSize + "," + actualMemUsed);
                bufferedWriter.newLine();
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Memory used: " + actualMemUsed);
            // close = false;
        // }
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

    public static TreeNode readHeirarchies(String heirarchyFilePath) throws IOException {
        List<TreeNode> listOfNodes = new ArrayList<>();
        TreeNode root = new TreeNode("");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(heirarchyFilePath));
            String line;
            int value = 0, prev = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int nodeOrder = parts.length;
                if (nodeOrder == 1) {
                    root = new TreeNode(parts[nodeOrder - 1]);
                    listOfNodes.add(root);
                } else if (value < nodeOrder) {
                    TreeNode tree = new TreeNode(parts[nodeOrder - 1]);
                    int len = listOfNodes.size() - 1;
                    listOfNodes.get(len).addChild(tree);
                    listOfNodes.add(tree);
                    prev = 1;
                    value = nodeOrder;
                } else if (value == nodeOrder) {
                    if (prev == 1) {
                        listOfNodes.remove(listOfNodes.size() - 1);
                    }
                    prev = 0;
                    TreeNode tree = new TreeNode(parts[nodeOrder - 1]);
                    listOfNodes.get(listOfNodes.size() - 1).addChild(tree);

                } else {
                    TreeNode tree = new TreeNode(parts[nodeOrder - 1]);
                    if (listOfNodes.size() > 1)
                        listOfNodes.remove(listOfNodes.size() - 1);
                    listOfNodes.get(listOfNodes.size() - 1).addChild(tree);
                    listOfNodes.add(tree);
                    value = nodeOrder;
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return root;
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

    static int index = -1;

    public static int getIndex(TreeNode node, String value, List<String> indexer) {
        if (node.getChildren().size() == 0) {
            if (node.getValue().equals(value)) {
                index = indexer.size();
            }
            indexer.add(node.getValue());
        } else {
            for (TreeNode child : node.getChildren()) {
                getIndex(child, value, indexer);
            }
        }
        return index;
    }

}
