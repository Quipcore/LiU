package labb5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		Dictionary sweng = new Dictionary();
		
		sweng.add("hej", "hello");
		sweng.add("hej", "hi");
		sweng.add("hej", "hey");
		sweng.add("godnatt", "good night");
		sweng.add("nattinatti", "good night");
		sweng.add("fågel", "bird");
		sweng.add("hund", "dog");
		sweng.add("katt", "cat");
		
		File targetFile = new File("src/labb5/resources/test.txt");
		sweng.save(new FileOutputStream(targetFile));
		
		Dictionary loaded = new Dictionary();	
		loaded.load(new FileInputStream(targetFile));
		
		WordQuiz wq = new WordQuiz(loaded);
		wq.runQuiz();
		
	}

}
