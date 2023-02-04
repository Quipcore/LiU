package yatzy.windows;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import yatzy.Dice;
import yatzy.FileHandler;
import yatzy.Frame;
import yatzy.Player;
import yatzy.guiObjects.Button;
import yatzy.guiObjects.Checkbox;
import yatzy.guiObjects.DropdownMenu;
import yatzy.guiObjects.TextBox;

@SuppressWarnings("serial")
public class Game extends Frame {

	private int playerAmount;
	private int aiAmount;

	private String[] totalPlayerList;
	private int totalPlayerAmount;

	private int currentPlayer = 1;
	private int diceTurn = 0;
	private int gameTurn = 0;
	private final int maxRounds = 15;

	private Dice dice = new Dice();
	private Player player = new Player(); // Needed?

	private int xPos = 5;
	private int yPos = 350;

	private int textWidth = 25;
	private int CBwidth = 50;
	private int selectWidth = 125;

	private int Objheight = 25;

	private String[] categories = { "Ettor", "Tvåor", "Treor", "Fyror", "Femmor", "Sexor", "Summa", "Bonus", "Par",
			"Två-Par", "Triss", "Fyrtal", "Kåk", "Liten-Stege", "Stor-Stege", "Chans", "Yatzy", "Summa" };
	private int CATEGORIES_AMOUNT = categories.length;

	private Object[][] endScore;

	// Kanske flytta alla guiObject in i metod.
	private TextBox diceDisplay1 = new TextBox("", xPos, yPos + Objheight * 0, textWidth, Objheight);
	private TextBox diceDisplay2 = new TextBox("", xPos, yPos + Objheight * 1, textWidth, Objheight);
	private TextBox diceDisplay3 = new TextBox("", xPos, yPos + Objheight * 2, textWidth, Objheight);
	private TextBox diceDisplay4 = new TextBox("", xPos, yPos + Objheight * 3, textWidth, Objheight);
	private TextBox diceDisplay5 = new TextBox("", xPos, yPos + Objheight * 4, textWidth, Objheight);

	private TextBox infobox = new TextBox(String.valueOf(currentPlayer), xPos, yPos + Objheight * 6, textWidth,
			Objheight);
	private TextBox infobox2 = new TextBox("Current Player", infobox.getX() + infobox.getWidth(), infobox.getY(),
			"Current Player".length() * 7, Objheight);

	private Checkbox d1 = new Checkbox("D1", diceDisplay1.getX() + textWidth, diceDisplay1.getY(), CBwidth, Objheight);
	private Checkbox d2 = new Checkbox("D2", diceDisplay2.getX() + textWidth, diceDisplay2.getY(), CBwidth, Objheight);
	private Checkbox d3 = new Checkbox("D3", diceDisplay3.getX() + textWidth, diceDisplay3.getY(), CBwidth, Objheight);
	private Checkbox d4 = new Checkbox("D4", diceDisplay4.getX() + textWidth, diceDisplay4.getY(), CBwidth, Objheight);
	private Checkbox d5 = new Checkbox("D5", diceDisplay5.getX() + textWidth, diceDisplay5.getY(), CBwidth, Objheight);

	private DropdownMenu selectDropDown = new DropdownMenu(infobox2.getX() + infobox2.getWidth(), yPos - 30,
			selectWidth, Objheight);
	private DropdownMenu scratchMenu = new DropdownMenu(selectDropDown.getX(), infobox.getY(),
			selectDropDown.getWidth(), Objheight);

	private JTextArea pointSheet = new JTextArea();

	private JButton rollButton = new Button("Roll die", xPos, yPos - 30, textWidth + CBwidth, Objheight);
	private JButton categorySelectButton = new Button("Select category",
			selectDropDown.getX() + selectDropDown.getWidth(), selectDropDown.getY(), selectWidth, Objheight);
	private JButton scratchButton = new Button("Skip category", categorySelectButton.getX(), infobox.getY(),
			selectWidth, Objheight);

	private JButton highscoreButton = new Button("HIGHSCORES",
			categorySelectButton.getX() + categorySelectButton.getWidth(), categorySelectButton.getY(), selectWidth,
			Objheight);

	Object[][] startTable;
	private JButton saveButton = new Button("Save", highscoreButton.getX(), highscoreButton.getY() + Objheight,
			selectWidth, Objheight);
	// ------------------------------------------------------------------------------------------------------------

	public Game(String[] playerList, String[] aiList) {
		this.playerAmount = playerList.length;
		this.aiAmount = aiList.length;

		this.totalPlayerAmount = this.playerAmount + this.aiAmount;
		this.totalPlayerList = new String[this.totalPlayerAmount];

		for (int i = 0; i < totalPlayerList.length; i++) {
			if (i < playerAmount) {
				totalPlayerList[i] = playerList[i];
			} else {
				totalPlayerList[i] = aiList[i - playerAmount];
			}
		}
		this.endScore = new Object[totalPlayerAmount][2];
		this.startTable = startTable();
	}

	public Game(Object[][] startTable, String[] playerList, int gameTurn, int aiAmount) {
		this.gameTurn = gameTurn;
		this.startTable = startTable;
		this.totalPlayerList = playerList;
		this.totalPlayerAmount = playerList.length;
		this.endScore = new Object[totalPlayerAmount][2];
		this.aiAmount = aiAmount;
		this.playerAmount = totalPlayerAmount-aiAmount;
		//this.currentPlayer = 1;
	}

	// ------------------------------------------------------------------------------------------------------------

	public void draw() {
		saveButton.setEnabled(false);
		frame.setLocation(500, 100);

		// Create a table to read and write to
		// Following lines also makes sure the user cant edit any part of the table.
		JPanel panel = new JPanel();
		JTable table = new JTable(startTable, totalPlayerList) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		table.setPreferredScrollableViewportSize(new Dimension(80 * (totalPlayerAmount + 1), 16 * CATEGORIES_AMOUNT));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		panel.add(new JScrollPane(table));
		frame.setContentPane(panel);
		frame.pack(); // The table need to be packed "before" any additional components get added to
						// the frame

		/**
		 * This is an internal
		 * divider-------------------------------------------------------------------------------------
		 */

		frame.setSize(frame.getWidth(), 700);
		frame.setLayout(null);

		// Använd egna klasser för att gruppera object. Ex lägg alla checkboxes i ett
		// object.
		selectDropDown.addItem("Select category");

		pointSheet.setBounds(xPos, yPos + Objheight * 7, textWidth * 5, Objheight * 5);
		pointSheet.setEditable(false);

		scratchButton.setEnabled(false);

		scratchMenu.addItem("Select category");

		rollButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Limit diceTurns to 3!!!!!
				if (diceTurn < 3) {
					dice.enableDice(!d1.isSelected(), !d2.isSelected(), !d3.isSelected(), !d4.isSelected(),
							!d5.isSelected());
					dice.rollDice();
					diceTurn++;

					diceDisplay1.setText(dice.getDice()[0][0].toString());
					diceDisplay2.setText(dice.getDice()[1][0].toString());
					diceDisplay3.setText(dice.getDice()[2][0].toString());
					diceDisplay4.setText(dice.getDice()[3][0].toString());
					diceDisplay5.setText(dice.getDice()[4][0].toString());

					// After a diceroll, get and set the available to the dropDownMenu
					String[] getCatArray = player.setCategories(dice.getDice());
					LinkedList<String> tempList = new LinkedList<>();
					for (String s : getCatArray) {
						tempList.add(s);
					}
					for (int i = 0; i < CATEGORIES_AMOUNT; i++) {
						if (!table.getValueAt(i, currentPlayer).equals("")) {
							tempList.remove(table.getValueAt(i, 0));
						}
					}
					String[] avaibleCate = tempList.toArray(new String[tempList.size()]);
					selectDropDown.replace(avaibleCate);

					// Print the pointvalue of available categories
					String temp = "";
					for (String s : avaibleCate) {
						temp += value(s) + "p - " + s + "\n";
					}
					pointSheet.setText(temp);

					// if no categories is aviable enable the scratch mechanic.
					if (avaibleCate.length == 0/* && diceTurn == 3 */) {
						scratchButton.setEnabled(true);
					} else {
						scratchButton.setEnabled(false);
					}
				}
			}
		});

		categorySelectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Only excecute the code if an actuall category is chosen
				if (!selectDropDown.getSelectedItem().equals("Select category") && diceTurn > 0
						&& table.getValueAt(tablepos(selectDropDown.getSelectedItem()), currentPlayer).equals("")) {

					// Set the value to the chosen category for the current player
					table.setValueAt(value(selectDropDown.getSelectedItem()),
							tablepos(selectDropDown.getSelectedItem()), currentPlayer);

					// Calculate the summ of the first 6 categories
					int sum1 = 0;
					for (int i = 0; i < 6; i++) {
						if (!table.getValueAt(i, currentPlayer).equals("")) {
							sum1 += (int) table.getValueAt(i, currentPlayer);
						}
					}
					table.setValueAt(sum1, 6, currentPlayer);

					if (sum1 >= 63) { // Bonus
						table.setValueAt(50, 7, currentPlayer);
					}

					// Calculate the summ for all categories
					int sum2 = 0;
					for (int i = 6; i < 17; i++) {
						if (!table.getValueAt(i, currentPlayer).equals("")) {
							sum2 += (int) table.getValueAt(i, currentPlayer);
						}
					}
					table.setValueAt(sum2, 17, currentPlayer);

					selectDropDown.replace(player.setCategories(dice.getDice()));

					if (gameTurn <= maxRounds) {
						for (int i = 0; i < totalPlayerAmount - 1; i++) {
							endScore[i][0] = totalPlayerList[i + 1];
							endScore[i][1] = table.getValueAt(17, i + 1);
							System.out.println(endScore[i][0] + ": " + endScore[i][1]);
						}
						System.out.println("");
					}
					updateTurn();

					// Update current player box and scratchMenu
					// Can't use because of the table interaction
					LinkedList<String> scratchMenuList = new LinkedList<>();
					for (int i = 0; i < table.getRowCount(); i++) {
						if (table.getValueAt(i, currentPlayer).equals("")) {
							scratchMenuList.add((String) table.getValueAt(i, 0));
						}
					}
					if (scratchMenuList.contains("Bonus")) { // FIX for "Bonus bug"
						scratchMenuList.remove("Bonus");
					}
					scratchMenu.replace(scratchMenuList.toArray(new String[scratchMenuList.size()]));

					updateUI();
					callAI();
				}
			}
		});

		scratchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!scratchMenu.getSelectedItem().equals("Select category")) {
					table.setValueAt(0, tablepos(scratchMenu.getSelectedItem()), currentPlayer);
					scratchButton.setEnabled(false);

					if (gameTurn <= maxRounds) {
						for (int i = 0; i < totalPlayerAmount - 1; i++) {
							endScore[i][0] = totalPlayerList[i + 1];
							endScore[i][1] = table.getValueAt(17, i + 1);
							System.out.println(endScore[i][0] + ": " + endScore[i][1]);
						}
						System.out.println("");
					}
					updateTurn();

					// Update scratchMenu
					LinkedList<String> scratchMenuList = new LinkedList<>();
					for (int i = 0; i < table.getRowCount(); i++) {
						if (table.getValueAt(i, currentPlayer).equals("")) {
							scratchMenuList.add((String) table.getValueAt(i, 0));
						}
					}

					// One of these need to go before demo!
					String bonus = "Bonus";
					if (scratchMenuList.contains(bonus)) { // Bonus bug fix
						scratchMenuList.remove(bonus);
					}
					scratchMenu.replace(scratchMenuList.toArray(new String[scratchMenuList.size()]));
					scratchMenu.removeItem("Bonus");

					updateUI();
					callAI();
				}
			}
		});

		highscoreButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Highscore highscoreS = new Highscore();
				highscoreS.draw();
				highscoreS.show();
			}

		});

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentPlayer == 1) {
					String url = "D:/Eclipse Workspace/TDDC77Labbar/src/Yatzy/resources/saveFiles/";
					String date = LocalDate.now().toString() + "-";
					String time = LocalTime.now().toString().replace(':', '-');
					String fileName = url + date + time;
					fileName.replace(".", "");
					File file = new File(fileName);
					try {
						file.createNewFile();
						FileHandler fh = new FileHandler(file);
						fh.save(startTable, totalPlayerList, gameTurn, totalPlayerAmount);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

		});

		frame.add(scratchButton);
		frame.add(scratchMenu);

		frame.add(pointSheet);

		frame.add(categorySelectButton);
		frame.add(selectDropDown);

		frame.add(diceDisplay1);
		frame.add(diceDisplay2);
		frame.add(diceDisplay3);
		frame.add(diceDisplay4);
		frame.add(diceDisplay5);

		frame.add(infobox);
		frame.add(infobox2);

		frame.add(d1);
		frame.add(d2);
		frame.add(d3);
		frame.add(d4);
		frame.add(d5);

		frame.add(rollButton);
		frame.add(highscoreButton);
		frame.add(saveButton);
		
		if(aiAmount == 0) {
			saveButton.setEnabled(true);
		}

		if (aiAmount == 5) {
			callAI();
		}
	}

	// ------------------------------------------------------------------------------------------------------------

	private void callAI() {
		// AI
		if (currentPlayer >= playerAmount && gameTurn < maxRounds) {
			rollButton.doClick();
			if (!scratchButton.isEnabled()) {
				selectDropDown.setSelectedIndex(1);
				categorySelectButton.doClick();
			} else {
				scratchMenu.setSelectedIndex(1);
				scratchButton.doClick();
			}
		}

	}

	// ------------------------------------------------------------------------------------------------------------

	private void updateTurn() {
		diceTurn = 0;
		currentPlayer++;

		if (currentPlayer >= totalPlayerAmount) {
			currentPlayer = 1;
			gameTurn++;
			System.out.println(gameTurn);
			if (gameTurn >= maxRounds) {
				rollButton.setEnabled(false);
				categorySelectButton.setEnabled(false);
				scratchButton.setEnabled(false);

				System.out.println(endScore[4][0] + ": " + endScore[4][1]);
				Frame end = new EndScreen(endScore);
				end.draw();
				end.show();
			}
		}

	}

	// ------------------------------------------------------------------------------------------------------------

	private void updateUI() {
		infobox.setText(String.valueOf(currentPlayer));
		diceDisplay1.setText("");
		diceDisplay2.setText("");
		diceDisplay3.setText("");
		diceDisplay4.setText("");
		diceDisplay5.setText("");

		d1.setSelected(false);
		d2.setSelected(false);
		d3.setSelected(false);
		d4.setSelected(false);
		d5.setSelected(false);

		pointSheet.setText("");
		
		if(currentPlayer != 1) {
			saveButton.setEnabled(false);
		}
		else {
			saveButton.setEnabled(true);
		}
	}

	// ------------------------------------------------------------------------------------------------------------

	private int value(Object selectedItem) {// Grabs the value for a selected category based on the current dice
											// values
		int ret = 0;
		int ret2 = 0;
		int[] numFreq = { 0, 0, 0, 0, 0, 0 };
		for (int k = 0; k < dice.size(); k++) {
			numFreq[(int) dice.getDice()[k][0] - 1]++;
		}

		switch (selectedItem.toString()) {
		case "Ettor":
			return sumOfNum(1);
		case "Tvåor":
			return sumOfNum(2);
		case "Treor":
			return sumOfNum(3);
		case "Fyror":
			return sumOfNum(4);
		case "Femmor":
			return sumOfNum(5);
		case "Sexor":
			return sumOfNum(6);

		case "Par":
			return multiplar(2);

		case "Två-Par":
			for (int i = 0; i < numFreq.length; i++) {
				for (int n = 0; n < numFreq.length; n++) {
					if (numFreq[i] == 2 && numFreq[n] == 2 && i != n) {
						ret = i + 1;
						ret2 = n + 1;
					}
				}
			}
			return 2 * ret + 2 * ret2;

		case "Triss":
			return multiplar(3);

		case "Fyrtal":
			return multiplar(4);

		case "Kåk":
			for (int i = 0; i < numFreq.length; i++) {
				for (int n = 0; n < numFreq.length; n++) {
					if (numFreq[i] == 2 && numFreq[n] == 3 && i != n) {
						ret = i + 1;
						ret2 = n + 1;
					}
				}
			}
			return 2 * ret + 3 * ret2;
		case "Liten-Stege":
			return 15;
		case "Stor-Stege":
			return 20;
		case "Chans":
			for (int i = 0; i < dice.size(); i++) {
				ret += (int) dice.getDice()[i][0];
			}
			return ret;
		case "Yatzy":
			return 50;
		default:
			return -1;
		}
	}

	// ------------------------------------------------------------------------------------------------------------

	private int multiplar(int n) {
		int[] numFreq = { 0, 0, 0, 0, 0, 0 };
		for (int k = 0; k < dice.size(); k++) {
			numFreq[(int) dice.getDice()[k][0] - 1]++;
		}

		int ret = 0;
		for (int i = 0; i < numFreq.length; i++) {
			if (numFreq[i] == n) {
				ret = i + 1;
			}
		}

		return n * ret;
	}

	// ------------------------------------------------------------------------------------------------------------

	private int sumOfNum(int n) {
		int[] numFreq = { 0, 0, 0, 0, 0, 0 };
		for (int k = 0; k < dice.size(); k++) {
			numFreq[(int) dice.getDice()[k][0] - 1]++;
		}
		return numFreq[n - 1] * n;
	}

	// ------------------------------------------------------------------------------------------------------------

	private int tablepos(Object selectedItem) {
		switch (selectedItem.toString()) {
		case "Ettor":
			return 0;
		case "Tvåor":
			return 1;
		case "Treor":
			return 2;
		case "Fyror":
			return 3;
		case "Femmor":
			return 4;
		case "Sexor":
			return 5;
		case "Bonus":
			return 6;
		case "Summa":
			return 7;
		case "Par":
			return 8;
		case "Två-Par":
			return 9;
		case "Triss":
			return 10;
		case "Fyrtal":
			return 11;
		case "Kåk":
			return 12;
		case "Liten-Stege":
			return 13;
		case "Stor-Stege":
			return 14;
		case "Chans":
			return 15;
		case "Yatzy":
			return 16;
		default:
			return -1;
		}
	}

	// ------------------------------------------------------------------------------------------------------------

	private Object[][] startTable() {// Creates inital startTable from playerAmount
		Object[][] startTable = new Object[categories.length][totalPlayerAmount + 1];

		for (int i = 0; i < CATEGORIES_AMOUNT; i++) {
			startTable[i][0] = categories[i];
			for (int j = 0; j < totalPlayerAmount; j++) {
				startTable[i][j + 1] = "";
			}
		}
		return startTable;
	}
}
