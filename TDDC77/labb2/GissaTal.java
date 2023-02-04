package labb2;
import java.util.Scanner;

public class GissaTal {

	public static void main(String[] args) {
		Scanner inläsning = new Scanner(System.in);
		System.out.print("Spelare ett, Mata in ett heltal mellan 0 och 100: ");
		int tal1 = inläsning.nextInt();

		for(int i = 100; i > 0; i--) {
			System.out.println("");
		}
		
		boolean gissning = false;
		int räknare = 1;
		
		while (!gissning){
			System.out.print("Spelare två, chansa på ett heltal mellan 0 och 100: ");
			int tal2 = inläsning.nextInt();
			if(tal2 == tal1) {
				System.out.println("Rätt! Du behövde " + räknare + " gissningar.");
				gissning = true;
			}
			else if(tal2>tal1) {
				System.out.println("För stort!");
			}
			else {
				System.out.println("För litet!");
			}
			räknare++;
		}

		
		inläsning.close(); 
	}

}
