import java.util.Scanner;

public class ToFarenheit {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in); 
		final double TEMP_CONVERT = 1.8;

		System.out.print("Type in temp- in celcius:");
		double cel = s.nextDouble();
		double fa;

		fa = (cel * TEMP_CONVERT) + 32;

		System.out.println("The Farenheit equivalence is " + fa + ".");

	}

}
