package yatzy.guiObjects;

import javax.swing.JButton;

@SuppressWarnings("serial")
/**
 * 
 * @author felix
 *
 */
public class Button extends JButton {

	/**
	 * 
	 * @param text
	 * @param xPos
	 * @param yPos
	 * @param width
	 * @param height
	 */
	public Button(String text, int xPos, int yPos, int width, int height) {
		setText(text);
		setBounds(xPos,yPos,width,height);
	}	
}
