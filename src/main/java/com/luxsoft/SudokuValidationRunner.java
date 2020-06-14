package com.luxsoft;


import java.io.IOException;

public class SudokuValidationRunner {

    public static void main(String[] args) {
        String fileAbsPath = args[0];
        System.out.println("Loading file from location >> " + fileAbsPath);
        try {
            FileValidator validator = new FileValidator(fileAbsPath);
            boolean validSudoku = validator.isValidFormat() && validator.isValidSudokuContent();
            System.out.println("Valid file!");
        } catch (IOException e) {
            System.out.println("Exiting program with code= -1 \n Cause [" + e.getMessage() + "]");
        }
    }
}
