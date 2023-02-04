package labb4.composition;

import java.util.Scanner;
public class Main {
	
	public static Operation getOperation(int menu) {
		switch(menu) {
		case 1: return new AdditionOperation();
		case 2: return new SubtractionOperation();
		case 3: return new DivisionOperation();
		case 4: return new MultiplicationOperation();
		default: throw new IllegalArgumentException("Menu item not valid");
		}
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.print("Enter amount of rows: ");
		int rows = scan.nextInt() + 1;

		System.out.print("Enter amount of columns: ");
		int columns = scan.nextInt() + 1;

		System.out.println("\n1. Addition");
		System.out.println("2. Subtraction");
		System.out.println("3. Multiplication");
		System.out.println("4. Division");
		System.out.print("Choose an item: ");
		
		int menu = scan.nextInt();
		Operation operation = getOperation(menu);
		Arithmetictable tab = new Arithmetictable(operation, rows, columns);
		tab.print();

		
		Operation op = new Operation() {

			@Override
			public char symbol() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int width(int rows, int cols) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public double evaluate(int a, int b) {
				// TODO Auto-generated method stub
				return 0;
			}
			
		}

		scan.close();
	}
}