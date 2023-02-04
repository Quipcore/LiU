package labb4.abstractlabb4;

public class SubtractionTable extends Arithmetictable {

	SubtractionTable(int rows, int columns) {
		super(rows, columns);
	}

	@Override
	public double evaluate(int a, int b) {
		return b-a;
	}

	@Override
	public String symbol() {
		// TODO Auto-generated method stub
		return "-";
	}

}
