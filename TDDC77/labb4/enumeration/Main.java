package labb4.enumeration;

import java.util.Scanner;

public class Main {
	public static EnumOperation getOperation(int menu) {
		switch(menu) {
		case 1: return EnumOperation.ADDITION;
		case 2: return EnumOperation.SUBTRACTION;
		case 3: return EnumOperation.MULTIPLICATION;
		case 4: return EnumOperation.DIVISION;
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
		System.out.print("Choose an item: \n");
		
		int menu = scan.nextInt();
		Operation operation = getOperation(menu);
		
		Arithmetictable tab = new Arithmetictable(operation, rows, columns);
		tab.print();

		scan.close();
	}
}