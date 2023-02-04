package yatzy;

import yatzy.windows.Menu;

public class Main {
	
	private static Frame menu;
	
	public static void main(String[] args) {
		menu = new Menu();
		menu.show();
	}
}
