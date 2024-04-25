package com.cams;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.util.Random;


public class DataStreamsCams {
    public static int charNum = 1;

    public static void main(String[] args) {
        String settingsFilePath = "src/main/resource/settings.dat";
        final String dataStreamsFilePath = "src/main/resource/dataStreams3.dat";

        // Scanner scanner = new Scanner(System.in);
        Map<String, String> variables = new HashMap<>();
        try {
            variables = readFile(settingsFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
         final int cubeSize = Integer.parseInt(variables.get("CUBE_SIZE"));

        final int FAN_OUT = Integer.parseInt(variables.get("FAN_OUT"));

        for (int i = 0; i < 300000; i++) {
            generateDataStreamsCams(dataStreamsFilePath, cubeSize, FAN_OUT);
        }
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

    public static String generateSubRandomChars(int number) {
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
    public static String finalGenerateSubRandomChars(int number) {
        String str = generateSubRandomChars(number);
        String s = "";
        String sub = "";
        for (int i = 1; i < str.length() - 1; i++) {
            if (str.charAt(i) == 'A' && str.charAt(i - 1) == 'A') {
                s += str.charAt(i);
            } else {
                s = "";
            }
        }
        if (s.length() > 0) {
            sub = generateSubRandomChars(s.length()-1);
          s= str.substring(1, s.length());
        }
        return str.charAt(0) +str.substring(1, str.length()).replace(s, sub);
    }
  
    public static void generateDataStreamsCams(String dataStreamsFilePath, int cubeSize, int fanOut) {
        int x = (cubeSize / fanOut) + 1;
        int min = ((cubeSize / x+1) * fanOut)+cubeSize;
        int max = min + cubeSize;
        try (FileWriter fileWriter = new FileWriter(dataStreamsFilePath, true)) {

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            Random random = new Random();
            int randomCharNum = random.nextInt(cubeSize) + 1;
            String D1 = finalGenerateSubRandomChars(randomCharNum);
            randomCharNum = random.nextInt((max - min+1) + 1) + min;
            System.out.println("/-/-/-/-/-/-/--/--/" + D1);
            String D2 = finalGenerateSubRandomChars(randomCharNum);
            System.out.println("/-/-/-/-/-/-/--/--/" + D2);
            int UPDATE_VALUE = random.nextInt((1000 - 900) + 1) + 900;
            bufferedWriter.write(D1 + "," + D2 + "," + UPDATE_VALUE);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
