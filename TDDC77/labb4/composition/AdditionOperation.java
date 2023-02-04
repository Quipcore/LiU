package labb4.composition;

public class AdditionOperation implements Operation{


	@Override
	public char symbol() {
		return '+';
	}

	@Override
	public int width(int rows, int cols) {
		return String.valueOf(rows+cols).length();
	}

	@Override
	public double evaluate(int a, int b) {
		return b+a;
	}

}
