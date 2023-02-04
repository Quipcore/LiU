package labb5;

import java.util.Iterator;
import java.util.Scanner;

public class WordQuiz {

	private Scanner scan = new Scanner(System.in);
	private Dictionary dic = new Dictionary();

	public WordQuiz(Dictionary dictionary) {
		this.dic = dictionary;
	}

	public void runQuiz() {
		int AmountOfGuesses = 1;
		boolean correctGuess = false;;
		while (true) {
			Iterator<Word> it = dic.terms().iterator();
			while (it.hasNext()) {
				Word keyWord = it.next();
				System.out.println("Word to translate: " + keyWord);
				System.out.print("Entry: ");
				String guess = scan.nextLine();

				for (Word w : dic.lookup(keyWord)) {
					if (w.equals(new Word(guess))) {
						correctGuess = true;
						break;
					}
				}
				
				if(correctGuess) {
					it.remove();
					System.out.println("Correct");
					correctGuess = false;
				}else {
					System.out.println("WRONG!");
				}
				
				
				System.out.println("");
			}
			if (dic.size() == 0) {
				System.out.println("YAY " + AmountOfGuesses);
				return;
			}
			AmountOfGuesses++;
		}
	}
}
