package lab_3_Ex1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CommonCsvWrite {
    private static final String File_Path = "CommonCsv.csv";
    public static void main(String[] args) throws IOException{
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(File_Path));

            CSVPrinter csvprinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        ){
            csvprinter.printRecord("First_name", "Second_name", "Age");
            csvprinter.printRecord("Vladislav", "Ababii", "22");
            csvprinter.printRecord("Cerednicenco", "Denis", "23");

            csvprinter.flush();
        }

    }
}
