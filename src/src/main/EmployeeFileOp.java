import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class EmployeeFileOp {

   public static void main(String[] args) {
       
    try {
        
    
    File inputFile = new File("HRDataset_v14.csv");
    Scanner scanner = new Scanner(inputFile);

    FileWriter writer = new FileWriter("old_employee.csv");
    

    while (scanner.hasNextLine()) {
        String Line = scanner.nextLine();
        writer.write(Line + "\n");


    }
    scanner.close();
    writer.close();

    } catch (IOException e) {
        System.out.println("This is a IO Exception");
    }


   }


}







