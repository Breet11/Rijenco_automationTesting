package lab_3_Ex1;

import java.io.IOException;
import java.io.Reader;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CommonCsvRead {
    private static final String File_Path = "CommonCsv.csv";
    public static void main(String[] args) throws IOException {
        try( Reader reader = Files.newBufferedReader(Paths.get(File_Path));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.builder().
                     setHeader().
                     setSkipHeaderRecord(true).
                     build());
        ){
            for(CSVRecord csvRecord : csvParser){
                String firstName = csvRecord.get("First_name");
                String secondName = csvRecord.get("Second_name");
                String age = csvRecord.get("Age");

                System.out.println("First name: " + firstName);
                System.out.println("Second name: " + secondName);
                System.out.println("Age: "+ age);
            }
        }
    }
}
