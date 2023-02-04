package yatzy.windows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import yatzy.Frame;
import yatzy.guiObjects.TextBox;

@SuppressWarnings("serial")
public class Highscore extends Frame {

	// Auto sorted map
	TreeMap<Integer, String> highscoreSet = new TreeMap<>();

	JTextField title;
	JTextArea text = new JTextArea();

	public Highscore() {
		frame.setLayout(null);
		frame.setAlwaysOnTop(true);
	}

	@Override
	public void draw() {
		frame.setSize(20,150);
		title = new TextBox("HIGHSCORES", 5, 0, 100, 25);
		text.setBounds(5, 25, 100, 83);
		text.setEditable(false);
		
		String url = "D:/Eclipse Workspace/TDDC77Labbar/src/Yatzy/resources/highscoreTable.txt";
		try {
			read(url);
			write(url);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String str2 = "";
		int n = highscoreSet.size();
		for (int i : highscoreSet.keySet()) {
			System.out.println(i + ":" + highscoreSet.get(i));
			str2 = n + ". " + highscoreSet.get(i) + " - " + i +"\n" +str2; 
			n--;
		}
		text.setText(str2);
		
		frame.add(title);
		frame.add(text);
	}

	private void read(String url) throws IOException {
		InputStream is = new FileInputStream(new File(url));

		String inString = new String(is.readAllBytes(), StandardCharsets.ISO_8859_1);
		String key = "";
		String value = "";
		boolean Where_to_write = true;
		for (int i = 0; i < inString.length(); i++) {
			if (inString.charAt(i) == ':') {
				Where_to_write = false;
			} else if (inString.charAt(i) == '/') {
				Where_to_write = true;
				addScore(Integer.parseInt(key), value);
				key = "";
				value = "";
			} else {
				if (Where_to_write) {
					key += inString.charAt(i);
				} else {
					value += inString.charAt(i);
				}
			}
		}
	}

	private void write(String url) throws IOException {

		OutputStream Os = new FileOutputStream(new File(url));

		String str = "";
		for (int i : highscoreSet.keySet()) {
			str += i + ":" + highscoreSet.get(i) + "/";
		}
		Os.write(str.getBytes());
		Os.close();
	}

	public void addScore(int Score, String name) {
		highscoreSet.put(Score, name);
	}

}
