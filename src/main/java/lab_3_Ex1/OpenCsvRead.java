package lab_3_Ex1;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class OpenCsvRead {
    private static final String File_Path = "CommonCsv.csv";
    public static void main(String[] args) throws IOException, CsvValidationException {
        try(CSVReader csvReader = new CSVReaderBuilder(new FileReader(File_Path)).
                withCSVParser(new CSVParserBuilder().build()).withSkipLines(1).build();
        ){
            String[] line;
            while((line = csvReader.readNext()) != null){
                System.out.println("First name: "+ line[0]);
                System.out.println("Second name: "+ line[1]);
                System.out.println("Age: "+ line[2] + "\n");
            }
        }
    }
}
