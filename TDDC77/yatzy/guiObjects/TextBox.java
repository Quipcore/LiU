package yatzy.guiObjects;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TextBox extends JTextField {

	public TextBox(String text, int xPos, int yPos, int width, int height) {
		setText(text);
		setBounds(xPos,yPos,width,height);
		setEditable(false);
	}	
}
