package labb4.MultiplicationTable;

import java.util.Scanner;
//import labb4.MultiplicationTable;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);

		System.out.print("Enter amount of rows: ");		
		//int rows = Integer.valueOf(args[0]) + 1;
		int rows = scan.nextInt() + 1;

		System.out.print("Enter amount of columns: ");
		//int columns = Integer.valueOf(args[1]) + 1;
		int columns = scan.nextInt() + 1;

		System.out.println("");

		MultiplicationTable Multi = new MultiplicationTable(rows,columns);
		Multi.print();

		scan.close();
	}
}
