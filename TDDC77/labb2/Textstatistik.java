package labb2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Textstatistik { 

	public static void main(String[] args) throws IOException{ 
		
		BufferedReader textFil = new BufferedReader(new FileReader(args[0]));
			
		int meningar = 0;	
		double ord = 0;
		int skiljetecken = 0;
		double bokstäver = 0;		
		String ma = ".?!:;";
		
		while (textFil.ready()) { //Loopar genom texten för att ta ut alla rader.
			String rad = textFil.readLine();
			
			for(int i = 0; i < rad.length(); i++) { //Går igenom varje rad och tar ut tecken.  
				if (Character.isAlphabetic(rad.charAt(i))){
					bokstäver++;
				}
				else if(Character.isSpaceChar(rad.charAt(i))){
					ord++;
				}
				else {
					if (memberOf(rad.charAt(i), ma)){
						meningar++;
						
					}
					//Skiljetecken inkluderar .!? etc
					if (memberOfst(rad.charAt(i))){
						skiljetecken++;						
					}
				}								
			}
			
			if (rad.length()>0) { //Om ett ord är i slutet på meningen så finns inget space efter. Vi tar ut detta ord här. Fin lösning... Eller hur?
				ord++;
			}					
		
		}
		
		
		double medel = bokstäver/ord;
		medel = Math.round(medel*100.00)/100.0;
			
		System.out.println("Antal meningar: " + meningar);
		System.out.println("Antal ord: " + ord);
		System.out.println("Antal skiljetecken: " + skiljetecken);
		System.out.println("Antal bokstäver: " + bokstäver);
		System.out.println("Medellängd på ord är: " + medel);
		textFil.close();
		
	}
	
	public static boolean memberOfst(char skiljetecken) {
		
		boolean st = false;
		/* Kollar om tecknet är ett skiljetecken genom ascii-tabellen.
		 */
		if (skiljetecken >= 32 && skiljetecken <= 63) {
			st = true;
			return st;
		}
		else {
			return st;
		}
						
	}	
	public static boolean memberOf(char tecken, String meningsavslut) {
		for (int i = 0; i < meningsavslut.length(); i++) {
			
			if(tecken == meningsavslut.charAt(i)) {
				return true;
			}
		}
	
		return false;
	}
}


	
