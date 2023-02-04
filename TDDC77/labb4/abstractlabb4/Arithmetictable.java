package labb4.abstractlabb4;

public abstract class Arithmetictable {

	int rows;
	int columns;
	String symbol;
	
	Arithmetictable(int rows, int columns) {
		this.columns = columns;
		this.rows = rows;
	}
	//----------------------------------------------------------
	public abstract String symbol();
	public abstract double evaluate(int a, int b);
	
	//----------------------------------------------------------
	
	private double[][] calc() {
		double[][] Table = new double[rows][columns];

		for (int i = 0; i < Table.length; i++) {
			for (int j = 0; j < Table[0].length; j++) {
				Table[i][j] = evaluate(i, j);
			}
		}
		return Table;
	}
	
	//----------------------------------------------------------

	//Kan byta utas ut till en absract width metod som ligger i table klasserna
	private int maxNumSearch(double[][] Table) {
		int maxNum = 0;
		
		for (int i = 0; i < Table.length; i++) {
			for (int j = 0; j < Table[0].length; j++) {
				if(maxNum < Math.abs(Table[j][i])) {
					if(Table[j][i] < 0) {
						maxNum = (int) Math.abs(Table[j][i]) +1;
					}
					else {
						maxNum = (int) Math.abs(Table[j][i]);
					}
				}
			}
		}
		
		return maxNum;
	}
	
	//----------------------------------------------------------
	
	public void print() {
		
		double[][] Table = calc();
	
		//Formatting
		double maxNum = maxNumSearch(Table);
		int width = String.valueOf(maxNum).length();
		int offset = 2;
		String columnwidthINT = "%" + (width + offset) + ".2f";
		String columnwidthSTR = "%" + (width + offset +2) + "s";

		//Create FirstRow
		String FirstRow = String.format(columnwidthSTR, symbol() +" |");
		for (double k = 0; k < Table[0].length; k++) {
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
		for (double i = 0; i < Table.length; i++) {
			System.out.print(String.format(columnwidthINT, i) + " |");
			for (double j = 0; j < Table[0].length; j++) {
				System.out.print(String.format(columnwidthINT, Table[(int)i][(int)j]));
			}
			System.out.println("");
		}
	}
}
