package yatzy.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import yatzy.Frame;
import yatzy.guiObjects.Button;
import yatzy.guiObjects.TextBox;

@SuppressWarnings("serial")
public class EndScreen extends Frame {

	Object endScore[][];


	Highscore highscore = new Highscore();

	public EndScreen(Object[][] endScore) {
		frame.setLocation(500, 300);
		frame.setAlwaysOnTop(true);
		this.endScore = endScore;

	}

	public void draw() {
		int max = 0;
		int n = 0;
		for (int i = 0; i < endScore.length - 1; i++) {
			if ((int) endScore[i][1] > max) {
				max = (int) endScore[i][1];
				n = i;
			}
		}

		TextBox winDisplay = new TextBox("Winner: " + endScore[n][0] + " - " + max + "p", 0, 0, 150, 25);
		
		for (int i = 0; i < endScore.length - 1; i++) {
			highscore.addScore((int) endScore[i][1], (String) endScore[i][0]);
		}
		
		Button highScoreButton = new Button("Highscore", 0, 25, 150, 25);
		highScoreButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				highscore.draw();
				highscore.show();
				// dispose();
			}
		});


		frame.add(winDisplay);
		frame.add(highScoreButton);
		//frame.add(scoreDisplay);
		frame.setSize(150, 90); 
		frame.setLayout(null);
	}
}
