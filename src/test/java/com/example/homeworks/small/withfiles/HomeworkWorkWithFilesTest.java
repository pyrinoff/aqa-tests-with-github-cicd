package com.example.homeworks.small.withfiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Disabled
public class HomeworkWorkWithFilesTest {

    public static final String XLS_FILENAME = "smeta.xlsx";
    public static final String CSV_FILENAME = "exam.csv";
    public static final String PDF_FILENAME = "part.pdf";
    static final String ZIP_FILEPATH = "homework13.zip";
    static final List<String> FILENAMES = List.of(new String[]{
            XLS_FILENAME,
            CSV_FILENAME,
            PDF_FILENAME
    });

    @Test
    @DisplayName("Проверяем, что все нужные файлы есть в списке")
    void checkZipContainsAllFiles() {
        final List<String> fileNames = new ArrayList<>();
        try (
                InputStream inputStream = HomeworkWorkWithFilesTest.class.getClassLoader().getResourceAsStream(ZIP_FILEPATH);
                ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                fileNames.add(zipEntry.getName());

                //check for pdf
                if (zipEntry.getName().equals(PDF_FILENAME)) {
                    final PDF pdf = new PDF(zipInputStream);
                    Assertions.assertEquals("Adobe InDesign CC 2017 (Windows)", pdf.creator);
                }
                //check for csv
                if (zipEntry.getName().equals(CSV_FILENAME)) {
                    final CSVReader csvReader = new CSVReader(new InputStreamReader(zipInputStream));
                    final List<String[]> stringsFromCSV = csvReader.readAll();
                    Assertions.assertEquals("fsdfsdfds", stringsFromCSV.get(0)[1]);
                }

                //check for xls
                if (zipEntry.getName().equals(XLS_FILENAME)) {
                    final XLS xls = new XLS(zipInputStream);
                    Assertions.assertEquals(10, xls.excel.getSheetAt(0).getRow(12).getCell(0).getNumericCellValue());
                }
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(fileNames.containsAll(FILENAMES));
    }

}