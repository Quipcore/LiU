import java.util.Scanner;

public class VadHeterDu{
   public static void main(String[] args) {
     //1.6
     Scanner scan = new Scanner (System.in);
     System.out.print("Vad heter du? ");
     String name = scan.nextLine();
     System.out.println("Hej, " + name + "!");
   }
}
