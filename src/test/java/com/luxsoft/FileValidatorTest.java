package com.luxsoft;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class FileValidatorTest {

    FileValidator unitUnderTest;

    private final String RES_PATH = "src/test/resources/";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testValidFormatText() throws IOException {
        //given
        unitUnderTest = new FileValidator(RES_PATH + "valid-text-file.txt");

        //when
        boolean result = unitUnderTest.isValidFormat();

        //then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void testInvalidFormatText() throws IOException {
        //given
        unitUnderTest = new FileValidator(RES_PATH + "invalid-columns-file.txt");

        //when
        boolean result = unitUnderTest.isValidFormat();

        //then
        Assertions.assertThat(result).isTrue();
    }


    @Test
    public void testInvalidFormatCsv() throws IOException {
        //given
        unitUnderTest = new FileValidator(RES_PATH + "invalid-csv-file.csv");

        //when
        boolean result = unitUnderTest.isValidFormat();

        //then
        Assertions.assertThat(result).isTrue();

    }

    @Test
    public void testEmptyFormatCsv() throws IOException {
        //given
        unitUnderTest = new FileValidator(RES_PATH + "invalid-csv-file.csv");

        //then
        expectedException.expect(IOException.class);
        expectedException.expectMessage("Invalid Row/column size!");

        //when
        unitUnderTest.isValidSudokuContent();
    }


    @Test
    public void testValidFormatCsv() throws IOException {
        //given
        unitUnderTest = new FileValidator(RES_PATH + "valid-csv-file.csv");

        //when
        boolean result = unitUnderTest.isValidFormat();

        //then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void testValidTextSudokuContent() throws IOException {
        //given
        unitUnderTest = new FileValidator(RES_PATH + "valid-text-file.txt");

        //when
        boolean result = unitUnderTest.isValidSudokuContent();

        //then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void testInvalidExtraColumnSudokuContent() throws IOException {
        //given
        unitUnderTest = new FileValidator(RES_PATH + "invalid-columns-file.txt");

        //then
        expectedException.expect(IOException.class);
        expectedException.expectMessage("Invalid Row/column size!");

        //when
        unitUnderTest.isValidSudokuContent();
    }

    @Test
    public void testInvalidNumberInSudokuContent() throws IOException {
        //given
        unitUnderTest = new FileValidator(RES_PATH + "invalid-numbers-file.txt");

        //then
        expectedException.expect(IOException.class);
        expectedException.expectMessage("Invalid number(s) in file!");

        //when
        unitUnderTest.isValidSudokuContent();
    }

}