package labb4.abstractlabb4;

public class MultiplicationTable extends Arithmetictable{

	MultiplicationTable(int rows, int columns) {
		super(rows, columns);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double evaluate(int a, int b) {

		double c = a*b;
		return c;
	}

	@Override
	public String symbol() {
		// TODO Auto-generated method stub
		return null;
	}
}
