package labb4.composition;

public class DivisionOperation implements Operation{

	@Override
	public char symbol() {
		return '/';
	}

	@Override
	public int width(int rows, int cols) {
		return String.valueOf(cols).length();
	}

	@Override
	public double evaluate(int a, int b) {
		//Bad fix for div by 0
		if(a == 0) {
			return 0;
		}
		
		return (b*100d/a)/100d;
	}

}
