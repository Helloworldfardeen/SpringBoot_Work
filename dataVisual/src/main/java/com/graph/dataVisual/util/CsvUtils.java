package com.graph.dataVisual.util;

import com.opencsv.CSVReader;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    public static List<Double> parseCsv(Reader reader) throws Exception {
        List<Double> data = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(reader)) {
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                for (String value : nextLine) {
                    data.add(Double.parseDouble(value));
                }
            }
        }
        return data;
    }
}
