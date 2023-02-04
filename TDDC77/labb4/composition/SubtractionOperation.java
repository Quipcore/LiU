package labb4.composition;

public class SubtractionOperation implements Operation{


	@Override
	public char symbol() {
		return '-';
	}

	@Override
	public int width(int rows, int cols) {
		// Max(diff) + '-' sign
		return String.valueOf(Math.max(rows, cols)).length()+1;
	}

	@Override
	public double evaluate(int a, int b) {
		return b-a;
	}

}
