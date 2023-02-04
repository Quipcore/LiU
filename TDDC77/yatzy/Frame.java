package yatzy;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class Frame extends JFrame {

	public JFrame frame = new JFrame();
	public abstract void draw();

	public Frame() {
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //FUUUUUUUCK THIS METHOD. SEEMS LIKE IT DOESNT WORK HALF THE FUCKING TIME!
		frame.setResizable(false);
	}

	public void show() {
		//draw();
		frame.setVisible(true);
	}

	public void hide() {
		frame.setVisible(false);
	}
}
