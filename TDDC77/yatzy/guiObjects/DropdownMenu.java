package yatzy.guiObjects;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class DropdownMenu extends JComboBox<Object> {
	
	public DropdownMenu(int xPos, int yPos, int width, int height) {
		setBounds(xPos,yPos,width,height);
	}

	//Replaces the current dropDownMenu with the given array
	public void replace(Object[] replacementList) {
		removeAllItems();
		addItem("Select category");
		for(Object o : replacementList) {
			addItem(o);
		}
	}
}
