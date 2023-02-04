package labb4.abstractlabb4;

public class DivisionTable extends Arithmetictable {

	public DivisionTable(int rows, int columns) {
		super(rows, columns);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double evaluate(int a, int b) {
		if(a == 0) {
			return 0;
		}else {
			return (b*100d/a) / 100;
		}
	}

	@Override
	public String symbol() {
		// TODO Auto-generated method stub
		return "/";
	}

}
