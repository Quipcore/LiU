package labb4.MultiplicationTable;

class MultiplicationTable{
	
	private int rows;
	private int columns;
	
	public MultiplicationTable(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
	}
	
	//----------------------------------------------------------

	private int[][] Multicalc() {
		int[][] Table = new int[rows][columns];

		for (int i = 0; i < Table.length; i++) {
			for (int j = 0; j < Table[0].length; j++) {
				Table[i][j] = i * j;
			}
		}
		return Table;
	}
	
	//----------------------------------------------------------
	
	public void print() {

		int[][] Table = Multicalc();
		
		//Formatting
		int width = String.valueOf(Table[rows-1][columns-1]).length();
		String columnwidthINT = "%" + (width + 1) + "d";
		String columnwidthSTR = "%" + (width + 3) + "s";

		//Create FirstRow
		String FirstRow = String.format(columnwidthSTR, "* |");
		for (int k = 0; k < Table[0].length; k++) {
			FirstRow += String.format(columnwidthINT, k);
		}
		
		//Create SecondRow
		String secondRow = "";
		for (int n = 0; n < FirstRow.length(); n++) {
			if (FirstRow.charAt(n) == '|') {
				secondRow += "+";
			} else {
				secondRow += "-";
			}
		}

		// Printing first two rows
		System.out.println(FirstRow + "\n" + secondRow);
		
		//Print rest rows
		for (int i = 0; i < Table.length; i++) {
			System.out.print(String.format(columnwidthINT, i) + " |");
			for (int j = 0; j < Table[0].length; j++) {
				System.out.print(String.format(columnwidthINT, Table[i][j]));
			}
			System.out.println("");
		}
	}
	
}
