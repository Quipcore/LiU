package labb5;

/**
 * Denna klass representerar ett ord, vilket best�r av en teckensekvens kallad
 * text.
 */
public class Word {
	String text;
	/**
	 * Skapar ett nytt ord med den givna texten.
	 */
	public Word(String text) {
		this.text = text;
	}

	/**
	 * J�mf�r detta ord med det specificerade objektet. Resultatet �r true om och
	 * endast om obj ocks� �r ett ord (Word) och har samma text.
	 */
	public boolean equals(Object obj) {
		if(obj.getClass() == this.getClass()) {
			if(obj.toString().equals(toString())) {
				return true;
			}
		}
		return false;	

	}

	/**
	 * Returnerar hashkoden f�r detta ord ber�knat p� ordets text.
	 */
	public int hashCode() {
		return text.hashCode();
	}

	/**
	 * Returnerar texten f�r detta ord.
	 */
	public String toString() {
		return text;
	}
	
}
