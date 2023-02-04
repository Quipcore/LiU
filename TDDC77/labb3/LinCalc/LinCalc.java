package labb3.LinCalc;
import java.util.Scanner;
import labb3.LinCalcFelix;

public class LinCalc {

    public static void printArray(String[] array){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            sb.append(", ");
        }
        // Replace the last ", " with "]"
        sb.replace(sb.length() - 2, sb.length(), "]");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expression;
        double result;

        System.out.print("Enter expression: ");
        expression = in.nextLine();
        do {
            result = evaluate(expression);
            System.out.println("Result was: " + result);
            System.out.print("Enter expression: ");
        } while (!"".equals(expression = in.nextLine()));
        in.close();
    }

    public static double calc(String[] lexedPostfix) {
        return LinCalcFelix.calc(lexedPostfix);
    }

    public static String[] toPostfix(String[] inputData) {

        return LinCalcFelix.toPostfix(inputData);
    }

    public static double evaluate(String expression) {
        String[] lexedInfix = lex(expression);
        String[] lexedPostfix = toPostfix(lexedInfix);
        printArray(lexedPostfix);
        return calc(lexedPostfix);
    }

    public static String[] lex(String expression) {
    	
    	
    	printArray(LinCalcFelix.lex(expression));
    	
        return LinCalcFelix.lex(expression);
        
    	
    }
}
