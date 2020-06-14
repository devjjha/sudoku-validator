package com.luxsoft;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@AllArgsConstructor
public class FileValidator {

    private final String filePath;
    private final int MAX_ROW_COL_LENGTH = 9;

    public boolean isValidFormat() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("Unable to load file from given path >> " + file.getAbsolutePath());
        }
        if (!file.isFile() && (!file.getName().endsWith(".csv") || !file.getName().endsWith(".txt"))) {
            throw new IOException("Unsupported format! Please provide csv file.");
        }
        return true;
    }

    public boolean isValidSudokuContent() throws IOException {
        List<String> rows = Files.readAllLines(Paths.get(filePath));
        if (rows.size() != MAX_ROW_COL_LENGTH || !isValidColumns(rows)) {
            throw new IOException("Invalid Row/column size!");
        } else if (!isValidNumbers(rows)) {
            throw new IOException("Invalid number(s) in file!");
        }
        rows.forEach(System.out::println);
        return true;
    }

    private boolean isValidColumns(List<String> lines) {
        return lines.stream()
                .map(str -> str.split(",").length == MAX_ROW_COL_LENGTH)
                .allMatch(Boolean::booleanValue);
    }

    private final Predicate<Integer> betweenOneToNine = (num) -> (num > 0 && num < 10);

    private boolean isValidNumbers(List<String> lines) {
        return lines.stream()
                .flatMap(str -> Arrays.stream(str.split(",")))
                .allMatch(s -> betweenOneToNine.test(Integer.valueOf(s)));

    }

}
