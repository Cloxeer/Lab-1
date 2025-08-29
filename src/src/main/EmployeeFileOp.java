import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class EmployeeFileOp {

    public static void main(String[] args) {

        int ageLimit = 50;

        try {

            // Read(scan) employee data from and importing core_employee.csv
            File inputFile = new File("Lab1/core_employee.csv");
            Scanner scanner = new Scanner(inputFile);
            
            // creating a writer to write to old_employee.csv
            FileWriter writer = new FileWriter("old_employee.csv");
            
            // Skip header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            
            // Scan each line with a while loop
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Use CSV parsing method to parse the line
                String[] parts = parseCSVLine(line);

                // Check if the line has enough columns
                if (parts.length >= 16) {  
                    // Get name (first column) and DOB (column 15, index 15)
                    String name = parts[0];
                    String dob = parts[15];
                    
                    // Calculate age from DOB (format: MM/DD/YY)
                    int age = calculateAge(dob);
                    
                    // If age > ageLimit, write to file
                    if (age > ageLimit) {
                        writer.write(name + "," + age + "\n");
                    }
                }
            }
            
            // Close the scanner and writer
            scanner.close();
            writer.close();
            System.out.println("Done! Check old_employee.csv");
            
            // Catch any errors that may occur
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            //Print the full error
            e.printStackTrace();  
        }
    }
    
    // Parse CSV line, handling quoted fields takes arraylist and stringbuilder to store the result
    private static String[] parseCSVLine(String line) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        
        // Loop through each character in the line
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString().trim());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        
        // Add the last field
        result.add(current.toString().trim());
        
        // Convert the arraylist to an array and return it
        return result.toArray(new String[0]);
    }
    
    // Calculate age from DOB string
    private static int calculateAge(String dobString) {
        try {
            // Parse the date (format: MM/DD/YY)
            String[] dateParts = dobString.split("/");
            if (dateParts.length >= 3) {
                int month = Integer.parseInt(dateParts[0]);
                int day = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                
                // Convert 2-digit year to 4-digit year
                // by adding 2000 to the year, if it's less than 50 then it's 2000s, and if it's greater than or equal to 50 it's 1900s
                if (year < 50) {
                    year += 2000;  // 51 becomes 1951
                } else {
                    year += 1900;  // 49 becomes 1949
                }
                
                // Calculate age 
                int age = 2025 - year;
                return age;
            }
        } catch (Exception e) {
            // If date parsing fails, return 0 as default
        }
        return 0;
    }
}