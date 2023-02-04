package labb4.enumeration;

public enum EnumOperation implements Operation{

	ADDITION('+'){
		@Override
		public int width(int rows, int cols) {
			return String.valueOf(rows+cols).length();
		}

		@Override
		public double evaluate(int a, int b) {
			return b+a;
		}
	},
	SUBTRACTION('-'){
		@Override
		public int width(int rows, int cols) {
			// Max(diff) + '-' sign
			return String.valueOf(Math.max(rows, cols)).length()+1;
		}

		@Override
		public double evaluate(int a, int b) {
			return b-a;
		}
	},
	MULTIPLICATION('*'){
		@Override
		public int width(int rows, int cols) {
			return String.valueOf(rows*cols).length();
		}

		@Override	
		public double evaluate(int a, int b) {
			return b*a;
		}
	},
	DIVISION('/'){
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
	};
	
	private char symbol;
	
	private EnumOperation(char symbol) {
		this.symbol = symbol;

	}
	
	public char symbol() {
		return symbol;
	}
}
