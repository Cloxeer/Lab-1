
import java.io.FileWriter;
import java.io.IOException;


public class welcome {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//double currentTime = System.currentTimeMillis();

		
		System.out.println("Welcome to Java");

		try {
			FileWriter writer = new FileWriter("eclipse_test.txt");


			writer.write("The current time is " + System.currentTimeMillis() + "\n");
			writer.close();

		} catch (IOException e) {
			System.out.println("This is an IO exeption");
		}


	}

}