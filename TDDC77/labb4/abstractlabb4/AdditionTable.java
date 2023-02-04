package labb4.abstractlabb4;

public class AdditionTable extends Arithmetictable {

	AdditionTable(int rows, int columns) {
		super(rows, columns);
		// TODO Auto-generated constructor stub
	}
	@Override
	public double evaluate(int a, int b) {
		return a+b;
	}
	@Override
	public String symbol() {
		// TODO Auto-generated method stub
		return "+";
	}

}
