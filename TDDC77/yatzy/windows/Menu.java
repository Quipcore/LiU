package yatzy.windows;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import yatzy.FileHandler;
import yatzy.Frame;

@SuppressWarnings("serial")
public class Menu extends Frame {

	private static int playerAmount = 0; // Starting playerAmount should be 1
	private static int aiAmount = 0;

	JPanel panel;
	JTextField textbox;
	JButton addPlayersButton;

	JTextField playerNames;
	LinkedList<String> playerNameList;
	JButton EnterGame;
	JButton endScreenButton;
	JButton load;

	// -----------------------------------------------------------------------------------

	public Menu() {
		draw();
	}

	// -----------------------------------------------------------------------------------

	// Creates the playerNames from the Amount of players
	public static String[] players(String[] names) {
		LinkedList<String> playerList = new LinkedList<>();
		playerList.add("Categories");

		for (int i = 0; i < playerAmount; i++) {
			playerList.add(names[i]);
		}

		return playerList.toArray(new String[playerList.size()]);
	}

	public static String[] computerPlayer() {
		LinkedList<String> compList = new LinkedList<>();
		for (int i = 0; i < aiAmount; i++) {
			compList.add("ai" + (i + 1));
			System.out.println(compList.get(i));
		}

		return compList.toArray(new String[compList.size()]);
	}

	// -----------------------------------------------------------------------------------

	@Override
	public void draw() {
		int rows = 3;
		int columns = 4;
		panel = new JPanel(new GridLayout(rows, columns));

		textbox = new JTextField("Player amount: " + playerAmount);
		textbox.setEditable(false);

		playerNames = new JTextField("");
		playerNameList = new LinkedList<>();

		addPlayersButton = new JButton("Add players");
		EnterGame = new JButton("Enter game");
		endScreenButton = new JButton("End Screen");
		load = new JButton("Load game");

		addPlayersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!playerNames.getText().equals("")) {
					playerAmount++;
					playerNameList.add(playerNames.getText());
					playerNames.setText("Enter player name");
					textbox.setText("Player amount: " + playerAmount);
					playerNames.setText("");
				}
			}
		});

		EnterGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Humancount < 5 then add the rest up to and including 5 as soulless bots
				if (playerAmount < 5) {

					aiAmount = 5 - playerAmount;
				}
				Frame game = new Game(players(playerNameList.toArray(new String[playerNameList.size()])),
						computerPlayer());
				game.draw();
				game.show();
				dispose();
			}
		});

		// !!!Delete before pushing to gitlab!!!!!
		// FOR DEBUGGING PERPOSES!!!!
		endScreenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[][] endScore = { { "pp1", 11 }, { "pp2", 12 }, { "pp3", 13 }, { "pp4", 14 }, { "pp5", 15 } };
				Frame end = new EndScreen(endScore);
				end.draw();
				end.show();
				dispose();
			}
		});

		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("D:/Eclipse Workspace/TDDC77Labbar/src/Yatzy/resources/saveFiles/");
				fc.showOpenDialog(rootPane);
				File file = fc.getSelectedFile();

				if (file.exists()) {
					FileHandler fh;
					try {
						fh = new FileHandler(file);
						Object[][] startTable = fh.loadStartTable();
						String[] playerList = fh.loadPlayerList();
						int gameTurn = fh.loadgameTurn();
						//int aiAmount = fh.loadAiAmount();
						Frame game = new Game(startTable, playerList, gameTurn,0);
						game.draw();
						game.show();
						dispose();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

		});
		panel.add(EnterGame);
		panel.add(addPlayersButton);
		panel.add(playerNames);
		panel.add(endScreenButton);
		panel.add(textbox);
		panel.add(load);

		frame.setContentPane(panel);
		frame.pack();

	}
}
