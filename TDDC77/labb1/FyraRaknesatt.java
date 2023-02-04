import java.util.Scanner;

public class FyraRaknesatt{
   public static void main(String[] args) {
     //1.5
     Scanner scan = new Scanner (System.in);
     System.out.print("Mata in ett tal: ");
     double tal1 = scan.nextDouble();
     System.out.print("Mata in ett till tal: ");
     double tal2 = scan.nextDouble();
     System.out.println(tal1 +" + "+ tal2 + " = " + (tal1 + tal2));
     System.out.println(tal1 +" - "+ tal2 + " = " + (tal1 - tal2));
     System.out.println(tal1 +" * "+ tal2 + " = " + (tal1 * tal2));
     System.out.println(tal1 +" / "+ tal2 + " = " + (tal1 / tal2));
   }
}
