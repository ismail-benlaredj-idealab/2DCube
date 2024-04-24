package com.cams;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;


public class App {

    public static void main(String[] args) throws CsvValidationException {
        String csvFilePath = "src/main/resource/sales_data.csv";

        // Geography allGeo = new Geography();
        // AllTime allTime = new AllTime();

        // try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
        //     String[] nextLine;
        //     while ((nextLine = reader.readNext()) != null) {
        //         // for (String cell : nextLine) {
        //         // System.out.println(cell);
        //         // }
        //         Country cn = new Country(nextLine[7]);
        //         C_State st = new C_State(nextLine[8]);
        //         cn.addState(st);
        //         allGeo.addCountry(cn);

        //         Year yr = new Year(nextLine[3]);
        //         Month mt = new Month(nextLine[2]);
        //         yr.addMonth(mt);
        //         allTime.addYear(yr);
        //         System.out.println(nextLine[7] + "**" + nextLine[8] + "**" + nextLine[2]+"**"+nextLine[3]);

        //     }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

       
    }

}
