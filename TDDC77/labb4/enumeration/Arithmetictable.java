package labb4.enumeration;

public class Arithmetictable {

	int rows;
	int columns;
	Operation operation;

	public Arithmetictable(Operation op, int r, int c) {
		this.rows = r;
		this.columns = c;
		this.operation = op;
	}

	public void print() {
		
		double[][] Table = new double[rows][columns];

		for (int i = 0; i < Table.length; i++) {
			for (int j = 0; j < Table[0].length; j++) {
				Table[i][j] = operation.evaluate(i, j);
			}
		}

		// Formatting
		int width = operation.width(rows, columns);
		int offset = 4;
		String columnwidthINT = "%" + (width + offset) + ".2f";
		String columnwidthSTR = "%" + (width + offset + 2) + "s";

		// Create FirstRow
		String FirstRow = String.format(columnwidthSTR, operation.symbol() + " |");
		for (double k = 0; k < Table[0].length; k++) {
			FirstRow += String.format(columnwidthINT, k);
		}

		// Create SecondRow
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

		// Print rest rows
		for (double i = 0; i < Table.length; i++) {
			System.out.print(String.format(columnwidthINT, i) + " |");
			for (double j = 0; j < Table[0].length; j++) {
				System.out.print(String.format(columnwidthINT, Table[(int) i][(int) j]));
			}
			System.out.println("");
		}
	}

}
