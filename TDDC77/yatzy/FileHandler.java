package yatzy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class FileHandler {

	File file;
	String inString;
	int playerAmount;

	public FileHandler(File file) throws IOException {
		this.file = file;
		InputStream is = new FileInputStream(file);
		this.inString = new String(is.readAllBytes(), StandardCharsets.ISO_8859_1);
		
	}
	
	// ------------------------------------------------------------------------------------------------------------

	public Object[][] loadStartTable() {
		Object[][] startTable = new Object[18][loadPlayerAmount()+1];
		
		int i = inString.indexOf('[')+1;
		String str = "";
		for(int n = 0; n < 18;n++) {
			for(int k = 0; k <loadPlayerAmount()+1; k++) {
				while(inString.charAt(i) != ',') {
					str +=inString.charAt(i);
					i++;
				}
				startTable[n][k] = str;
				System.out.println(str);
				str = "";
				i++;
			}
			i +=2;
		}
		return (Object[][]) startTable;
	}
	
	// ------------------------------------------------------------------------------------------------------------

	public String[] loadPlayerList() {

		LinkedList<String> players = new LinkedList<>();
		int i = inString.indexOf('/')+1;
		String player = "";
		while(inString.charAt(i) != '[') {
			player += inString.charAt(i);
			if((inString.charAt(i+1)) == '^') {
				System.out.println(player);
				players.add(player);
				player = "";
				i++;
			}
			i++;
			if(i > 500) {
				break;
			}
		}
		return players.toArray(new String[players.size()]);
	}
	
	// ------------------------------------------------------------------------------------------------------------

	public int loadgameTurn() {
		int i = 0;
		String turn = "";
		while(inString.charAt(i) != '*') {
			turn += inString.charAt(i);
			i++;
		}
		System.out.println(turn);
		return Integer.parseInt(turn);
	}
	
	// ------------------------------------------------------------------------------------------------------------
	
	public int loadPlayerAmount() {
		int i = inString.indexOf('*')+1;
		String str = "";
		while(inString.charAt(i) != '/') {
			str += inString.charAt(i);
			i++;
		}
		System.out.println(str);
		return Integer.parseInt(str);
	}
	
	// ------------------------------------------------------------------------------------------------------------
	
	public void save(Object[][] startTable, String[] playerList,int gameTurn,int playerAmount) throws IOException {
		String outString = String.valueOf(gameTurn) + "*";
		outString += String.valueOf(playerAmount) + "/";
		for(String s: playerList) {
			outString += s +"^";
		}
		
		for(Object[] o : startTable) {
			outString += "[";
			for(Object elem : o) {
				outString += elem + ",";
			}
			outString += "]";
		}
		outString+="~";
		
		OutputStream Os = new FileOutputStream(file);
		Os.write(outString.getBytes());
		Os.close();
		
	}
}
