package yatzy.guiObjects;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class Checkbox extends JCheckBox{

	public Checkbox(String text, int xPos, int yPos, int width, int height) {
		setText(text);
		setBounds(xPos,yPos,width,height);
	}
}
