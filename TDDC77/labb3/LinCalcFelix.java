package labb3;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class LinCalcFelix {

	// ------------------------------------------------------------------------------------------------

	public static boolean compare(char index) {

		String operatorer = "(+-*/)";
		if (operatorer.contains(Character.toString(index))) {
			return true;

		} else {
			return false;
		}
	}
	
	
	public static boolean isNumber(String tecken) {
		String nummer = "0123456789";
		
		if (tecken.length() == 1 && nummer.contains(Character.toString(tecken.charAt(0)))) {
			return true;
		}
		else if(tecken.length() >= 2) {
			return true;
		}
		else {
			return false;
		}
	}

	// ------------------------------------------------------------------------------------------------
	public static String implicitMultiplcation(String str) {
		String[] splitStr = str.split("");
		String temp = "";
		
		for (int i = 0; i < splitStr.length - 1; i++) {
			if (splitStr[i].equals("-") && splitStr[i + 1].equals("(")) {
				splitStr[i] += "1*";
			}
		}
				
		for (int j = 0; j < splitStr.length; j++) {
			temp += splitStr[j];
		}
		str = temp;
		return str;
	}

	// ------------------------------------------------------------------------------------------------

	public static String[] lex(String expression) {

		expression = implicitMultiplcation(expression);

		expression = expression.replaceAll(",", ".");

		LinkedList<String> lexQueue = new LinkedList<>();
		String tempString = "";

		for (int index = 0; index < expression.length(); index++) {

			if (expression.charAt(index) == '-') {

				if (index == 0 || compare(expression.charAt(index - 1))) {

					tempString = tempString + expression.charAt(index);

				} else {
					lexQueue.add(tempString);

					lexQueue.add(Character.toString(expression.charAt(index)));

					tempString = "";
				}

			} else if (compare(expression.charAt(index))) {

				if (tempString != "") {
					lexQueue.add(tempString);
				}

				lexQueue.add(Character.toString(expression.charAt(index)));

				tempString = "";

			} else {

				tempString = tempString + expression.charAt(index);
			}
		}

		if (tempString != "") {
			lexQueue.add(tempString);
		}

		// Används för att få ut rätt format vid test
		String[] myArray = new String[lexQueue.size()];
		return lexQueue.toArray(myArray);
	}

	// ------------------------------------------------------------------------------------------------
	
	public static String[] toPostfix(String[] infix) {
		LinkedList<String> postFix = new LinkedList<>();
		Stack<String> stack = new Stack<>();
		
		for(int i = 0; i < infix.length; i++) {
			if(isNumber(infix[i])) {
				postFix.add(infix[i]);
			}
			else if(compare(infix[i].charAt(0)) && !infix[i].equals("(") && !infix[i].equals(")")) {
				while(!stack.isEmpty() && !stack.peek().equals("(") && valueOfOperator(infix[i]) <= valueOfOperator(stack.peek())) {
					postFix.add(stack.pop());
				}
				stack.push(infix[i]);
			}
			else if(infix[i].equals("(")){
				stack.push(infix[i]);
			}
			else if(infix[i].equals(")")) {
				if(!stack.isEmpty()) {
					while(!stack.peek().equals("(")) {
						postFix.add(stack.pop());
					}
					stack.pop();
				}
			}
		}
		
		
		while(!stack.isEmpty()) {
			postFix.add(stack.pop());
		}
		
		String[] tempArray = new String[postFix.size()];
		return postFix.toArray(tempArray);
		
	}
	
	// ------------------------------------------------------------------------------------------------
	public static int valueOfOperator(String operator) {

		int value;
		switch (operator) {
		case "(":
			value = 0;
			return value;
		case "+":
		case "-":
			value = 1;
			return value;
		case "*":
		case "/":
			value = 2;
			return value;
		case ")":
			value = 3;
			return value;
		default:
			value = -1;
			return value;
		}
	}

	// ------------------------------------------------------------------------------------------------

	public static double calc(String[] postfix) {
		double result = 0;
		Stack<String> stk = new Stack<>();
		double a = 1;
		double b = 0;
		for (int i = 0; i < postfix.length; i++) {
			if (!compare(postfix[i].charAt(0))) {
				stk.push(postfix[i]);

			} else if (compare(postfix[i].charAt(0))) {
				// Hanterar uttryck som startar med negativa tal
				if (postfix[i].length() > 1) {
					stk.push(postfix[i]);

				} else {
					a = Double.parseDouble(stk.pop());
					b = Double.parseDouble(stk.pop());

					switch (postfix[i]) {
					case "+":
						stk.push(String.valueOf(b + a));
						break;
					case "-":
						stk.push(String.valueOf(b - a));
						break;
					case "*":
						stk.push(String.valueOf(b * a));
						break;
					case "/":
						stk.push(String.valueOf(b / a));
						break;
					}
				}
			}
		}

		result = Double.parseDouble(stk.pop());
		return result;

	}

	// ------------------------------------------------------------------------------------------------

	public static double evaluate(String expression) {
		String[] lexedInfix = lex(expression);
		String[] lexedPostfix = toPostfix(lexedInfix);
		return calc(lexedPostfix);
	}

	// ------------------------------------------------------------------------------------------------

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String expression;
		double result;

		System.out.println("Enter an expression: ");
		// expression = scan.nextLine();
		expression = "11+(22-33*1+11)/99-8*3.0";
		
		
		//runTestcase("1-2*3+4", -1.0);
		// runTestcase("11+(22-33*1+11)/99-8*3.0", -13);

		result = evaluate(expression);
		System.out.println(result);

		scan.close();
	}

}
