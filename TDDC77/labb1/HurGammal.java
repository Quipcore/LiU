import java.util.Scanner;

public class HurGammal{
   public static void main(String[] args) {
     //1.4
     Scanner scan = new Scanner (System.in);
     System.out.println("Hej hur gammal är du?");
     int age = scan.nextInt();
     System.out.println("Du är " + age + " år gammal.");
   }
}
