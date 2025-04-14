package lab_3_Ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerRead {
    public static void main(String[] args) throws FileNotFoundException{
        try(Scanner Reader = new Scanner(new File("CommonCsv.csv"));
        ){
            while(Reader.hasNextLine()){
                String data = Reader.nextLine();
                System.out.println(data);
            }
        }
    }
}
