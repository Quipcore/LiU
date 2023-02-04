package labb4.abstractlabb4;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.print("Enter amount of rows: ");
		int rows = scan.nextInt() + 1;

		System.out.print("Enter amount of columns: ");
		int columns = scan.nextInt() + 1;

		System.out.println("1. Addition");
		System.out.println("2. Subtraction");
		System.out.println("3. Multiplication");
		System.out.println("4. Division");
		System.out.print("Choose an item: ");

		int menu = scan.nextInt();
		switch (menu) {
		case 1:
			AdditionTable Add = new AdditionTable(rows, columns);
			Add.print();
			break;
		case 2:
			SubtractionTable Sub = new SubtractionTable(rows, columns);
			Sub.print();
			break;
		case 3:
			MultiplicationTable Multi = new MultiplicationTable(rows, columns);
			Multi.print();
			break;
		case 4:
			DivisionTable Div = new DivisionTable(rows, columns);
			Div.print();
			break;
		default:
			System.out.println("Not a correct number!");
			break;
		}
		scan.close();
	}
}