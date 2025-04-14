package lab_3_Ex1;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OpenCsvWrite {
    private static final String File_Path = "OpenCsv.csv";
    public static void main(String[] args) throws IOException {
        File file = new File(File_Path);

        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[] {"First_name", "Second_name", "Age"});
            data.add(new String[] {"Vladimir", "Rijenco", "21"});
            data.add(new String[] {"Artem", "Dorosenco", "22"});
            writer.writeAll(data);
        }
    }
}
