package com.user.command.app.file_handling.house_file;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.user.command.app.file_handling.house_file.reader.file.csv.CsvFileReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class PdfConverter {
    public static void main(String[] args) {
        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/house5bhk.csv"));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                System.out.println(nextLine[0]);
                String test;
                test = nextLine[0];

                // step 1
                Document document = new Document();

                // step 2
                PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/Test.pdf"));

                // step 3
                document.open();

                // step 4
                PdfPTable arrayTable3 = new PdfPTable(1);
                arrayTable3.setHorizontalAlignment(Element.ALIGN_LEFT);

                Phrase phrase1 = new Phrase(nextLine[0]);
                PdfPCell arrayDetailsCell3 = new PdfPCell();

                arrayDetailsCell3.addElement(phrase1);

                // Add the cell to the table
                arrayTable3.addCell(arrayDetailsCell3);

                // Add table to the document
                document.add(arrayTable3);

                // step 5
                document.close();
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
