import java.io.FileWriter;
import java.io.IOException;

public class addition {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int number = 20;
		int step = 1;
		int summation =0;
		
		for (int i=0; i<number; i++){
			summation+=step;
			step++;
		}

		try {
			FileWriter writer = new FileWriter("eclipse_test.txt");


			writer.write("Summation for number " + number +" is: " + summation + "\n");
			writer.close();

		} catch (IOException e) {
			System.out.println("This is an IO exeption");
		}
		
		
	}

}